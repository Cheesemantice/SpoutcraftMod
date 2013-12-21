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
package org.spoutcraft.api.enchantment;

import net.minecraft.enchantment.EnumEnchantmentType;
import org.spoutcraft.api.Prefab;
import org.spoutcraft.api.addon.Addon;

public class EnchantmentPrefab extends Prefab {
    private static final long serialVersionUID = -7118572071238928874L;
    private final EnumEnchantmentType enchantmentType;
    private final String displayName;
    private final int weight;

    public EnchantmentPrefab(Addon addon, String identifier, String displayName, int weight, EnumEnchantmentType enchantmentType) {
        super(addon, identifier);
        this.displayName = displayName;
        this.weight = weight;
        this.enchantmentType = enchantmentType;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getWeight() {
        return weight;
    }

    public EnumEnchantmentType getEnchantmentType() {
        return enchantmentType;
    }

    @Override
    public String toString() {
        final String NEW_LINE = System.getProperty("line.separator");
        final String parent = super.toString();
        final StringBuilder builder = new StringBuilder(parent.substring(0, parent.length() - 1) + NEW_LINE);
        builder
                .append(" Enchantment Type: " + enchantmentType + NEW_LINE)
                .append("}");
        return builder.toString();
    }
}
