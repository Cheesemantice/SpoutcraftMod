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
package org.spoutcraft.api.gui.widget;

import net.minecraft.util.ResourceLocation;
import org.lwjgl.util.*;

public class Button extends Label {
    public static final ResourceLocation DEFAULT_BACKGROUND = new ResourceLocation("spoutcraft", "textures/gui/button_background.png");
    public static final ResourceLocation DEFAULT_CLICKED_BACKGROUND = new ResourceLocation("spoutcraft", "textures/gui/button_clicked_background.png");
    private ResourceLocation clickedBackground;

    public Button(String identifier, int x, int y, int heightBound, int widthBound, String text) {
        this(identifier, x, y, heightBound, widthBound, text, DEFAULT_TEXT_COLOR, DEFAULT_TEXT_COLOR, DEFAULT_DISABLED_COLOR, DEFAULT_BACKGROUND, DEFAULT_CLICKED_BACKGROUND);
    }

    public Button(String identifier, int x, int y, int heightBound, int widthBound, String text, Color textColor, Color hoverColor, Color disableColor, ResourceLocation background, ResourceLocation clickedBackground) {
        super(identifier, x, y, heightBound, widthBound, text, textColor, hoverColor, disableColor, background);
        this.clickedBackground = clickedBackground;
    }

    public ResourceLocation getClickedBackground() {
        return clickedBackground;
    }

    public void setClickedBackground(ResourceLocation clickedBackground) {
        this.clickedBackground = clickedBackground;
    }
}
