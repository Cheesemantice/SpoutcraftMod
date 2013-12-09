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
package org.spoutcraft.mod.block;

import net.minecraft.block.BlockSand;
import org.spoutcraft.api.block.MovingPrefab;
import org.spoutcraft.mod.SpoutcraftMod;
import org.spoutcraft.mod.material.CustomMaterial;

public class CustomMovingBlock extends BlockSand {
    private final MovingPrefab prefab;

    public CustomMovingBlock(int id, MovingPrefab prefab, CustomMaterial material) {
        super(id, material);
        this.prefab = prefab;
        setUnlocalizedName(prefab.getIdentifier());
        setTextureName("spoutcraft:" + prefab.getIdentifier());
        setHardness(prefab.getHardness());

        if (prefab.shouldShowInCreativeTab()) {
            setCreativeTab(SpoutcraftMod.getCustomTabs());
        }
    }

    public MovingPrefab getPrefab() {
        return prefab;
    }
}
