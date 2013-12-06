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
package org.spoutcraft.api.gui.component;

import java.awt.Font;
import java.util.List;

import org.lwjgl.opengl.*;
import org.spoutcraft.api.gui.Component;
import org.spoutcraft.api.gui.Container;
import org.spoutcraft.api.resource.CustomFont;
import org.spoutcraft.api.util.Color;
import org.spoutcraft.mod.SpoutcraftMod;

public class Frame extends Container {
    private static final CustomFont titleFont;

    static {
        try {
            titleFont = new CustomFont(Font.createFont(Font.TRUETYPE_FONT, SpoutcraftMod.class.getResourceAsStream("/assets/spoutcraft/fonts/ubuntu-regular.ttf")).deriveFont(27f));
        } catch (Exception e) {
            throw new RuntimeException("Could not load font", e);
        }
    }

    private Label frameTitle;
    private Container innerContainer;
    private Color titleBackColor = Color.DARK_GRAY;

    public Frame() {
        frameTitle = new Label("New Frame");
        frameTitle.setFont(titleFont);
        frameTitle.setFontSize(9);
        frameTitle.setForeground(Color.WHITE);
        titleFont.setSize(9);
        frameTitle.setY(11 - (int) titleFont.getDescent());
        innerContainer = new Container();
        innerContainer.setX(1);
        innerContainer.setY(12);
        super.addComponent(innerContainer);
        super.addComponent(frameTitle);
        setWidth(3);
        setHeight(14);
        setBackground(Color.LIGHT_GRAY);
    }

    public void setTitle(String title) {
        frameTitle.setText(title);
    }

    public String getTitle() {
        return frameTitle.getText();
    }

    public void setTitleBackColor(Color c) {
        this.titleBackColor = c;
    }

    public Color getTitleBackColor() {
        return this.titleBackColor;
    }

    @Override
    public void setWidth(int width) {
        width = Math.max(width, 3);
        super.setWidth(width);
        innerContainer.setWidth(width - 2);
        frameTitle.setX(width / 2 - frameTitle.getWidth() / 2);
    }

    @Override
    public void setHeight(int height) {
        height = Math.max(height, 14);
        super.setHeight(height);
        innerContainer.setHeight(height - 13);
    }

    public void addComponent(Component c) {
        innerContainer.addComponent(c);
    }

    public void removeComponent(Component c) {
        innerContainer.addComponent(c);
    }

    public void clearComponents() {
        innerContainer.clearComponents();
    }

    public List<Component> getComponents() {
        return innerContainer.getComponents();
    }

    @Override
    public void render() {
        this.pushClip();
        GL11.glPushMatrix();
        GL11.glTranslatef(getX(), getY(), 0);
        this.setClip(0, 0, getWidth(), getHeight());
        //this.fillRect(0, 0, getWidth(), getHeight(), getBackground());
        this.fillRect(0, 0, getWidth(), getHeight(), Color.BLACK);
        this.fillRect(1, 1, getWidth() - 2, 10, titleBackColor);
        this.fillRect(1, 12, getWidth() - 2, getHeight() - 13, getBackground());
        for (Component c : super.getComponents()) {
            if (c.isVisible()) {
                c.render();
            }
        }
        GL11.glPopMatrix();
        this.popClip();
    }
}
