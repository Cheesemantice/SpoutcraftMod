/**
 * This file is a part of Spoutcraft.
 *
 * Copyright (c) 2013 SpoutcraftDev <http://spoutcraft.org>
 * Spoutcraft is licensed under the MIT License.
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
import org.spoutcraft.api.LinkedPrefabRegistry;
import org.spoutcraft.api.Spoutcraft;
import org.spoutcraft.api.block.MovingPrefab;
import org.spoutcraft.api.material.MapIndex;
import org.spoutcraft.api.material.MaterialPrefab;
import org.spoutcraft.mod.SpoutcraftMod;
import org.spoutcraft.mod.item.special.SpoutcraftEmblem;
import org.spoutcraft.mod.item.special.VanillaEmblem;

@SuppressWarnings("unchecked")
public final class SpoutcraftAddon extends Addon {
    public SpoutcraftAddon(Side side) {
        this.side = side;
        root = null;
        dataPath = null;
        loader = new AddonLoader(side);
        classLoader = new AddonClassLoader(SpoutcraftMod.class.getClassLoader(), loader);
        classLoader.setAddon(this);
        prefab = new AddonPrefab("spoutcraft", "Spoutcraft", Spoutcraft.VERSION, AddonMode.BOTH, null);
        logger = new AddonLogger(this);
    }

    @Override
    public void onEnable() {
        getLogger().info("Internal addon hooked");

        //Special
        // Spoutcraft.getItemPrefabRegistry().put(new SpoutcraftEmblem());
        Spoutcraft.getItemPrefabRegistry().put(new VanillaEmblem());

        //TODO Move test code to an accompanying addon
        final LinkedPrefabRegistry registry = Spoutcraft.getBlockPrefabRegistry();
        registry.put(new MovingPrefab("0b", "0 (Black)", new MaterialPrefab("testmaterial", MapIndex.DIRT), 0.5f, true));
        registry.put(new MovingPrefab("0w", "0 (White)", new MaterialPrefab("testmaterial", MapIndex.DIRT), 0.5f, true));
        registry.put(new MovingPrefab("1b", "1 (Black)", new MaterialPrefab("testmaterial", MapIndex.DIRT), 0.5f, true));
        registry.put(new MovingPrefab("1w", "1 (White)", new MaterialPrefab("testmaterial", MapIndex.DIRT), 0.5f, true));
        registry.put(new MovingPrefab("2b", "2 (Black)", new MaterialPrefab("testmaterial", MapIndex.DIRT), 0.5f, true));
        registry.put(new MovingPrefab("2w", "2 (White)", new MaterialPrefab("testmaterial", MapIndex.DIRT), 0.5f, true));
        registry.put(new MovingPrefab("3b", "3 (Black)", new MaterialPrefab("testmaterial", MapIndex.DIRT), 0.5f, true));
        registry.put(new MovingPrefab("3w", "3 (White)", new MaterialPrefab("testmaterial", MapIndex.DIRT), 0.5f, true));
        registry.put(new MovingPrefab("4b", "4 (Black)", new MaterialPrefab("testmaterial", MapIndex.DIRT), 0.5f, true));
        registry.put(new MovingPrefab("4w", "4 (White)", new MaterialPrefab("testmaterial", MapIndex.DIRT), 0.5f, true));
        registry.put(new MovingPrefab("5b", "5 (Black)", new MaterialPrefab("testmaterial", MapIndex.DIRT), 0.5f, true));
        registry.put(new MovingPrefab("5w", "5 (White)", new MaterialPrefab("testmaterial", MapIndex.DIRT), 0.5f, true));
        registry.put(new MovingPrefab("6b", "6 (Black)", new MaterialPrefab("testmaterial", MapIndex.DIRT), 0.5f, true));
        registry.put(new MovingPrefab("6w", "6 (White)", new MaterialPrefab("testmaterial", MapIndex.DIRT), 0.5f, true));
        registry.put(new MovingPrefab("7b", "7 (Black)", new MaterialPrefab("testmaterial", MapIndex.DIRT), 0.5f, true));
        registry.put(new MovingPrefab("7w", "7 (White)", new MaterialPrefab("testmaterial", MapIndex.DIRT), 0.5f, true));
        registry.put(new MovingPrefab("8b", "8 (Black)", new MaterialPrefab("testmaterial", MapIndex.DIRT), 0.5f, true));
        registry.put(new MovingPrefab("8w", "8 (White)", new MaterialPrefab("testmaterial", MapIndex.DIRT), 0.5f, true));
        registry.put(new MovingPrefab("9b", "9 (Black)", new MaterialPrefab("testmaterial", MapIndex.DIRT), 0.5f, true));
        registry.put(new MovingPrefab("9w", "9 (White)", new MaterialPrefab("testmaterial", MapIndex.DIRT), 0.5f, true));
    }
}
