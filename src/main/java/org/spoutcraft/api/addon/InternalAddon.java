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
package org.spoutcraft.api.addon;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumToolMaterial;
import net.minecraftforge.common.EnumHelper;
import org.spoutcraft.api.LinkedPrefabRegistry;
import org.spoutcraft.api.Spoutcraft;
import org.spoutcraft.api.block.MovingPrefab;
import org.spoutcraft.api.item.ArmorPrefab;
import org.spoutcraft.api.item.AxePrefab;
import org.spoutcraft.api.item.PickaxePrefab;
import org.spoutcraft.api.item.SpadePrefab;
import org.spoutcraft.api.item.SwordPrefab;
import org.spoutcraft.api.material.MapIndex;
import org.spoutcraft.api.material.MaterialPrefab;
import org.spoutcraft.mod.SpoutcraftMod;
import org.spoutcraft.mod.item.special.SpoutcraftEmblem;
import org.spoutcraft.mod.item.special.VanillaEmblem;

@SuppressWarnings ("unchecked")
public final class InternalAddon extends Addon {
    public InternalAddon(Side side) {
        this.side = side;
        root = null;
        dataPath = null;
        loader = new AddonLoader(side);
        classLoader = new AddonClassLoader(getClassLoader(), loader);
        classLoader.setAddon(this);
        description = new AddonDescription("spoutcraft", "Spoutcraft", "1.0-SNAPSHOT", AddonMode.BOTH, null);
        logger = new AddonLogger(this);
    }

    @Override
    public void onEnable() {
        getLogger().info("Internal addon hooked");

        //Special
        final LinkedPrefabRegistry itemRegistry = Spoutcraft.getItemPrefabRegistry();
        itemRegistry.put(new SpoutcraftEmblem());
        itemRegistry.put(new VanillaEmblem());

        //--------------------------------Custom tools-----------------------------------
        itemRegistry.put(new SwordPrefab(this, "custom_blade", "Custom Blade", 1, true, EnumToolMaterial.EMERALD));
        itemRegistry.put(new PickaxePrefab(this, "custom_pickaxe", "Custom Pickaxe", 2, true, EnumToolMaterial.EMERALD));
        itemRegistry.put(new SpadePrefab(this, "custom_shovel", "Custom Shovel", 3, true, EnumToolMaterial.EMERALD));
        itemRegistry.put(new AxePrefab(this, "custom_axe", "Custom Axe", 4, true, EnumToolMaterial.EMERALD));

        //-------------------------------Custom Armor------------------------------------
        EnumArmorMaterial CUSTOM = EnumHelper.addArmorMaterial("CUSTOM", 100, new int[] {2, 3, 2, 2}, 15);
        itemRegistry.put(new ArmorPrefab(this, "custom_helmet", "Custom Helmet", true, 3, 0, CUSTOM));
        itemRegistry.put(new ArmorPrefab(this, "custom_chestplate", "Custom Chestplate", true, 3, 1, CUSTOM));
        itemRegistry.put(new ArmorPrefab(this, "custom_leggings", "Custom Leggings", true, 3, 2, CUSTOM));
        itemRegistry.put(new ArmorPrefab(this, "custom_boots", "Custom Boots", true, 3, 3, CUSTOM));

        final LinkedPrefabRegistry blockRegistry = Spoutcraft.getBlockPrefabRegistry();
        final MaterialPrefab testMaterial = new MaterialPrefab(this, "testMaterial", MapIndex.DIRT);

        //Everyone loves numbers :P
        blockRegistry.put(new MovingPrefab(this, "0b", "0 (Black)", testMaterial, 0.5f, 1, 255, true));
        blockRegistry.put(new MovingPrefab(this, "0w", "0 (White)", testMaterial, 0.5f, 1, 255, true));
        blockRegistry.put(new MovingPrefab(this, "1b", "1 (Black)", testMaterial, 0.5f, 1, 255, true));
        blockRegistry.put(new MovingPrefab(this, "1w", "1 (White)", testMaterial, 0.5f, 1, 255, true));
        blockRegistry.put(new MovingPrefab(this, "2b", "2 (Black)", testMaterial, 0.5f, 1, 255, true));
        blockRegistry.put(new MovingPrefab(this, "2w", "2 (White)", testMaterial, 0.5f, 1, 255, true));
        blockRegistry.put(new MovingPrefab(this, "3b", "3 (Black)", testMaterial, 0.5f, 1, 255, true));
        blockRegistry.put(new MovingPrefab(this, "3w", "3 (White)", testMaterial, 0.5f, 1, 255, true));
        blockRegistry.put(new MovingPrefab(this, "4b", "4 (Black)", testMaterial, 0.5f, 1, 255, true));
        blockRegistry.put(new MovingPrefab(this, "4w", "4 (White)", testMaterial, 0.5f, 1, 255, true));
        blockRegistry.put(new MovingPrefab(this, "5b", "5 (Black)", testMaterial, 0.5f, 1, 255, true));
        blockRegistry.put(new MovingPrefab(this, "5w", "5 (White)", testMaterial, 0.5f, 1, 255, true));
        blockRegistry.put(new MovingPrefab(this, "6b", "6 (Black)", testMaterial, 0.5f, 1, 255, true));
        blockRegistry.put(new MovingPrefab(this, "6w", "6 (White)", testMaterial, 0.5f, 1, 255, true));
        blockRegistry.put(new MovingPrefab(this, "7b", "7 (Black)", testMaterial, 0.5f, 1, 255, true));
        blockRegistry.put(new MovingPrefab(this, "7w", "7 (White)", testMaterial, 0.5f, 1, 255, true));
        blockRegistry.put(new MovingPrefab(this, "8b", "8 (Black)", testMaterial, 0.5f, 1, 255, true));
        blockRegistry.put(new MovingPrefab(this, "8w", "8 (White)", testMaterial, 0.5f, 1, 255, true));
        blockRegistry.put(new MovingPrefab(this, "9b", "9 (Black)", testMaterial, 0.5f, 1, 255, true));
        blockRegistry.put(new MovingPrefab(this, "9w", "9 (White)", testMaterial, 0.5f, 1, 255, true));
    }
}
