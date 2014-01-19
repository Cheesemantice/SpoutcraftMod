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
package org.spoutcraft.api.addon;

import java.util.EnumSet;

import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.common.TickType;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraftforge.common.EnumHelper;
import org.lwjgl.input.*;
import org.spoutcraft.api.LinkedPrefabRegistry;
import org.spoutcraft.api.Spoutcraft;
import org.spoutcraft.api.block.BlockPrefab;
import org.spoutcraft.api.block.MovingPrefab;
import org.spoutcraft.api.item.ArmorPrefab;
import org.spoutcraft.api.item.AxePrefab;
import org.spoutcraft.api.item.ItemPrefab;
import org.spoutcraft.api.item.PickaxePrefab;
import org.spoutcraft.api.item.SpadePrefab;
import org.spoutcraft.api.item.SwordPrefab;
import org.spoutcraft.mod.gui.builtin.SpoutcraftTestGui;
import org.spoutcraft.mod.item.special.SpoutEmblem;
import org.spoutcraft.mod.item.special.VanillaEmblem;

public final class InternalAddon extends Addon {
    public InternalAddon(Spoutcraft game) {
        this.game = game;
        root = null;
        dataPath = null;
        loader = new AddonLoader(game);
        classLoader = new AddonClassLoader(getClassLoader(), loader);
        classLoader.setAddon(this);
        description = new AddonDescription("internal", Spoutcraft.MOD_ID, "1.0-SNAPSHOT", AddonMode.BOTH, null);
        logger = new AddonLogger(game.getLogger(), this);
    }

    @Override
    public void onEnable() {
        final LinkedPrefabRegistry<BlockPrefab, Block> blockRegistry = game.getBlockPrefabRegistry();
        final LinkedPrefabRegistry<ItemPrefab, Item> itemRegistry = game.getItemPrefabRegistry();

        //Special
        itemRegistry.put(this, new SpoutEmblem());
        itemRegistry.put(this, new VanillaEmblem());

        //--------------------------------Custom Tools--------------------------------------------------------------------
        itemRegistry.put(this, new SwordPrefab("custom_blade", "Custom Blade", 1, true, EnumToolMaterial.EMERALD));
        itemRegistry.put(this, new PickaxePrefab("custom_pickaxe", "Custom Pickaxe", 2, true, EnumToolMaterial.EMERALD));
        itemRegistry.put(this, new SpadePrefab("custom_shovel", "Custom Shovel", 3, true, EnumToolMaterial.EMERALD));
        itemRegistry.put(this, new AxePrefab("custom_axe", "Custom Axe", 4, true, EnumToolMaterial.EMERALD));

        //-------------------------------Custom Armor---------------------------------------------------------------------
        EnumArmorMaterial customArmorMaterial = EnumHelper.addArmorMaterial("Custom", 100, new int[] {2, 3, 2, 2}, 15);
        itemRegistry.put(this, new ArmorPrefab("custom_helmet", "Custom Helmet", true, ArmorPrefab.ArmorType.HELMET, customArmorMaterial));
        itemRegistry.put(this, new ArmorPrefab("custom_chestplate", "Custom Chestplate", true, ArmorPrefab.ArmorType.CHESTPLATE, customArmorMaterial));
        itemRegistry.put(this, new ArmorPrefab("custom_leggings", "Custom Leggings", true, ArmorPrefab.ArmorType.LEGGINGS, customArmorMaterial));
        itemRegistry.put(this, new ArmorPrefab("custom_boots", "Custom Boots", true, ArmorPrefab.ArmorType.BOOTS, customArmorMaterial));

        //Everyone loves numbers :P
        blockRegistry.put(this, new MovingPrefab("0b", "0 (Black)", 0.5f, 1, 255, true));
        blockRegistry.put(this, new MovingPrefab("0w", "0 (White)", 0.5f, 1, 255, true));
        blockRegistry.put(this, new MovingPrefab("1b", "1 (Black)", 0.5f, 1, 255, true));
        blockRegistry.put(this, new MovingPrefab("1w", "1 (White)", 0.5f, 1, 255, true));
        blockRegistry.put(this, new MovingPrefab("2b", "2 (Black)", 0.5f, 1, 255, true));
        blockRegistry.put(this, new MovingPrefab("2w", "2 (White)", 0.5f, 1, 255, true));
        blockRegistry.put(this, new MovingPrefab("3b", "3 (Black)", 0.5f, 1, 255, true));
        blockRegistry.put(this, new MovingPrefab("3w", "3 (White)", 0.5f, 1, 255, true));
        blockRegistry.put(this, new MovingPrefab("4b", "4 (Black)", 0.5f, 1, 255, true));
        blockRegistry.put(this, new MovingPrefab("4w", "4 (White)", 0.5f, 1, 255, true));
        blockRegistry.put(this, new MovingPrefab("5b", "5 (Black)", 0.5f, 1, 255, true));
        blockRegistry.put(this, new MovingPrefab("5w", "5 (White)", 0.5f, 1, 255, true));
        blockRegistry.put(this, new MovingPrefab("6b", "6 (Black)", 0.5f, 1, 255, true));
        blockRegistry.put(this, new MovingPrefab("6w", "6 (White)", 0.5f, 1, 255, true));
        blockRegistry.put(this, new MovingPrefab("7b", "7 (Black)", 0.5f, 1, 255, true));
        blockRegistry.put(this, new MovingPrefab("7w", "7 (White)", 0.5f, 1, 255, true));
        blockRegistry.put(this, new MovingPrefab("8b", "8 (Black)", 0.5f, 1, 255, true));
        blockRegistry.put(this, new MovingPrefab("8w", "8 (White)", 0.5f, 1, 255, true));
        blockRegistry.put(this, new MovingPrefab("9b", "9 (Black)", 0.5f, 1, 255, true));
        blockRegistry.put(this, new MovingPrefab("9w", "9 (White)", 0.5f, 1, 255, true));

        if (game.getSide().isClient()) {
            KeyBinding guiBind = new KeyBinding("CustomGui", Keyboard.KEY_U);

            KeyBindingRegistry.registerKeyBinding(new KeyBindingRegistry.KeyHandler(new KeyBinding[] {guiBind}, new boolean[] {false}) {
                private EnumSet<TickType> ticks = EnumSet.of(TickType.CLIENT);

                @Override
                public String getLabel() {
                    return "Spoutcraft - Custom GUI Test Key Handler";
                }

                @Override
                public void keyDown(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd, boolean isRepeat) {
                }

                @Override
                public void keyUp(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd) {
                    if (kb.keyDescription.equals("CustomGui") && Minecraft.getMinecraft().currentScreen == null) {
                        Minecraft.getMinecraft().displayGuiScreen(new SpoutcraftTestGui());
                    }
                }

                @Override
                public EnumSet<TickType> ticks() {
                    return ticks;
                }
            });
        }
    }
}
