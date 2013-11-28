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
package org.spoutcraft.mod.gui.builtin;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import org.spoutcraft.api.util.RenderUtil;

@SideOnly (Side.CLIENT)
public class SpoutcraftCreateWorld extends GuiScreen {
    private SpoutcraftSimpleButton backButton, createButton;
    private SpoutcraftBackground background = new SpoutcraftBackground();
    private GuiScreen parent;
    private FontRenderer fontRenderer = RenderUtil.MINECRAFT.fontRenderer;
    private String titleText = I18n.getString("selectWorld.create");
    ;

    public SpoutcraftCreateWorld(GuiScreen parent) {
        this.parent = parent;
        background.selectBackground();
    }

    public void initGui() {
        final String backText = I18n.getString("gui.cancel");
        final String createText = I18n.getString("mco.create.world");
        int xPosition = fontRenderer.getStringWidth(createText) + 3;
        int yPosition = height - fontRenderer.FONT_HEIGHT - 1;

        backButton = new SpoutcraftSimpleButton(1, 3, yPosition, backText);
        createButton = new SpoutcraftSimpleButton(4, width - xPosition, yPosition, createText);

        buttonList.add(backButton);
        buttonList.add(createButton);
    }

    protected void actionPerformed(GuiButton button) {
        switch (button.id) {
            case 1: // Back
                RenderUtil.MINECRAFT.displayGuiScreen(parent);
                break;
            case 2: // Create
                break;
        }
    }

    @Override
    public void drawScreen(int par1, int par2, float par3) {
        // Draw the background with overlay
        background.drawBackground(0, 0, width, height, Integer.MAX_VALUE, 0);

        // Draw gradients - each drawn twice to get desired darkness
        drawGradientRect(0, 0, width, fontRenderer.FONT_HEIGHT + 4, Integer.MIN_VALUE, Integer.MIN_VALUE);
        drawGradientRect(0, 0, width, fontRenderer.FONT_HEIGHT + 4, Integer.MIN_VALUE, Integer.MIN_VALUE);

        drawGradientRect(0, height - (fontRenderer.FONT_HEIGHT + 4), width, height, Integer.MIN_VALUE, Integer.MIN_VALUE);
        drawGradientRect(0, height - (fontRenderer.FONT_HEIGHT + 4), width, height, Integer.MIN_VALUE, Integer.MIN_VALUE);

        // Draw screen title string
        drawString(RenderUtil.MINECRAFT.fontRenderer, titleText, (width / 2) - (fontRenderer.getStringWidth(titleText) / 2), 3, 14737632);

        super.drawScreen(par1, par2, par3);
    }
}
