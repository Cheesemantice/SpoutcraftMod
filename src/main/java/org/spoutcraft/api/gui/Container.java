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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.lwjgl.opengl.*;
import org.spoutcraft.api.gui.event.EventHandler;
import org.spoutcraft.api.gui.event.container.AddComponentEvent;
import org.spoutcraft.api.gui.event.container.RemoveComponentEvent;
import org.spoutcraft.api.gui.event.key.KeyPressEvent;
import org.spoutcraft.api.gui.event.key.KeyReleaseEvent;
import org.spoutcraft.api.gui.event.mouse.MouseDownEvent;
import org.spoutcraft.api.gui.event.mouse.MouseMoveEvent;
import org.spoutcraft.api.gui.event.mouse.MouseUpEvent;

public class Container extends Component {
    private List<Component> components = new ArrayList<Component>();

    public Container() {
        this.addEventListeners(this);
    }

    @Override
    public void render() {
        this.pushClip();
        this.setClip(getX(), getY(), getWidth(), getHeight());
        GL11.glPushMatrix();
        GL11.glTranslatef(getX(), getY(), 0);
        this.fillRect(0, 0, getWidth(), getHeight(), getBackground());
        for (Component c : components) {
            if (c.isVisible()) {
                c.render();
            }
        }
        GL11.glPopMatrix();
        this.popClip();
    }

    public void addComponent(Component c) {
        callEvent(new AddComponentEvent(this, c));
    }

    public void removeComponent(Component c) {
        callEvent(new RemoveComponentEvent(this, c));
    }

    public void clearComponents() {
        for (Component c : getComponents()) {
            removeComponent(c);
        }
    }

    public List<Component> getComponents() {
        return Collections.unmodifiableList(components);
    }

    @EventHandler (priority = -1)
    public void onAddComponent(AddComponentEvent e) {
        e.getAddedComponent().setParent(this);
        e.getAddedComponent().setGui(getGui());
        components.add(e.getAddedComponent());
    }

    @EventHandler (priority = -1)
    public void onRemoveComponent(RemoveComponentEvent e) {
        e.getRemovedComponent().setParent(null);
        e.getRemovedComponent().setGui(null);
        components.remove(e.getRemovedComponent());
    }

    @Override
    public void mouseDown(int btn, int x, int y) {
        for (Component c : components) {
            if (c.receiveAllEvents() || c.containsPoint(x, y)) {
                c.callEvent(new MouseDownEvent(c, btn, x - c.getX(), y - c.getY()));
            }
        }
    }

    @Override
    public void mouseUp(int btn, int x, int y) {
        for (Component c : components) {
            if (c.receiveAllEvents() || c.containsPoint(x, y)) {
                c.callEvent(new MouseUpEvent(c, btn, x - c.getX(), y - c.getY()));
            }
        }
    }

    @Override
    public void mouseMove(int btn, int x, int y) {
        for (Component c : components) {
            if (c.receiveAllEvents() || c.containsPoint(x, y)) {
                c.callEvent(new MouseMoveEvent(c, btn, x - c.getX(), y - c.getY()));
            }
        }
    }

    @Override
    public void keyPress(int key, char ch) {
        for (Component c : components) {
            if (c.receiveAllEvents()) {
                c.callEvent(new KeyPressEvent(this, key, ch));
            }
        }
    }

    @Override
    public void keyRelease(int key, char ch) {
        for (Component c : components) {
            if (c.receiveAllEvents()) {
                c.callEvent(new KeyReleaseEvent(this, key, ch));
            }
        }
    }

    @Override
    protected void processEvents() {
        //Because we process our events first,
        //it allows us to pass our events down to sub components in 1 tick
        super.processEvents();
        for (Component c : components) {
            c.processEvents();
        }
    }
}
