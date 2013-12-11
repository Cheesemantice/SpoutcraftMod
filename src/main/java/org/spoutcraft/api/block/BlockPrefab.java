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
package org.spoutcraft.api.block;

import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import org.spoutcraft.api.Prefab;
import org.spoutcraft.api.material.MaterialPrefab;

public class BlockPrefab extends Prefab {
    private static final long serialVersionUID = 2552797718795539253L;
    private final String displayName;
    private final MaterialPrefab prefab;
    private final float hardness;
    private final boolean showInCreativeTab;

    public BlockPrefab(String identifier, String displayName, MaterialPrefab prefab, float hardness, boolean showInCreativeTab) {
        super(identifier);
        this.displayName = displayName;
        this.prefab = prefab;
        this.hardness = hardness;
        this.showInCreativeTab = showInCreativeTab;
    }

    public String getDisplayName() {
        return displayName;
    }

    public MaterialPrefab getMaterialPrefab() {
        return prefab;
    }

    public float getHardness() {
        return hardness;
    }

    public boolean shouldShowInCreativeTab() {
        return showInCreativeTab;
    }

    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        return 1;
    }

    public int getLightOpacity(World world, int x, int y, int z) {
        return 255;
    }

    @Override
    public String toString() {
        final String NEW_LINE = System.getProperty("line.separator");
        final String parent = super.toString();
        final StringBuilder builder = new StringBuilder(parent.substring(0, parent.length() - 1));
        builder
                .append(" Display Name: " + displayName + NEW_LINE)
                .append(" " + prefab.toString() + NEW_LINE)
                .append(" Hardness: " + hardness + NEW_LINE)
                .append("}");
        return builder.toString();
    }
}
