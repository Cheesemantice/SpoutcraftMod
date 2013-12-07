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

import org.spoutcraft.api.gui.Component;
import org.spoutcraft.api.util.Color;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import net.minecraft.util.ChatAllowedCharacters;

public class TextField extends LabelBase {

    private Color overTxtColor;

    public TextField() {
        super();
        this.setText("");
        this.setBackground(Color.DARK_GRAY);
        this.setForeground(Color.WHITE);
        this.setOverTextColor(new Color(0xFF, 0xFF, 0x88));
        setHeight(-1);
    }

    @Override
    public void render() {
        int width = this.getWidth();
        int height = this.getHeight();
        int x = this.getX();
        int y = this.getY();

        this.fillRect(x, y, width, height, Color.BLACK);
        this.fillRect(x + 1, y + 1, width - 2, height - 2, this.getBackground());

        this.pushClip();
        this.setClip(x + 2, y, getWidth() - 4, getHeight());

        GL11.glPushMatrix();

        getFont().setSize(getFontSize());
        int strWidth = (int) getFont().getWidth(getText());
        if(strWidth > width - 6) {
            GL11.glTranslatef(-(strWidth - (width - 6)), 0, 0);
        }
        float strDescent = getFont().getDescent();

        Color txtColor = this.getForeground();
        if (containsMouse()) {
            txtColor = this.getOverTextColor();
        }
        getFont().setColor(txtColor);
        getFont().drawString(getText(), x + 2, y + getHeight() - strDescent - 2);

        if(this.isFocused()) {
            this.fillRect(x + 2 + strWidth + 1, y + 2, 1, height - 4, txtColor);
        }

        GL11.glPopMatrix();

        this.popClip();
    }

    @Override
    public boolean focusable() {
        return true;
    }

    public void setOverTextColor(Color c) {
        this.overTxtColor = c;
    }

    public Color getOverTextColor() {
        return this.overTxtColor;
    }

    @Override
    public int getHeight() {
        if (super.getHeight() >= 0) {
            return super.getHeight();
        } else {
            this.getFont().setSize(this.getFontSize());
            return (int) (this.getFont().getSize() + 4);
        }
    }

    public void keyPress(int key, char c) {
        if(key == Keyboard.KEY_BACK) {
            int curLen = this.getText().length();
            if(curLen > 0) {
                this.setText(this.getText().substring(0, curLen - 1));
            }
        } else if(ChatAllowedCharacters.isAllowedCharacter(c)) {
            this.setText(this.getText() + c);
        }
        System.out.println("CHAR: " + c);
    }

    public void keyRelease(int key, char c) {

    }

}
