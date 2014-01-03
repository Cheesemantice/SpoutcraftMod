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
package org.spoutcraft.api.item;

import org.spoutcraft.api.Prefab;

public class ItemPrefab extends Prefab {
    private static final long serialVersionUID = 1L;
    private final String displayName;
    private final int maxStackSize;
    private final boolean showInCreativeTab;

    public ItemPrefab(String identifier, String displayName, int maxStackSize, boolean showInCreativeTab) {
        super(identifier);
        this.displayName = displayName;
        this.maxStackSize = maxStackSize;
        this.showInCreativeTab = showInCreativeTab;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getMaxStackSize() {
        return maxStackSize;
    }

    public boolean shouldShowInCreativeTab() {
        return showInCreativeTab;
    }

    @Override
    public String toString() {
        final String NEW_LINE = System.getProperty("line.separator");
        final String parent = super.toString();
        return parent.substring(0, parent.length() - 1) + NEW_LINE + " Display Name: " + displayName + NEW_LINE + " Max Stack Size: " + maxStackSize + NEW_LINE + "}";
    }
}
