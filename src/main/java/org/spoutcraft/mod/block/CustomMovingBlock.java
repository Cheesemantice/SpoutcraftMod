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
package org.spoutcraft.mod.block;

import net.minecraft.block.BlockSand;
import org.spoutcraft.api.Materials;
import org.spoutcraft.api.Prefabable;
import org.spoutcraft.api.addon.Addon;
import org.spoutcraft.api.block.MovingPrefab;
import org.spoutcraft.mod.SpoutcraftMod;

public class CustomMovingBlock extends BlockSand implements Prefabable<MovingPrefab> {
    private final MovingPrefab prefab;

    public CustomMovingBlock(int id, Addon addon, MovingPrefab prefab) {
        super(id, Materials.CUSTOM_MOVING);
        this.prefab = prefab;
        setUnlocalizedName(prefab.getIdentifier());
        setTextureName("spoutcraft:" + addon.getDescription().getIdentifier() + "/moving/" + prefab.getIdentifier());
        setHardness(prefab.getHardness());

        if (prefab.shouldShowInCreativeTab()) {
            setCreativeTab(SpoutcraftMod.getCustomTabs());
        }
    }

    @Override
    public MovingPrefab getPrefab() {
        return prefab;
    }
}