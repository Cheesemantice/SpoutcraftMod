/**
 * This file is a part of SpoutcraftMod.
 *
 * Copyright (c) 2013 SpoutcraftDev <http://spoutcraft.org>
 * SpoutcraftMod is licensed under the MIT License.
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
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.item.EnumToolMaterial;
import org.lwjgl.input.*;
import org.spoutcraft.api.LinkedPrefabRegistry;
import org.spoutcraft.api.Spoutcraft;
import org.spoutcraft.api.block.MovingPrefab;
import org.spoutcraft.api.item.AxePrefab;
import org.spoutcraft.api.item.PickaxePrefab;
import org.spoutcraft.api.item.SpadePrefab;
import org.spoutcraft.api.item.SwordPrefab;
import org.spoutcraft.api.material.MapIndex;
import org.spoutcraft.api.material.MaterialPrefab;
import org.spoutcraft.mod.SpoutcraftMod;
import org.spoutcraft.mod.gui.builtin.SpoutcraftTestGui;
import org.spoutcraft.mod.item.special.SpoutcraftEmblem;
import org.spoutcraft.mod.item.special.VanillaEmblem;

@SuppressWarnings ("unchecked")
public final class SpoutcraftAddon extends Addon {
    public SpoutcraftAddon(Side side) {
        this.side = side;
        root = null;
        dataPath = null;
        loader = new AddonLoader(side);
        classLoader = new AddonClassLoader(SpoutcraftMod.class.getClassLoader(), loader);
        classLoader.setAddon(this);
        description = new AddonDescription("spoutcraft", "Spoutcraft", Spoutcraft.VERSION, AddonMode.BOTH, null);
        logger = new AddonLogger(this);
    }

    @SuppressWarnings ("rawtypes")
    @Override
    public void onEnable() {
        getLogger().info("Internal addon hooked");

        //Special
        final LinkedPrefabRegistry itemRegistry = Spoutcraft.getItemPrefabRegistry();
        itemRegistry.put(new SpoutcraftEmblem());
        itemRegistry.put(new VanillaEmblem());
        itemRegistry.put(new SwordPrefab("custom_blade", "Custom Blade", 1, true, EnumToolMaterial.EMERALD));
        itemRegistry.put(new PickaxePrefab("custom_pickaxe", "Custom Pickaxe", 2, true, EnumToolMaterial.EMERALD));
        itemRegistry.put(new SpadePrefab("custom_shovel", "Custom Shovel", 3, true, EnumToolMaterial.EMERALD));
        itemRegistry.put(new AxePrefab("custom_axe", "Custom Axe", 4, true, EnumToolMaterial.EMERALD));

        final LinkedPrefabRegistry blockRegistry = Spoutcraft.getBlockPrefabRegistry();
        final MaterialPrefab testMaterial = new MaterialPrefab("testMaterial", MapIndex.DIRT);

        //Everyone loves numbers :P
        blockRegistry.put(new MovingPrefab("0b", "0 (Black)", testMaterial, 0.5f, 1, 255, true));
        blockRegistry.put(new MovingPrefab("0w", "0 (White)", testMaterial, 0.5f, 1, 255, true));
        blockRegistry.put(new MovingPrefab("1b", "1 (Black)", testMaterial, 0.5f, 1, 255, true));
        blockRegistry.put(new MovingPrefab("1w", "1 (White)", testMaterial, 0.5f, 1, 255, true));
        blockRegistry.put(new MovingPrefab("2b", "2 (Black)", testMaterial, 0.5f, 1, 255, true));
        blockRegistry.put(new MovingPrefab("2w", "2 (White)", testMaterial, 0.5f, 1, 255, true));
        blockRegistry.put(new MovingPrefab("3b", "3 (Black)", testMaterial, 0.5f, 1, 255, true));
        blockRegistry.put(new MovingPrefab("3w", "3 (White)", testMaterial, 0.5f, 1, 255, true));
        blockRegistry.put(new MovingPrefab("4b", "4 (Black)", testMaterial, 0.5f, 1, 255, true));
        blockRegistry.put(new MovingPrefab("4w", "4 (White)", testMaterial, 0.5f, 1, 255, true));
        blockRegistry.put(new MovingPrefab("5b", "5 (Black)", testMaterial, 0.5f, 1, 255, true));
        blockRegistry.put(new MovingPrefab("5w", "5 (White)", testMaterial, 0.5f, 1, 255, true));
        blockRegistry.put(new MovingPrefab("6b", "6 (Black)", testMaterial, 0.5f, 1, 255, true));
        blockRegistry.put(new MovingPrefab("6w", "6 (White)", testMaterial, 0.5f, 1, 255, true));
        blockRegistry.put(new MovingPrefab("7b", "7 (Black)", testMaterial, 0.5f, 1, 255, true));
        blockRegistry.put(new MovingPrefab("7w", "7 (White)", testMaterial, 0.5f, 1, 255, true));
        blockRegistry.put(new MovingPrefab("8b", "8 (Black)", testMaterial, 0.5f, 1, 255, true));
        blockRegistry.put(new MovingPrefab("8w", "8 (White)", testMaterial, 0.5f, 1, 255, true));
        blockRegistry.put(new MovingPrefab("9b", "9 (Black)", testMaterial, 0.5f, 1, 255, true));
        blockRegistry.put(new MovingPrefab("9w", "9 (White)", testMaterial, 0.5f, 1, 255, true));

        if (side.isClient()) {
            KeyBinding guiBind = new KeyBinding("SpoutGuiBind", Keyboard.KEY_U);

            KeyBindingRegistry.registerKeyBinding(new KeyBindingRegistry.KeyHandler(new KeyBinding[] {guiBind}, new boolean[] {false}) {
                private EnumSet<TickType> ticks = EnumSet.of(TickType.CLIENT);

                @Override
                public String getLabel() {
                    return "Spoutcraft Key Handler";
                }

                @Override
                public void keyDown(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd, boolean isRepeat) {
                }

                @Override
                public void keyUp(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd) {
                    if (kb.keyDescription.equals("SpoutGuiBind") && Minecraft.getMinecraft().currentScreen == null) {
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
