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
package org.spoutcraft.mod.block;

import java.util.HashMap;
import java.util.Map;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import org.spoutcraft.api.LinkedPrefabRegistry;
import org.spoutcraft.api.addon.Addon;
import org.spoutcraft.api.block.BlockPrefab;
import org.spoutcraft.api.block.MovingPrefab;
import org.spoutcraft.api.util.LanguageUtil;
import org.spoutcraft.mod.SpoutcraftMod;

public class BlockPrefabRegistry implements LinkedPrefabRegistry<BlockPrefab, Block> {
    private static final int ID_START = 2000;
    private static int ID_COUNTER = 0;
    //INTERNAL
    private static final Map<Addon, Map<BlockPrefab, Block>> ADDON_BLOCK_PREFAB_INSTANCE_REGISTRY = new HashMap<>();

    @Override
    public BlockPrefab put(Addon addon, BlockPrefab prefab) {
        create(addon, prefab);
        return prefab;
    }

    @Override
    public Block create(Addon addon, BlockPrefab prefab) {
        if (addon == null) {
            throw new IllegalStateException("Attempt to create block with null addon!");
        }

        if (prefab == null) {
            throw new IllegalStateException("Attempt to create block with null block prefab!");
        }

        Map<BlockPrefab, Block> addonRegistry = ADDON_BLOCK_PREFAB_INSTANCE_REGISTRY.get(addon);
        if (addonRegistry == null) {
            addonRegistry = ADDON_BLOCK_PREFAB_INSTANCE_REGISTRY.put(addon, new HashMap<BlockPrefab, Block>());
        }

        Block block = addonRegistry.get(prefab);
        if (block != null) {
            return block;
        }

        final int id = ID_START + ID_COUNTER++;
        if (prefab instanceof MovingPrefab) {
            block = new CustomMovingBlock(id, addon, (MovingPrefab) prefab);
        } else {
            block = new CustomBlock(id, addon, prefab);
        }

        addonRegistry.put(prefab, block);
        GameRegistry.registerBlock(block, ItemBlock.class, prefab.getIdentifier(), SpoutcraftMod.MOD_ID + addon.getDescription().getIdentifier());
        LanguageUtil.name(block, prefab.getDisplayName());
        return block;
    }

    @Override
    public BlockPrefab get(Addon addon, String identifier) {
        if (addon == null) {
            throw new IllegalStateException("Attempt to get block prefab with null addon!");
        }

        if (identifier == null || identifier.isEmpty()) {
            throw new IllegalStateException("Attempt to get block prefab with empty or null identifier!");
        }
        Map<BlockPrefab, Block> addonRegistry = ADDON_BLOCK_PREFAB_INSTANCE_REGISTRY.get(addon);
        if (addonRegistry != null && !addonRegistry.isEmpty()) {
            for (BlockPrefab prefab : addonRegistry.keySet()) {
                if (prefab.getIdentifier().equalsIgnoreCase(identifier)) {
                    return prefab;
                }
            }
        }
        return null;
    }

    @Override
    public Block find(Addon addon, BlockPrefab prefab) {
        if (addon == null) {
            throw new IllegalStateException("Attempt to find block with null addon!");
        }

        if (prefab == null) {
            throw new IllegalStateException("Attempt to find block with null block prefab!");
        }
        Map<BlockPrefab, Block> addonRegistry = ADDON_BLOCK_PREFAB_INSTANCE_REGISTRY.get(addon);
        if (addonRegistry != null && !addonRegistry.isEmpty()) {
            return addonRegistry.get(prefab);
        }
        return null;
    }

    @Override
    public Block find(Addon addon, String identifier) {
        if (addon == null) {
            throw new IllegalStateException("Attempt to find block with null addon!");
        }

        if (identifier == null || identifier.isEmpty()) {
            throw new IllegalStateException("Attempt to find block with null or empty identifier!");
        }
        Map<BlockPrefab, Block> addonRegistry = ADDON_BLOCK_PREFAB_INSTANCE_REGISTRY.get(addon);
        if (addonRegistry != null && !addonRegistry.isEmpty()) {
            for (Map.Entry<BlockPrefab, Block> entry : addonRegistry.entrySet()) {
                if (entry.getKey().getIdentifier().equalsIgnoreCase(identifier)) {
                    return entry.getValue();
                }
            }
        }
        return null;
    }
}
