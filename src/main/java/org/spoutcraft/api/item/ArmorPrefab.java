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
package org.spoutcraft.api.item;

import net.minecraft.item.EnumArmorMaterial;
import org.spoutcraft.api.addon.Addon;

public class ArmorPrefab extends ItemPrefab {
    private static final long serialVersionUID = 1L;
    private final EnumArmorMaterial armorMaterial;
    private final int renderIndex;
    private final int armorType;

    /**
     * @param armorType 0 is helmet, 1 is plate, 2 is legs and 3 is boots
     */
    public ArmorPrefab(Addon addon, String identifier, String displayName, boolean showInCreativeTab, int renderIndex, int armorType, EnumArmorMaterial armorMaterial) {
        super(addon, identifier, displayName, 1, showInCreativeTab);
        this.armorMaterial = armorMaterial;
        this.renderIndex = renderIndex;
        this.armorType = armorType;
    }

    public EnumArmorMaterial getToolMaterial() {
        return armorMaterial;
    }

    public int getRenderIndex() {
        return renderIndex;
    }

    public int getArmorType() {
        return armorType;
    }
}
