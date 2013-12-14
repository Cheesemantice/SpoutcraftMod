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
package org.spoutcraft.mod.item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.network.INetworkManager;
import org.spoutcraft.api.LinkedPrefabRegistry;
import org.spoutcraft.api.item.ArmorPrefab;
import org.spoutcraft.api.item.AxePrefab;
import org.spoutcraft.api.item.FoodPrefab;
import org.spoutcraft.api.item.ItemPrefab;
import org.spoutcraft.api.item.PickaxePrefab;
import org.spoutcraft.api.item.SpadePrefab;
import org.spoutcraft.api.item.SwordPrefab;
import org.spoutcraft.api.util.LanguageUtil;
import org.spoutcraft.mod.protocol.SpoutcraftPacket;
import org.spoutcraft.mod.protocol.message.AddPrefabMessage;

public class ItemPrefabRegistry implements LinkedPrefabRegistry<ItemPrefab, Item> {
    private static final ArrayList<ItemPrefab> REGISTRY = new ArrayList<>();
    private static final AtomicInteger ID_COUNTER = new AtomicInteger(0);
    //INTERNAL
    private static final HashMap<ItemPrefab, Item> PREFAB_BY_ITEM = new HashMap<>();
    private static final int ID_START = 200;

    @Override
    public ItemPrefab put(ItemPrefab prefab) {
        create(prefab);
        return prefab;
    }

    @Override
    public Item create(ItemPrefab prefab) {
        if (prefab == null) {
            throw new IllegalStateException("Attempt made to put null item prefab into registry!");
        }

        final int id = ID_START + ID_COUNTER.incrementAndGet();
        final Item item;
        if (prefab instanceof FoodPrefab) {
            item = new CustomFood(id, (FoodPrefab) prefab);
        } else if (prefab instanceof SwordPrefab) {
            item = new CustomSword(id, (SwordPrefab) prefab);
        } else if (prefab instanceof PickaxePrefab) {
            item = new CustomPickaxe(id, (PickaxePrefab) prefab);
        } else if (prefab instanceof SpadePrefab) {
            item = new CustomSpade(id, (SpadePrefab) prefab);
        } else if (prefab instanceof AxePrefab) {
            item = new CustomAxe(id, (AxePrefab) prefab);
        } else if (prefab instanceof ArmorPrefab) {
            item = new CustomArmor(id, (ArmorPrefab) prefab);
        } else {
            item = new CustomItem(id, prefab);
        }

        REGISTRY.add(prefab);
        PREFAB_BY_ITEM.put(prefab, item);

        GameRegistry.registerItem(item, prefab.getIdentifier(), "Spoutcraft");
        LanguageUtil.name(item, prefab.getDisplayName());
        return item;
    }

    @Override
    public ItemPrefab get(String identifier) {
        for (ItemPrefab prefab : REGISTRY) {
            if (prefab.getIdentifier().equals(identifier)) {
                return prefab;
            }
        }
        return null;
    }

    @Override
    public Item find(ItemPrefab prefab) {
        return prefab == null ? null : PREFAB_BY_ITEM.get(prefab);
    }

    @Override
    public Item find(String identifier) {
        if (identifier != null && !identifier.isEmpty()) {
            for (Map.Entry<ItemPrefab, Item> entry : PREFAB_BY_ITEM.entrySet()) {
                if (entry.getKey().getIdentifier().equals(identifier)) {
                    return entry.getValue();
                }
            }
        }
        return null;
    }

    /**
     * Syncs the entire block registry to the client
     *
     * @param network The connected network
     */
    public void sync(final INetworkManager network) {
        for (ItemPrefab prefab : REGISTRY) {
            network.addToSendQueue(new SpoutcraftPacket(new AddPrefabMessage(prefab)));
        }
    }
}
