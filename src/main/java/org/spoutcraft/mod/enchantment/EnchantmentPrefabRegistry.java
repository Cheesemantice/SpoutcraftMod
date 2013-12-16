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
package org.spoutcraft.mod.enchantment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.network.INetworkManager;

import org.spoutcraft.api.LinkedPrefabRegistry;
import org.spoutcraft.api.enchantment.EnchantmentPrefab;
import org.spoutcraft.api.protocol.MessageDispatcher;
import org.spoutcraft.api.util.LanguageUtil;
import org.spoutcraft.mod.protocol.message.AddPrefabMessage;

public class EnchantmentPrefabRegistry implements LinkedPrefabRegistry<EnchantmentPrefab, Enchantment> {
    private static final ArrayList<EnchantmentPrefab> REGISTRY = new ArrayList<>();
    private static final AtomicInteger ID_COUNTER = new AtomicInteger(0);
    //INTERNAL
    private static final HashMap<EnchantmentPrefab, Enchantment> PREFAB_BY_ENCHANTMENT = new HashMap<>();
    private static final int ID_START = 200;

    @Override
    public EnchantmentPrefab put(EnchantmentPrefab prefab) {
        create(prefab);
        return prefab;
    }

    @Override
    public Enchantment create(EnchantmentPrefab prefab) {
        if (prefab == null) {
            throw new IllegalStateException("Attempt made to put null enchantment prefab into registry!");
        }

        final int id = ID_START + ID_COUNTER.incrementAndGet();
        final Enchantment enchantment = new CustomEnchantment(id, prefab);

        REGISTRY.add(prefab);
        PREFAB_BY_ENCHANTMENT.put(prefab, enchantment);
        
        LanguageUtil.add("enchantment." + prefab.getIdentifier(), prefab.getDisplayName());
        
        return enchantment;
    }

    @Override
    public EnchantmentPrefab get(String identifier) {
        for (EnchantmentPrefab prefab : REGISTRY) {
            if (prefab.getIdentifier().equals(identifier)) {
                return prefab;
            }
        }
        return null;
    }

    @Override
    public Enchantment find(EnchantmentPrefab prefab) {
        return prefab == null ? null : PREFAB_BY_ENCHANTMENT.get(prefab);
    }

    @Override
    public Enchantment find(String identifier) {
        if (identifier != null && !identifier.isEmpty()) {
            for (Map.Entry<EnchantmentPrefab, Enchantment> entry : PREFAB_BY_ENCHANTMENT.entrySet()) {
                if (entry.getKey().getIdentifier().equals(identifier)) {
                    return entry.getValue();
                }
            }
        }
        return null;
    }

    /**
     * Syncs the entire enchantment registry to the client
     *
     * @param network The connected network
     */
    public void sync(final INetworkManager network) {
        for (EnchantmentPrefab prefab : REGISTRY) {
            network.addToSendQueue(MessageDispatcher.create(new AddPrefabMessage(prefab)));
        }
    }
}
