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
package org.spoutcraft.api;

import cpw.mods.fml.relauncher.Side;
import org.spoutcraft.api.addon.AddonManager;
import org.spoutcraft.api.block.BlockPrefab;
import org.spoutcraft.api.item.ItemPrefab;
import org.spoutcraft.api.logger.SpoutcraftLogger;
import org.spoutcraft.api.protocol.MessageDispatcher;
import org.spoutcraft.api.resource.FileSystem;
import org.spoutcraft.mod.addon.ClientAddonManager;
import org.spoutcraft.mod.addon.CommonAddonManager;
import org.spoutcraft.mod.block.BlockPrefabRegistry;
import org.spoutcraft.mod.item.ItemPrefabRegistry;
import org.spoutcraft.mod.resource.ClientFileSystem;
import org.spoutcraft.mod.resource.CommonFileSystem;

/**
 * The game class that has access to various components that culminate into the Spoutcraft experience.
 */
public final class Spoutcraft {
    public static final String MOD_ID = "Spoutcraft";
    private final Side side;
    private final SpoutcraftLogger logger;
    private final AddonManager addonManager;
    private final FileSystem fileSystem;
    private final MessageDispatcher network;
    private final LinkedPrefabRegistry<? extends BlockPrefab, ?> blockPrefabRegistry;
    private final LinkedPrefabRegistry<? extends ItemPrefab, ?> itemPrefabRegistry;

    public Spoutcraft(Side side) {
        this.side = side;
        logger = new SpoutcraftLogger();

        if (getSide().isServer()) {
            addonManager = new CommonAddonManager(this);
            fileSystem = new CommonFileSystem();
        } else {
            addonManager = new ClientAddonManager(this);
            fileSystem = new ClientFileSystem();
        }
        network = new MessageDispatcher(this);
        blockPrefabRegistry = new BlockPrefabRegistry();
        itemPrefabRegistry = new ItemPrefabRegistry();
    }

    public Side getSide() {
        return side;
    }

    public SpoutcraftLogger getLogger() {
        return logger;
    }

    public AddonManager getAddonManager() {
        return addonManager;
    }

    public FileSystem getFileSystem() {
        return fileSystem;
    }

    public MessageDispatcher getNetwork() {
        return network;
    }

    public LinkedPrefabRegistry<? extends BlockPrefab, ?> getBlockPrefabRegistry() {
        return blockPrefabRegistry;
    }

    public LinkedPrefabRegistry<? extends ItemPrefab, ?> getItemPrefabRegistry() {
        return itemPrefabRegistry;
    }
}
