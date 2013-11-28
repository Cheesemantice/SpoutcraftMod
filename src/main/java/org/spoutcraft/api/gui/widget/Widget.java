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

import org.spoutcraft.api.gui.result.ClickResult;
import org.spoutcraft.api.gui.result.FocusResult;

public abstract class Widget {
    private final String identifier;
    private int x, y, heightBound, widthBound;
    private boolean focused, shown, enabled;

    public Widget(String identifier, int x, int y, int heightBound, int widthBound) {
        this.identifier = identifier;
        this.x = x;
        this.y = y;
        this.heightBound = heightBound;
        this.widthBound = widthBound;
    }

    public String getIdentifier() {
        return identifier;
    }

    public int getX() {
        return x;
    }

    public Widget setX(int x) {
        this.x = x;
        return this;
    }

    public int getY() {
        return y;
    }

    public Widget setY(int y) {
        this.y = y;
        return this;
    }

    public int getHeightBound() {
        return heightBound;
    }

    public Widget setHeightBound(int heightBound) {
        this.heightBound = heightBound;
        return this;
    }

    public int getWidthBound() {
        return widthBound;
    }

    public Widget setWidthBound(int widthBound) {
        this.widthBound = widthBound;
        return this;
    }

    public void enable() {
        this.enabled = true;
    }

    public void disable() {
        this.enabled = false;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void focus(boolean focused) {
        this.focused = focused;
    }

    public boolean isFocused() {
        return focused;
    }

    public void show() {
        this.shown = true;
    }

    public void hide() {
        this.shown = false;
    }

    public boolean isVisible() {
        return shown;
    }

    public void onEnable() {

    }

    public void onDisable() {

    }

    public void onShow() {

    }

    public void onHide() {

    }

    public void onHover(int x, int y) {

    }

    public void onClick(ClickResult action, int x, int y) {

    }

    public void onFocus(FocusResult reason) {

    }
}
