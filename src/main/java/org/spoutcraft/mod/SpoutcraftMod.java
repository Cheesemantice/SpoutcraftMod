/**
 * This file is part of SpoutcraftMod, licensed under the MIT License (MIT).
 *
 * Copyright (c) 2013-2014 SpoutcraftDev <http://spoutcraft.org/>
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

import java.awt.Font;
import java.nio.ByteBuffer;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.*;
import org.spoutcraft.api.Spoutcraft;
import org.spoutcraft.api.resource.CustomFont;
import org.spoutcraft.api.util.TextureUtil;
import org.spoutcraft.mod.addon.CommonAddonManager;
import org.spoutcraft.mod.handler.ClientTickHandlers;
import org.spoutcraft.mod.protocol.CommonConnectionHandler;
import org.spoutcraft.mod.protocol.codec.AddFileCodec;
import org.spoutcraft.mod.protocol.codec.AddPrefabCodec;
import org.spoutcraft.mod.protocol.codec.AddonListCodec;
import org.spoutcraft.mod.protocol.codec.DownloadLinkCodec;
import org.spoutcraft.mod.protocol.message.AddFileMessage;
import org.spoutcraft.mod.protocol.message.AddPrefabMessage;
import org.spoutcraft.mod.protocol.message.AddonListMessage;
import org.spoutcraft.mod.protocol.message.DownloadLinkMessage;
import org.spoutcraft.mod.resource.CommonFileSystem;

// TODO: Reflect GameRegistry, LanguageRegistry, NetworkRegistry and remove addon content on server leave
@Mod (modid = "Spoutcraft")
@NetworkMod (clientSideRequired = true, serverSideRequired = true)
public class SpoutcraftMod {
    private static CustomTabs customTabs;
    private final Spoutcraft game;
    private static CustomFont customFont = null;

    public SpoutcraftMod() {
        game = new Spoutcraft(FMLCommonHandler.instance().getEffectiveSide());
    }

    @EventHandler
    public void onInitialize(FMLInitializationEvent event) {
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

                // Setup CustomFont
                try {
                    customFont = (new CustomFont(Font.createFont(Font.TRUETYPE_FONT, SpoutcraftMod.class.getResourceAsStream("/assets/spoutcraft/fonts/" + ((CommonAddonManager) game.getAddonManager()).getInternalAddon().getDescription().getIdentifier() + "/ubuntu-regular.ttf")).deriveFont(36f)));
                } catch (Exception e) {
                    throw new RuntimeException("Could not load font", e);
                }
                
                final ClientTickHandlers handlers = new ClientTickHandlers(game);
                handlers.start();
                break;
            case SERVER:
                break;
            default:
                throw new RuntimeException("Spoutcraft is being ran on an invalid side!");
        }

        // Setup creative tab
        customTabs = new CustomTabs(game);

        // Setup protocol
        NetworkRegistry.instance().registerConnectionHandler(new CommonConnectionHandler(game));
        game.getNetwork().getProtocol().register(AddFileMessage.class, AddFileCodec.class);
        game.getNetwork().getProtocol().register(AddPrefabMessage.class, AddPrefabCodec.class);
        game.getNetwork().getProtocol().register(AddonListMessage.class, AddonListCodec.class);
        game.getNetwork().getProtocol().register(DownloadLinkMessage.class, DownloadLinkCodec.class);

        // Enable addons
        game.getAddonManager().enable();
    }

    public static CustomTabs getCustomTabs() {
        return customTabs;
    }

	public static CustomFont getCustomFont() {
		return customFont;
	}	
}
