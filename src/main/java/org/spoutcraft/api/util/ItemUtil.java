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
package org.spoutcraft.api.util;

import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;

public class ItemUtil {
    public static String getItemName(Item item) {
        return getItemName(item.getUnlocalizedName());
    }

    public static String getItemName(String identifier) {
        return identifier.replace("item.spoutcraft:", "");
    }

    public static String getArmorName(ItemArmor itemArmor) {
        String identifier = getItemName(itemArmor);
        return getArmorName(identifier, itemArmor.armorType);
    }

    public static String getArmorName(String identifier, int armorType) {
        if (identifier.contains("spoutcraft")) {
            identifier = getItemName(identifier);
        }
        switch (armorType) {
            case 0:
                identifier = identifier.replace("_helmet", "");
                break;
            case 1:
                identifier = identifier.replace("_chestplate", "");
                break;
            case 2:
                identifier = identifier.replace("_leggings", "");
                break;
            case 3:
                identifier = identifier.replace("_boots", "");
                break;
            default:
                identifier = identifier.replace("_helmet", "");
                break;
        }

        return identifier;
    }
}