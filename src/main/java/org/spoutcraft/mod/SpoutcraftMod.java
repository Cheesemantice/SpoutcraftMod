/**
 * This file is part of SpoutcraftMod, licensed under the MIT License (MIT).
 *
 * Copyright (c) 2013 SpoutcraftDev <http://spoutcraft.org/>
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

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.common.MinecraftForge;
import org.lwjgl.opengl.*;
import org.spoutcraft.api.Spoutcraft;
import org.spoutcraft.api.gl.GLGCFactory;
import org.spoutcraft.api.gl.GLGCObject;
import org.spoutcraft.api.util.RenderUtil;
import org.spoutcraft.api.util.TextureUtil;
import org.spoutcraft.mod.addon.CommonAddonManager;
import org.spoutcraft.mod.gui.builtin.SpoutcraftMainMenu;
import org.spoutcraft.mod.resource.CommonFileSystem;

// TODO: Reflect GameRegistry, LanguageRegistry, NetworkRegistry and remove addon content on server leave
@Mod (modid = Spoutcraft.MOD_ID)
public class SpoutcraftMod {
    private static CustomTabs customTabs;
    private final Spoutcraft game;

    public SpoutcraftMod() {
        game = new Spoutcraft(FMLCommonHandler.instance().getEffectiveSide());
    }

    @EventHandler
    public void onInitialize(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);

        // Setup file system
        try {
            game.getFileSystem().init();
        } catch (Exception e) {
            throw new RuntimeException("Could not initialize FileSystem", e);
        }

        // Setup addons
        game.getAddonManager().loadAddons(CommonFileSystem.ADDONS_PATH);

        switch (game.getSide()) {
            case CLIENT:
                // Set the title
                Display.setTitle("Spoutcraft");

                // Set the icons
                final ByteBuffer windowIcon = TextureUtil.createImageBufferFrom(new ResourceLocation("spoutcraft", "textures/window_icon.png"), true);
                final ByteBuffer taskbarIcon = TextureUtil.createImageBufferFrom(new ResourceLocation("spoutcraft", "textures/taskbar_icon.png"), true);
                if (windowIcon != null && taskbarIcon != null) {
                    Display.setIcon(new ByteBuffer[] {windowIcon, taskbarIcon});
                }

                break;
            case SERVER:
                break;
            default:
                throw new RuntimeException("Spoutcraft is being ran on an invalid side!");
        }

        // Setup creative tab
        customTabs = new CustomTabs(game);

        //TODO Rebuild protocol

        // Enable addons
        game.getAddonManager().enable();
    }

    public static CustomTabs getCustomTabs() {
        return customTabs;
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            // Clean up gl objects
            GLGCObject toDelete;
            while ((toDelete = GLGCFactory.poll()) != null) {
                toDelete.delete();
            }
        }
    }

    @SubscribeEvent
    public void onTick(TickEvent.RenderTickEvent event) {
        // End of tick and in-game
        if (event.phase == TickEvent.Phase.END && RenderUtil.MINECRAFT.currentScreen == null) {
            ScaledResolution scaledResolution = new ScaledResolution(RenderUtil.MINECRAFT.gameSettings, RenderUtil.MINECRAFT.displayWidth, RenderUtil.MINECRAFT.displayHeight);
            int width = scaledResolution.getScaledWidth();
            int height = scaledResolution.getScaledHeight();

            // Draw Spoutcraft logo
            GL11.glPushMatrix();
            RenderUtil.MINECRAFT.getTextureManager().bindTexture(new ResourceLocation("spoutcraft", "textures/gui/" + ((CommonAddonManager) game.getAddonManager()).getInternalAddon().getDescription().getIdentifier() + "/spoutcraft.png"));
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

    @SubscribeEvent
    public void onGuiOpen(GuiOpenEvent event) {
        //TODO Check config file, see if they want to use our menu
        if (event.gui instanceof GuiMainMenu) {
            event.gui = new SpoutcraftMainMenu(game);
        }
    }
}
