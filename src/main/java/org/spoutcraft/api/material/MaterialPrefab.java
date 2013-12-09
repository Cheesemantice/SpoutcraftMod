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
package org.spoutcraft.api.material;

import org.spoutcraft.api.Prefab;

public class MaterialPrefab extends Prefab {
    private final MapIndex index;

    public MaterialPrefab(String identifier, MapIndex index) {
        super(identifier);
        this.index = index;
    }

    public MapIndex getMapIndex() {
        return index;
    }

    @Override
    public String toString() {
        final String NEW_LINE = System.getProperty("line.separator");
        final String parent = super.toString();
        final StringBuilder builder;
        if (parent.endsWith("}")) {
            builder = new StringBuilder(parent.substring(0, parent.length() - 2) + NEW_LINE); //TODO CHECK THIS
            builder.insert(builder.indexOf("Identifier: " + super.getIdentifier()), " ");
            builder
                    .append("  Map Index: " + index.name() + NEW_LINE)
                    .append(" }");
        } else {
            builder = new StringBuilder(parent.substring(0, parent.length() - 1) + NEW_LINE);
            builder
                    .append(" Map Index: " + index.name() + NEW_LINE)
                    .append("}");
        }
        return builder.toString();
    }
}
