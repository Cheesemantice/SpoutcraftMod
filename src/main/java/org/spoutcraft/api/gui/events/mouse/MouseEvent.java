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
package org.spoutcraft.api.gui.events.mouse;

import net.minecraft.nbt.NBTTagCompound;

import org.spoutcraft.api.gui.Component;
import org.spoutcraft.api.gui.events.Event;

public class MouseEvent extends Event {

    public static final int MOUSE_DOWN = 0;
    public static final int MOUSE_UP = 1;
    public static final int MOUSE_MOVE = 2;

    private final int x, y, btn, type;

    public MouseEvent(Component source, int btn, int x, int y, int type) {
        super(source);
        this.x = x;
        this.y = y;
        this.btn = btn;
        this.type = type;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getButton() {
        return btn;
    }

    public int getType() {
        return type;
    }

    @Override
    public boolean isWritable() {
        return true;
    }

    @Override
    public void write(NBTTagCompound c) {
        super.write(c);
        c.setInteger("x", getX());
        c.setInteger("y", getY());
        c.setInteger("btn", getButton());
        c.setInteger("mEvtType", getType());
    }

}
