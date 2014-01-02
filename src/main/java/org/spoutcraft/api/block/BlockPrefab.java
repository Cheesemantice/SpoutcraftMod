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
package org.spoutcraft.api.block;

import org.spoutcraft.api.Prefab;
import org.spoutcraft.api.addon.Addon;
import org.spoutcraft.api.material.MaterialPrefab;

public class BlockPrefab extends Prefab {
    private static final long serialVersionUID = 1L;
    private final String displayName;
    private final MaterialPrefab prefab;
    private final float hardness;
    private final boolean showInCreativeTab;
    private final int light;
    private final int lightOpacity;

    public BlockPrefab(Addon addon, String identifier, String displayName, MaterialPrefab prefab, float hardness, int light, int lightOpacity, boolean showInCreativeTab) {
        super(addon, identifier);
        this.displayName = displayName;
        this.prefab = prefab;
        this.hardness = hardness;
        this.light = light;
        this.lightOpacity = lightOpacity;
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

    @Override
    public String toString() {
        final String NEW_LINE = System.getProperty("line.separator");
        final String parent = super.toString();
        final StringBuilder builder = new StringBuilder(parent.substring(0, parent.length() - 1));
        builder
                .append(" Display Name: " + displayName + NEW_LINE)
                .append(" " + prefab.toString() + NEW_LINE)
                .append(" Hardness: " + hardness + NEW_LINE)
                .append(" Light Level: " + light + NEW_LINE)
                .append(" Light Opacity: " + lightOpacity + NEW_LINE)
                .append("}");
        return builder.toString();
    }
}
