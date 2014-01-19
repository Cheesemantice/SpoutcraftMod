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
package org.spoutcraft.mod.item;

import java.util.HashMap;
import java.util.Map;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import org.spoutcraft.api.LinkedPrefabRegistry;
import org.spoutcraft.api.Spoutcraft;
import org.spoutcraft.api.addon.Addon;
import org.spoutcraft.api.item.ArmorPrefab;
import org.spoutcraft.api.item.AxePrefab;
import org.spoutcraft.api.item.FoodPrefab;
import org.spoutcraft.api.item.ItemPrefab;
import org.spoutcraft.api.item.PickaxePrefab;
import org.spoutcraft.api.item.SpadePrefab;
import org.spoutcraft.api.item.SwordPrefab;
import org.spoutcraft.api.util.LanguageUtil;

public class ItemPrefabRegistry implements LinkedPrefabRegistry<ItemPrefab, Item> {
    private static final int ID_START = 3000;
    private static int ID_COUNTER = 0;
    private static final Map<Addon, Map<ItemPrefab, Item>> ADDON_ITEM_PREFAB_INSTANCE_REGISTRY = new HashMap<>();

    @Override
    public ItemPrefab put(Addon addon, ItemPrefab prefab) {
        create(addon, prefab);
        return prefab;
    }

    @Override
    public Item create(Addon addon, ItemPrefab prefab) {
        if (addon == null) {
            throw new IllegalStateException("Attempt to create item with null addon!");
        }

        if (prefab == null) {
            throw new IllegalStateException("Attempt to create block with null block prefab!");
        }

        Map<ItemPrefab, Item> addonRegistry = ADDON_ITEM_PREFAB_INSTANCE_REGISTRY.get(addon);
        if (addonRegistry == null) {
            addonRegistry = new HashMap<>();
            ADDON_ITEM_PREFAB_INSTANCE_REGISTRY.put(addon, addonRegistry);
        }

        Item item = addonRegistry.get(prefab);
        if (item != null) {
            return item;
        }

        final int id = ID_START + ID_COUNTER++;
        if (prefab instanceof FoodPrefab) {
            item = new CustomFood(id, addon, (FoodPrefab) prefab);
        } else if (prefab instanceof SwordPrefab) {
            item = new CustomSword(id, addon, (SwordPrefab) prefab);
        } else if (prefab instanceof PickaxePrefab) {
            item = new CustomPickaxe(id, addon, (PickaxePrefab) prefab);
        } else if (prefab instanceof SpadePrefab) {
            item = new CustomSpade(id, addon, (SpadePrefab) prefab);
        } else if (prefab instanceof AxePrefab) {
            item = new CustomAxe(id, addon, (AxePrefab) prefab);
        } else if (prefab instanceof ArmorPrefab) {
            item = new CustomArmor(id, addon, (ArmorPrefab) prefab);
        } else {
            item = new CustomItem(id, addon, prefab);
        }

        addonRegistry.put(prefab, item);
        GameRegistry.registerItem(item, prefab.getIdentifier(), Spoutcraft.MOD_ID);
        LanguageUtil.name(item, prefab.getDisplayName());
        return item;
    }

    @Override
    public ItemPrefab get(Addon addon, String identifier) {
        if (addon == null) {
            throw new IllegalStateException("Attempt to get item prefab with null addon!");
        }

        if (identifier == null || identifier.isEmpty()) {
            throw new IllegalStateException("Attempt to get item prefab with empty or null identifier!");
        }
        Map<ItemPrefab, Item> addonRegistry = ADDON_ITEM_PREFAB_INSTANCE_REGISTRY.get(addon);
        if (addonRegistry != null && !addonRegistry.isEmpty()) {
            for (ItemPrefab prefab : addonRegistry.keySet()) {
                if (prefab.getIdentifier().equalsIgnoreCase(identifier)) {
                    return prefab;
                }
            }
        }
        return null;
    }

    @Override
    public Item find(Addon addon, ItemPrefab prefab) {
        if (addon == null) {
            throw new IllegalStateException("Attempt to find item with null addon!");
        }

        if (prefab == null) {
            throw new IllegalStateException("Attempt to find item with null item prefab!");
        }
        Map<ItemPrefab, Item> addonRegistry = ADDON_ITEM_PREFAB_INSTANCE_REGISTRY.get(addon);
        if (addonRegistry != null && !addonRegistry.isEmpty()) {
            return addonRegistry.get(prefab);
        }
        return null;
    }

    @Override
    public Item find(Addon addon, String identifier) {
        if (addon == null) {
            throw new IllegalStateException("Attempt to find block with null addon!");
        }

        if (identifier == null || identifier.isEmpty()) {
            throw new IllegalStateException("Attempt to find block with null or empty identifier!");
        }
        Map<ItemPrefab, Item> addonRegistry = ADDON_ITEM_PREFAB_INSTANCE_REGISTRY.get(addon);
        if (addonRegistry != null && !addonRegistry.isEmpty()) {
            for (Map.Entry<ItemPrefab, Item> entry : addonRegistry.entrySet()) {
                if (entry.getKey().getIdentifier().equalsIgnoreCase(identifier)) {
                    return entry.getValue();
                }
            }
        }
        return null;
    }
}
