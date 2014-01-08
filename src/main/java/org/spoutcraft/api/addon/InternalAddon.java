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

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;
import org.apache.logging.log4j.LogManager;
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
import org.spoutcraft.mod.item.special.SpoutEmblem;
import org.spoutcraft.mod.item.special.VanillaEmblem;

public final class InternalAddon extends Addon {
    @SideOnly (Side.CLIENT)
    private final KeyBinding bind = new KeyBinding("CustomGui", Keyboard.KEY_U, "Opens a test custom gui");

    public InternalAddon(Spoutcraft game) {
        this.game = game;
        root = null;
        dataPath = null;
        loader = new AddonLoader(game);
        classLoader = new AddonClassLoader(getClassLoader(), loader);
        classLoader.setAddon(this);
        description = new AddonDescription("internal", Spoutcraft.MOD_ID, "1.0-SNAPSHOT", AddonMode.BOTH, null);
        logger = LogManager.getLogger("Internal");
    }

    @Override
    public void onEnable() {
        final LinkedPrefabRegistry<BlockPrefab, Block> blockRegistry = game.getBlockPrefabRegistry();
        final LinkedPrefabRegistry<ItemPrefab, Item> itemRegistry = game.getItemPrefabRegistry();

        //Special
        itemRegistry.put(this, new SpoutEmblem());
        itemRegistry.put(this, new VanillaEmblem());

        //--------------------------------Custom Tools--------------------------------------------------------------------
        itemRegistry.put(this, new SwordPrefab("custom_blade", "Custom Blade", 1, true, Item.ToolMaterial.EMERALD));
        itemRegistry.put(this, new PickaxePrefab("custom_pickaxe", "Custom Pickaxe", 2, true, Item.ToolMaterial.EMERALD));
        itemRegistry.put(this, new SpadePrefab("custom_shovel", "Custom Shovel", 3, true, Item.ToolMaterial.EMERALD));
        itemRegistry.put(this, new AxePrefab("custom_axe", "Custom Axe", 4, true, Item.ToolMaterial.EMERALD));

        //-------------------------------Custom Armor---------------------------------------------------------------------
        ItemArmor.ArmorMaterial customArmorMaterial = EnumHelper.addArmorMaterial("Custom", 100, new int[] {2, 3, 2, 2}, 15);
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
            ClientRegistry.registerKeyBinding(bind);
        }
    }

    @SubscribeEvent
    public void onKeyPress(InputEvent.KeyInputEvent event) {
        //TODO Ask cpw or complete KeyInputEvent for them
    }
}
