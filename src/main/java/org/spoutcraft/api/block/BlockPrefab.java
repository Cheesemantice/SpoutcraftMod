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

import com.google.gson.Gson;
import org.spoutcraft.api.Prefab;

public class BlockPrefab extends Prefab {
    private static final long serialVersionUID = 1L;
    private final String displayName;
    private final float hardness;
    private final boolean showInCreativeTab;
    private final int light;
    private final int lightOpacity;
    private final String modelPath;

    public BlockPrefab(String identifier, String displayName, float hardness, int light, int lightOpacity, boolean showInCreativeTab) {
        super(identifier);
        this.displayName = displayName;
        this.hardness = hardness;
        this.light = light;
        this.lightOpacity = lightOpacity;
        this.showInCreativeTab = showInCreativeTab;
        modelPath = null;
    }

    public BlockPrefab(String identifier, Gson gson) {
        super(identifier);
        displayName = gson.fromJson("display-name", String.class);
        hardness = gson.fromJson("hardness", Float.class);
        showInCreativeTab = gson.fromJson("show-in-creative-tab", Boolean.class);
        light = gson.fromJson("light-level", Integer.class);
        lightOpacity = gson.fromJson("light-opacity", Integer.class);
        modelPath = gson.fromJson("model-path", String.class);
    }

    public String getDisplayName() {
        return displayName;
    }

    public float getHardness() {
        return hardness;
    }

    public boolean shouldShowInCreativeTab() {
        return showInCreativeTab;
    }

    public String getModelPath() {
        return modelPath;
    }

    @Override
    public String toString() {
        final String NEW_LINE = System.getProperty("line.separator");
        final String parent = super.toString();
        return parent.substring(0, parent.length() - 1) + " Display Name: " + displayName + NEW_LINE + " Hardness: " + hardness + NEW_LINE + " Light Level: " + light + NEW_LINE + " Light Opacity: " + lightOpacity + NEW_LINE + " Model: " + modelPath + NEW_LINE + "}";
    }
}
