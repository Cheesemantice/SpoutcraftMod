/**
 * This file is a part of Spoutcraft.
 *
 * Copyright (c) 2013 SpoutcraftDev <http://spoutcraft.org>
 * Spoutcraft is licensed under the MIT License.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.spoutcraft.mod;

import java.nio.ByteBuffer;
import java.util.EnumSet;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.*;
import org.spoutcraft.api.LinkedPrefabRegistry;
import org.spoutcraft.api.Spoutcraft;
import org.spoutcraft.api.addon.AddonManager;
import org.spoutcraft.api.block.MovingPrefab;
import org.spoutcraft.api.gl.DeleteQueueObject;
import org.spoutcraft.api.logger.SpoutcraftLogger;
import org.spoutcraft.api.material.MapIndex;
import org.spoutcraft.api.material.MaterialPrefab;
import org.spoutcraft.api.protocol.Protocol;
import org.spoutcraft.api.resource.FileSystem;
import org.spoutcraft.api.util.LanguageUtil;
import org.spoutcraft.api.util.RenderUtil;
import org.spoutcraft.api.util.TextureUtil;
import org.spoutcraft.mod.addon.ClientAddonManager;
import org.spoutcraft.mod.addon.ServerAddonManager;
import org.spoutcraft.mod.block.BlockPrefabRegistry;
import org.spoutcraft.mod.gui.builtin.SpoutcraftMainMenu;
import org.spoutcraft.mod.item.ItemPrefabRegistry;
import org.spoutcraft.mod.item.special.SpoutcraftEmblem;
import org.spoutcraft.mod.item.special.VanillaEmblem;
import org.spoutcraft.mod.material.MaterialPrefabRegistry;
import org.spoutcraft.mod.protocol.SpoutcraftConnectionHandler;
import org.spoutcraft.mod.protocol.SpoutcraftPacket;
import org.spoutcraft.mod.protocol.codec.AddPrefabCodec;
import org.spoutcraft.mod.protocol.codec.DownloadLinkCodec;
import org.spoutcraft.mod.protocol.message.AddPrefabMessage;
import org.spoutcraft.mod.protocol.message.DownloadLinkMessage;
import org.spoutcraft.mod.resource.ClientFileSystem;
import org.spoutcraft.mod.resource.ServerFileSystem;

// TODO: Reflect GameRegistry, LanguageRegistry, NetworkRegistry and remove addon content on server leave
// TODO: Fix generics?
@Mod (modid = "Spoutcraft")
@NetworkMod (clientSideRequired = true, serverSideRequired = true)
public class SpoutcraftMod {
    @Instance (value = "Spoutcraft")
    public static SpoutcraftMod instance;
    private static CustomTabs customTabs;
    //Used to delete OpenGl objects on the main thread
    private static Queue<DeleteQueueObject> glDeleteQueue = new ConcurrentLinkedQueue<DeleteQueueObject>();

    @EventHandler
    @SuppressWarnings ("unchecked")
    public void onInitialize(FMLInitializationEvent event) {
        // Setup logger
        Spoutcraft.setLogger(new SpoutcraftLogger());

        // Setup protocol
        bindCodecMessages();

        final FileSystem fileSystem;
        final AddonManager manager;

        switch (event.getSide()) {
            case CLIENT:
                // Set the title
                Display.setTitle("Spoutcraft");

                // Set the icons
                final ByteBuffer windowIcon = TextureUtil.createImageBufferFrom(new ResourceLocation("spoutcraft", "textures/window_icon.png"), true);
                final ByteBuffer taskbarIcon = TextureUtil.createImageBufferFrom(new ResourceLocation("spoutcraft", "textures/taskbar_icon.png"), true);
                if (windowIcon != null && taskbarIcon != null) {
                    Display.setIcon(new ByteBuffer[] {windowIcon, taskbarIcon});
                }

                fileSystem = Spoutcraft.setFileSystem(new ClientFileSystem());

                // Setup file system
                try {
                    fileSystem.init();
                } catch (Exception e) {
                    throw new RuntimeException("Could not initialize FileSystem", e);
                }

                manager = Spoutcraft.setAddonManager(new ClientAddonManager());

                //Setup addon manager
                manager.loadAddons(((ClientFileSystem) fileSystem).addonsPath);
                registerHandlers();
                break;
            case SERVER:
                fileSystem = Spoutcraft.setFileSystem(new ServerFileSystem());

                // Setup file system
                try {
                    fileSystem.init();
                } catch (Exception e) {
                    throw new RuntimeException("Could not initialize FileSystem", e);
                }

                manager = Spoutcraft.setAddonManager(new ServerAddonManager());

                //Setup addon manager
                manager.loadAddons(((ServerFileSystem) fileSystem).addonsPath);
                break;
            default:
                throw new RuntimeException("Spoutcraft is being ran on an invalid side!");
        }

        manager.enable();

        // Setup registries
        Spoutcraft.setBlockRegistry(new BlockPrefabRegistry());
        Spoutcraft.setItemPrefabRegistry(new ItemPrefabRegistry());
        Spoutcraft.setMaterialRegistry(new MaterialPrefabRegistry());

        // Setup creative tab
        customTabs = new CustomTabs();

        //Special
        Spoutcraft.getItemPrefabRegistry().put(new SpoutcraftEmblem());
        Spoutcraft.getItemPrefabRegistry().put(new VanillaEmblem());

        //TODO Move test code to an accompanying addon
        final LinkedPrefabRegistry registry = Spoutcraft.getBlockPrefabRegistry();
        registry.put(new MovingPrefab("0b", "0 (Black)", new MaterialPrefab("testmaterial", MapIndex.DIRT), 0.5f, true));
        registry.put(new MovingPrefab("0w", "0 (White)", new MaterialPrefab("testmaterial", MapIndex.DIRT), 0.5f, true));
        registry.put(new MovingPrefab("1b", "1 (Black)", new MaterialPrefab("testmaterial", MapIndex.DIRT), 0.5f, true));
        registry.put(new MovingPrefab("1w", "1 (White)", new MaterialPrefab("testmaterial", MapIndex.DIRT), 0.5f, true));
        registry.put(new MovingPrefab("2b", "2 (Black)", new MaterialPrefab("testmaterial", MapIndex.DIRT), 0.5f, true));
        registry.put(new MovingPrefab("2w", "2 (White)", new MaterialPrefab("testmaterial", MapIndex.DIRT), 0.5f, true));
        registry.put(new MovingPrefab("3b", "3 (Black)", new MaterialPrefab("testmaterial", MapIndex.DIRT), 0.5f, true));
        registry.put(new MovingPrefab("3w", "3 (White)", new MaterialPrefab("testmaterial", MapIndex.DIRT), 0.5f, true));
        registry.put(new MovingPrefab("4b", "4 (Black)", new MaterialPrefab("testmaterial", MapIndex.DIRT), 0.5f, true));
        registry.put(new MovingPrefab("4w", "4 (White)", new MaterialPrefab("testmaterial", MapIndex.DIRT), 0.5f, true));
        registry.put(new MovingPrefab("5b", "5 (Black)", new MaterialPrefab("testmaterial", MapIndex.DIRT), 0.5f, true));
        registry.put(new MovingPrefab("5w", "5 (White)", new MaterialPrefab("testmaterial", MapIndex.DIRT), 0.5f, true));
        registry.put(new MovingPrefab("6b", "6 (Black)", new MaterialPrefab("testmaterial", MapIndex.DIRT), 0.5f, true));
        registry.put(new MovingPrefab("6w", "6 (White)", new MaterialPrefab("testmaterial", MapIndex.DIRT), 0.5f, true));
        registry.put(new MovingPrefab("7b", "7 (Black)", new MaterialPrefab("testmaterial", MapIndex.DIRT), 0.5f, true));
        registry.put(new MovingPrefab("7w", "7 (White)", new MaterialPrefab("testmaterial", MapIndex.DIRT), 0.5f, true));
        registry.put(new MovingPrefab("8b", "8 (Black)", new MaterialPrefab("testmaterial", MapIndex.DIRT), 0.5f, true));
        registry.put(new MovingPrefab("8w", "8 (White)", new MaterialPrefab("testmaterial", MapIndex.DIRT), 0.5f, true));
        registry.put(new MovingPrefab("9b", "9 (Black)", new MaterialPrefab("testmaterial", MapIndex.DIRT), 0.5f, true));
        registry.put(new MovingPrefab("9w", "9 (White)", new MaterialPrefab("testmaterial", MapIndex.DIRT), 0.5f, true));
    }

    private void registerHandlers() {
        // Show our main menu
        TickRegistry.registerTickHandler(new ITickHandler() {
            @Override
            public void tickStart(EnumSet<TickType> type, Object... tickData) {
                final GuiScreen current = RenderUtil.MINECRAFT.currentScreen;
                if (current != null && current.getClass() == GuiMainMenu.class && current.getClass() != SpoutcraftMainMenu.class) {
                    RenderUtil.MINECRAFT.displayGuiScreen(new SpoutcraftMainMenu());
                }
            }

            @Override
            public void tickEnd(EnumSet<TickType> type, Object... tickData) {
            }

            @Override
            public EnumSet<TickType> ticks() {
                return EnumSet.of(TickType.CLIENT);
            }

            @Override
            public String getLabel() {
                return "Spoutcraft - Main Menu Hotswap";
            }
        }, Side.CLIENT);

        // Draw our watermark in game
        TickRegistry.registerTickHandler(new ITickHandler() {
            @Override
            public void tickStart(EnumSet<TickType> type, Object... tickData) {
            }

            @Override
            public void tickEnd(EnumSet<TickType> type, Object... tickData) {
                final GuiScreen current = RenderUtil.MINECRAFT.currentScreen;
                if (current == null) {
                    ScaledResolution scaledResolution = new ScaledResolution(RenderUtil.MINECRAFT.gameSettings, RenderUtil.MINECRAFT.displayWidth, RenderUtil.MINECRAFT.displayHeight);
                    int width = scaledResolution.getScaledWidth();
                    int height = scaledResolution.getScaledHeight();

                    // Draw Spoutcraft logo
                    GL11.glPushMatrix();
                    RenderUtil.MINECRAFT.getTextureManager().bindTexture(new ResourceLocation("spoutcraft", "textures/gui/title/spoutcraft.png"));
                    GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
                    GL11.glEnable(GL11.GL_BLEND);
                    GL11.glTranslatef(width - 45, height - 13, 0.0f);
                    GL11.glScalef(0.17f, 0.17f, 1.0f);
                    GL11.glTranslatef(-width + 45, -height + 13, 0.0f);
                    RenderUtil.create2DRectangleModal(width - 45, height - 13, 256, 67, 0);
                    RenderUtil.TESSELLATOR.draw();
                    GL11.glDisable(GL11.GL_BLEND);
                    GL11.glPopMatrix();

                    // Draw milestone string
                    GL11.glPushMatrix();
                    GL11.glTranslatef(width - 14, height - 8, 0.0f);
                    GL11.glScalef(0.50f, 0.50f, 1.0f);
                    GL11.glTranslatef(-width + 14, -height + 8, 0.0f);
                    RenderUtil.MINECRAFT.fontRenderer.drawString("Alpha", width - 14, height - 3, 16776960);
                    GL11.glPopMatrix();
                }
            }

            @Override
            public EnumSet<TickType> ticks() {
                return EnumSet.of(TickType.RENDER);
            }

            @Override
            public String getLabel() {
                return "Spoutcraft - Watermark";
            }
        }, Side.CLIENT);

        //Remove delete GL Objects
        TickRegistry.registerTickHandler(new ITickHandler() {
            @Override
            public void tickStart(EnumSet<TickType> type, Object... tickData) {
            }

            @Override
            public void tickEnd(EnumSet<TickType> type, Object... tickData) {
                DeleteQueueObject toDelete;
                while ((toDelete = glDeleteQueue.poll()) != null) {
                    toDelete.delete();
                }
            }

            @Override
            public EnumSet<TickType> ticks() {
                return EnumSet.of(TickType.CLIENT);
            }

            @Override
            public String getLabel() {
                return "Spoutcraft - GL Garbage Collection";
            }
        }, Side.CLIENT);
    }

    private void bindCodecMessages() {
        Packet.addIdClassMapping(251, true, true, SpoutcraftPacket.class);
        NetworkRegistry.instance().registerConnectionHandler(new SpoutcraftConnectionHandler());
        Protocol.register(AddPrefabMessage.class, AddPrefabCodec.class);
        Protocol.register(DownloadLinkMessage.class, DownloadLinkCodec.class);
    }

    private class CustomTabs extends CreativeTabs {
        public CustomTabs() {
            super("Spoutcraft");
            LanguageUtil.add("itemGroup.Spoutcraft", "Spoutcraft");
        }

        @Override
        public ItemStack getIconItemStack() {
            return new ItemStack((Item) Spoutcraft.getItemPrefabRegistry().find("spout_emblem"), 1, 0);
        }
    }

    public static CustomTabs getCustomTabs() {
        return customTabs;
    }

    public static void queueDeletion(DeleteQueueObject obj) {
        glDeleteQueue.offer(obj);
    }
}
