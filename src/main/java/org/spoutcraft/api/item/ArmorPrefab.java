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

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.item.EnumArmorMaterial;

public class ArmorPrefab extends ItemPrefab {
    public static final int RENDER_INDEX;
    private static final long serialVersionUID = 1L;
    private final EnumArmorMaterial armorMaterial;
    private final ArmorType armorType;

    static {
        if (FMLCommonHandler.instance().getEffectiveSide().isClient()) {
            RENDER_INDEX = RenderingRegistry.addNewArmourRendererPrefix("Custom");
        } else {
            RENDER_INDEX = 0;
        }
    }

    public ArmorPrefab(String identifier, String displayName, boolean showInCreativeTab, ArmorType armorType, EnumArmorMaterial armorMaterial) {
        super(identifier, displayName, 1, showInCreativeTab);
        this.armorType = armorType;
        this.armorMaterial = armorMaterial;
    }

    public EnumArmorMaterial getToolMaterial() {
        return armorMaterial;
    }

    public ArmorType getArmorType() {
        return armorType;
    }

    public static enum ArmorType {
        HELMET(0),
        CHESTPLATE(1),
        LEGGINGS(2),
        BOOTS(3);
        private final int value;

        private ArmorType(int value) {
            this.value = value;
        }

        public int value() {
            return value;
        }
    }
}
