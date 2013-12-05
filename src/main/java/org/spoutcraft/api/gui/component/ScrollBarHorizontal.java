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
import org.spoutcraft.api.util.RenderUtil;

public class ScrollBarHorizontal extends Component {
    int barX;

    public ScrollBarHorizontal() {
        this.setBackground(new Color(0x2F, 0x2F, 0x2F));
        this.setForeground(new Color(0x6F, 0x6F, 0x6F));
    }

    @Override
    public void render() {
        RenderUtil.drawRect(getX(), getY(), getWidth(), getHeight(), Color.BLACK);
        RenderUtil.drawRect(getX() + 1, getY() + 1, getWidth() - 2, getHeight() - 2, getBackground());
    }
}
