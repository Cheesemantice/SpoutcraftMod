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
package org.spoutcraft.api.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.*;
import org.spoutcraft.api.gui.component.RootContainer;
import org.spoutcraft.api.gui.event.key.KeyPressEvent;
import org.spoutcraft.api.gui.event.key.KeyReleaseEvent;
import org.spoutcraft.api.gui.event.mouse.MouseDownEvent;
import org.spoutcraft.api.gui.event.mouse.MouseMoveEvent;
import org.spoutcraft.api.gui.event.mouse.MouseUpEvent;
import org.spoutcraft.api.gui.renderer.GuiRenderer;
import org.spoutcraft.api.gui.renderer.GuiRendererDepth;

public class Gui extends GuiScreen {
    private int lastButton = -1;
    private long clickTime = 0;
    private RootContainer root;
    private GuiRenderer guiRenderer = new GuiRendererDepth();

    public Gui() {
        this.root = new RootContainer(this);
    }

    @Override
    public void initGui() {
        guiRenderer.initGui(this);
        root.clearComponents();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTick) {
        guiRenderer.drawScreen(this, root, mouseX, mouseY, partialTick);
    }

    public GuiRenderer getRenderer() {
        return this.guiRenderer;
    }

    public RootContainer getRoot() {
        return root;
    }

    @Override
    public void handleKeyboardInput() {
        int key = Keyboard.getEventKey();
        char keyChar = Keyboard.getEventCharacter();
        if (Keyboard.getEventKeyState()) {
            if (key == 87) {
                this.mc.toggleFullscreen();
                return;
            }
            this.keyTyped(keyChar, key);
            //            root.callEvent(new KeyPressEvent(root, key, keyChar));
            //            super.keyTyped(keyChar, key);
        } else {
            root.callEvent(new KeyReleaseEvent(root, key, keyChar));
        }
    }

    @Override
    public void handleMouseInput() {
        int x = Mouse.getEventX() * this.width / this.mc.displayWidth;
        int y = this.height - Mouse.getEventY() * this.height / this.mc.displayHeight - 1;
        int btn = Mouse.getEventButton();
        if (Mouse.getEventButtonState()) {
            if (this.lastButton != -1) {
                mouseClickMove(x, y, btn, System.currentTimeMillis() - this.clickTime);
            } else {
                mouseClicked(x, y, btn);
                this.clickTime = System.currentTimeMillis();
            }
            this.lastButton = btn;
        } else if (btn != -1) {
            mouseMovedOrUp(x, y, btn);
            this.lastButton = -1;
        } else {
            if (this.lastButton != -1) {
                mouseClickMove(x, y, this.lastButton, System.currentTimeMillis() - this.clickTime);
            } else {
                mouseMovedOrUp(x, y, -1);
            }
        }
    }

    @Override
    protected void keyTyped(char keyChar, int key) {
        super.keyTyped(keyChar, key);
        root.callEvent(new KeyPressEvent(root, key, keyChar));
    }

    @Override
    protected void mouseClicked(int x, int y, int btn) {
        super.mouseClicked(x, y, btn);
        root.callEvent(new MouseDownEvent(root, btn, x, y));
    }

    @Override
    protected void mouseMovedOrUp(int x, int y, int btn) {
        super.mouseMovedOrUp(x, y, btn);
        if (btn == -1) {
            root.callEvent(new MouseMoveEvent(root, btn, x, y));
        } else {
            root.callEvent(new MouseUpEvent(root, btn, x, y));
        }
    }

    @Override
    protected void mouseClickMove(int x, int y, int btn, long timePressed) {
        super.mouseClickMove(x, y, btn, timePressed);
        root.callEvent(new MouseMoveEvent(root, btn, x, y));
    }

    @Override
    public boolean doesGuiPauseGame() {
        return root.pausesGame();
    }

    @Override
    public void updateScreen() {
        root.processEvents();
    }

    public void add(Component c) {
        root.addComponent(c);
    }

    public void remove(Component c) {
        root.removeComponent(c);
    }

    public void addListeners(Object o) {
        root.addEventListeners(o);
    }

    public void removeListeners(Object o) {
        root.removeEventListeners(o);
    }

    public int[] screenToScaled(int x, int y) {
        x = x * width / Minecraft.getMinecraft().displayWidth;
        y = y * height / Minecraft.getMinecraft().displayHeight;
        return new int[] {x, y};
    }

    public int[] scaledToScreen(int x, int y) {
        x = x * Minecraft.getMinecraft().displayWidth / width;
        y = y * Minecraft.getMinecraft().displayHeight / height;
        return new int[] {x, y};
    }
}
