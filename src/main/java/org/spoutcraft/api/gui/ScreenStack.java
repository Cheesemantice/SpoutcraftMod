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
package org.spoutcraft.api.gui;

import java.util.LinkedList;

import org.spoutcraft.api.addon.Addon;
import org.spoutcraft.api.gui.result.FocusResult;

public abstract class ScreenStack {
    private Screen focused = null;

    public abstract Screen put(Class<? extends Addon> clazz, Screen screen);

    public abstract Screen get(Class<? extends Addon> clazz, String identifier);

    public abstract Screen remove(Class<? extends Addon> clazz, String identifier);

    public abstract LinkedList<Screen> getAll(Class<? extends Addon> clazz);

    public abstract Class<? extends Addon> getAddonClassFor(Screen screen);

    public abstract void show(Class<? extends Addon> clazz, Screen screen);

    public abstract void show(Class<? extends Addon> clazz, String identifier);

    public void focus(Class<? extends Addon> clazz, String identifier) {
        final Screen found = get(clazz, identifier);
        //No screen found
        if (found == null) {
            return;
        }
        //Trying to focus a screen that already has focus
        if (focused != null && focused.equals(found)) {
            return;
        }
        //A screen already has focus, fire callback for focus lost
        if (focused != null) {
            onFocusChange(FocusResult.LOST, focused);
        }
        //Fire callback for screen that just gained focus
        onFocusChange(FocusResult.GAINED, found);
    }

    public Screen getFocusedScreen() {
        return focused;
    }

    public void onFocusChange(FocusResult result, Screen screen) {

    }
}
