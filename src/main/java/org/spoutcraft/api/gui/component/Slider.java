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

import net.minecraft.nbt.NBTTagCompound;
import org.lwjgl.input.*;
import org.spoutcraft.api.gui.Container;
import org.spoutcraft.api.gui.Gui;
import org.spoutcraft.api.gui.event.SliderEvent;
import org.spoutcraft.api.util.Color;

public class Slider extends Button {
    private Button slideButton = new Button();
    private boolean trackingMouse = false;
    private int min;
    private int max;
    private int val;

    public Slider() {
        this("");
    }

    public Slider(String text) {
        super(text);
        this.setBackground(new Color(0x2F, 0x2F, 0x2F));
        this.setOverColor(this.getBackground());
        this.setClickColor(this.getBackground());
        this.slideButton.setWidth(6);
        this.slideButton.setHeight(this.getHeight());
        this.slideButton.setOverColor(slideButton.getBackground());
        this.slideButton.setClickColor(slideButton.getBackground());
    }

    @Override
    public void setX(int x) {
        super.setX(x);
        this.calcSliderLoc();
    }

    @Override
    public void setY(int y) {
        super.setY(y);
        slideButton.setY(y);
    }

    @Override
    public void setHeight(int height) {
        super.setHeight(height);
        if (this.slideButton != null) {
            this.slideButton.setHeight(this.getHeight());
        }
    }

    @Override
    public void setParent(Container c) {
        super.setParent(c);
        this.slideButton.setParent(c);
    }

    @Override
    public void setGui(Gui gui) {
        super.setGui(gui);
        this.slideButton.setGui(gui);
    }

    @Override
    public void render() {
        int width = this.getWidth();
        int height = this.getHeight();
        int x = this.getX();
        int y = this.getY();

        Color dispColor = this.getBackground();
        Color txtColor = this.getForeground();
        if (containsMouse()) {
            txtColor = this.getOverTextColor();
            if (Mouse.isButtonDown(0)) {
                dispColor = this.getClickColor();
            } else {
                dispColor = this.getOverColor();
            }
        }
        this.fillRect(x, y, width, height, Color.BLACK);
        this.fillRect(x + 1, y + 1, width - 2, height - 2, dispColor);

        //Slider goes beneath text
        this.slideButton.render();

        getFont().setSize(getFontSize());
        int strWidth = (int) getFont().getWidth(getText());
        float strDescent = getFont().getDescent();
        getFont().setColor(txtColor);
        getFont().drawString(getText(), x + getWidth() / 2 - strWidth / 2, y + getHeight() - strDescent - 2);
    }

    @Override
    public void mouseMove(int btn, int x, int y) {
        if (trackingMouse) {
            setSliderLoc(x);
        }
    }

    @Override
    public void mouseDown(int btn, int x, int y) {
        if (btn == 0 && this.containsMouse()) {
            setSliderLoc(x);
            trackingMouse = true;
        }
    }

    @Override
    public void mouseUp(int btn, int x, int y) {
        if (btn == 0 && this.trackingMouse) {
            this.trackingMouse = false;
        }
    }

    @Override
    public boolean receiveAllEvents() {
        return true;
    }

    private void setSliderLoc(int mouseX) {
        int x = Math.min(Math.max(mouseX, 3), this.getWidth() - 3);
        //        float pct = (x - 3) / (float)(this.getWidth() - 6);
        int len = (x - 3) * (max - min) / (this.getWidth() - 6);
        len += min;
        val = len;
        slideButton.setX(getX() + x - 3);
        callEvent(new SliderEvent(this, val));
    }

    private void calcSliderLoc() {
        if (max - min > 0) {
            slideButton.setX(getX() + (val - min) * (this.getWidth() - 6) / (max - min));
        }
    }

    public void setMin(int min) {
        this.min = min;
        this.calcSliderLoc();
    }

    public void setMax(int max) {
        this.max = max;
        this.calcSliderLoc();
    }

    public void setValue(int value) {
        this.val = value;
        this.calcSliderLoc();
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public int getValue() {
        return val;
    }

    public float getValuePct() {
        return val / (float) (max - min);
    }
}
