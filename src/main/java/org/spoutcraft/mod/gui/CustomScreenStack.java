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
package org.spoutcraft.mod.gui;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import org.spoutcraft.api.addon.Addon;
import org.spoutcraft.api.gui.Screen;
import org.spoutcraft.api.gui.ScreenStack;

public class CustomScreenStack extends ScreenStack {
    private static final HashMap<Class<? extends Addon>, LinkedList<Screen>> ADDONS_BY_SCREENS = new HashMap<>();
    private Screen focused = null;

    @Override
    public Screen put(Class<? extends Addon> clazz, Screen screen) {
        if (clazz == null) {
            throw new IllegalArgumentException("Attempt to put a null addon class for the ScreenStack!");
        }
        if (screen == null) {
            throw new IllegalArgumentException("Attempt to put a null screen into the ScreenStack!");
        }
        LinkedList<Screen> addonScreens = ADDONS_BY_SCREENS.get(clazz);
        if (addonScreens == null) {
            addonScreens = new LinkedList<>();
            ADDONS_BY_SCREENS.put(clazz, addonScreens);
        }
        addonScreens.add(screen);
        return screen;
    }

    @Override
    public Screen get(Class<? extends Addon> clazz, String identifier) {
        if (clazz == null) {
            throw new IllegalArgumentException("Attempt to get a null addon class for the ScreenStack!");
        }
        if (identifier == null || identifier.isEmpty()) {
            throw new IllegalArgumentException("Attempt to get a screen from the screen stack but the identifier is invalid [" + identifier + "]");
        }
        LinkedList<Screen> addonScreens = ADDONS_BY_SCREENS.get(clazz);
        if (addonScreens != null && !addonScreens.isEmpty()) {
            for (Screen screen : addonScreens) {
                if (screen.getIdentifier().equals(identifier)) {
                    return screen;
                }
            }
        }
        return null;
    }

    @Override
    public Screen remove(Class<? extends Addon> clazz, String identifier) {
        if (clazz == null) {
            throw new IllegalArgumentException("Attempt to remove a null addon class for the ScreenStack!");
        }
        if (identifier == null || identifier.isEmpty()) {
            throw new IllegalArgumentException("Attempt to remove a screen from the screen stack but the identifier is invalid [" + identifier + "]");
        }
        LinkedList<Screen> addonScreens = ADDONS_BY_SCREENS.get(clazz);
        Screen found = null;
        if (addonScreens != null && !addonScreens.isEmpty()) {
            Iterator<Screen> addonScreensIterator = addonScreens.iterator();
            while (addonScreensIterator.hasNext()) {
                found = addonScreensIterator.next();
                if (found.getIdentifier().equals(identifier)) {
                    addonScreensIterator.remove();
                }
            }
        }
        return found;
    }

    @Override
    public LinkedList<Screen> getAll(Class<? extends Addon> clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException("Attempt to getAll a null addon class for the ScreenStack!");
        }
        return ADDONS_BY_SCREENS.get(clazz);
    }

    @Override
    public void show(Class<? extends Addon> clazz, Screen screen) {

    }

    @Override
    public void show(Class<? extends Addon> clazz, String identifier) {

    }
}
