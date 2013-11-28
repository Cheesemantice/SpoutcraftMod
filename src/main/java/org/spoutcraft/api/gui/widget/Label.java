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
import org.lwjgl.util.Color;

public class Label extends Widget {
    public static final Color DEFAULT_TEXT_COLOR = new Color(Color.WHITE);
    public static final Color DEFAULT_DISABLED_COLOR = new Color(Color.LTGREY);
    private String text;
    private Color textColor, hoverColor, disableColor;
    private ResourceLocation background;

    public Label(String identifier, int x, int y, int heightBound, int widthBound, String text) {
        this(identifier, x, y, heightBound, widthBound, text, DEFAULT_TEXT_COLOR, DEFAULT_TEXT_COLOR, DEFAULT_DISABLED_COLOR, null);
    }

    public Label(String identifier, int x, int y, int heightBound, int widthBound, String text, Color textColor, Color hoverColor, Color disableColor, ResourceLocation background) {
        super(identifier, x, y, heightBound, widthBound);
        this.text = text;
        this.textColor = textColor;
        this.hoverColor = hoverColor;
        this.disableColor = disableColor;
        this.background = background;
    }

    public String getText() {
        return text;
    }

    public Label setText(String text) {
        this.text = text;
        return this;
    }

    public Color getTextColor() {
        return textColor;
    }

    public Label setTextColor(Color textColor) {
        this.textColor = textColor;
        return this;
    }

    public Color getHoverColor() {
        return hoverColor;
    }

    public Label setHoverColor(Color hoverColor) {
        this.hoverColor = hoverColor;
        return this;
    }

    public Color getDisableColor() {
        return disableColor;
    }

    public Label setDisableColor(Color disableColor) {
        this.disableColor = disableColor;
        return this;
    }

    public ResourceLocation getBackground() {
        return background;
    }

    public Label setBackground(ResourceLocation background) {
        this.background = background;
        return this;
    }

    public void onTextChanged(String previous) {

    }
}
