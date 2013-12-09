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
package org.spoutcraft.mod.gui.builtin;

import java.awt.Font;

import cpw.mods.fml.client.GuiModList;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSelectWorld;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.*;
import org.spoutcraft.api.resource.CustomFont;
import org.spoutcraft.api.util.RenderUtil;
import org.spoutcraft.api.util.TextureUtil;
import org.spoutcraft.mod.SpoutcraftMod;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.*;

public class SpoutcraftMainMenu extends GuiScreen {
    private static final int SPOUTCRAFT_LOGO_TEX = TextureUtil.loadTexture(new ResourceLocation("spoutcraft", "textures/gui/title/spoutcraft.png"));
    private SpoutcraftBackground background = new SpoutcraftBackground();
    private final CustomFont ubuntu;

    static {
        TextureUtil.bind(SPOUTCRAFT_LOGO_TEX);
        TextureUtil.setMinFilter(GL_LINEAR);
        TextureUtil.setMagFilter(GL_LINEAR);
        TextureUtil.setWrapS(GL_CLAMP_TO_EDGE);
        TextureUtil.setWrapT(GL_CLAMP_TO_EDGE);
    }

    public SpoutcraftMainMenu() {
        try {
            ubuntu = new CustomFont(Font.createFont(Font.TRUETYPE_FONT, SpoutcraftMod.class.getResourceAsStream("/assets/spoutcraft/fonts/ubuntu-regular.ttf")).deriveFont(36f));
        } catch (Exception e) {
            throw new RuntimeException("Could not load font", e);
        }
    }

    public void initGui() {
        addButtons();
    }

    private void addButtons() {
        String singleplayerText = I18n.getString("menu.singleplayer");
        String multiplayerText = I18n.getString("menu.multiplayer");
        String optionsText = I18n.getString("menu.options");
        String modsText = "Mods";
        String addonsText = "Addons";
        String quitText = I18n.getString("menu.quit");

        int normalFontColor = 14737632;
        int hoverFontColor = 5479876;
        int disabledFontColor = -6250336;

        final SpoutcraftSimpleButton singlePlayerButton = new SpoutcraftSimpleButton(1, getSidebarCenterX(130, singleplayerText), 55, singleplayerText, normalFontColor, hoverFontColor, disabledFontColor);
        final SpoutcraftSimpleButton multiplayerButton = new SpoutcraftSimpleButton(2, getSidebarCenterX(130, multiplayerText), 72, multiplayerText, normalFontColor, hoverFontColor, disabledFontColor);
        final SpoutcraftSimpleButton optionsButton = new SpoutcraftSimpleButton(3, getSidebarCenterX(130, optionsText), 89, optionsText, normalFontColor, hoverFontColor, disabledFontColor);
        final SpoutcraftSimpleButton modsButton = new SpoutcraftSimpleButton(4, getSidebarCenterX(130, modsText), 106, modsText, normalFontColor, hoverFontColor, disabledFontColor);
        final SpoutcraftSimpleButton addonsButton = new SpoutcraftSimpleButton(5, getSidebarCenterX(130, addonsText), 123, addonsText, normalFontColor, hoverFontColor, disabledFontColor);
        final SpoutcraftSimpleButton quitButton = new SpoutcraftSimpleButton(6, getSidebarCenterX(130, quitText), height - 15, quitText, normalFontColor, hoverFontColor, disabledFontColor);

        addonsButton.enabled = false;

        buttonList.add(singlePlayerButton);
        buttonList.add(multiplayerButton);
        buttonList.add(optionsButton);
        buttonList.add(modsButton);
        buttonList.add(addonsButton);
        buttonList.add(quitButton);
    }

    protected void actionPerformed(GuiButton button) {
        switch (button.id) {
            case 1: // Singleplayer
                RenderUtil.MINECRAFT.displayGuiScreen(new GuiSelectWorld(this));
                break;
            case 2: // Multiplayer
                RenderUtil.MINECRAFT.displayGuiScreen(new GuiMultiplayer(this));
                break;
            case 3: // Options
                RenderUtil.MINECRAFT.displayGuiScreen(new GuiOptions(this, RenderUtil.MINECRAFT.gameSettings));
                break;
            case 4: // Mods
                RenderUtil.MINECRAFT.displayGuiScreen(new GuiModList(this));
                break;
            case 5: // Addons
                break;
            default:// Quit
                RenderUtil.MINECRAFT.shutdown();
        }
    }

    @Override
    public void drawScreen(int par1, int par2, float par3) {
        // Draw the background with overlay
        float imgAspectRatio = 420 / 240F;
        float currentAspectRatio = width / (float) height;
        if (currentAspectRatio > imgAspectRatio) {
            //Skewed on x axis, expand on y axis to fix
            //420 / 240 = width / newHeight
            int newHeight = 240 * width / 420;
            int newY = height / 2 - newHeight / 2;
            background.drawBackground(0, newY, width, newHeight);
        } else if (currentAspectRatio < imgAspectRatio) {
            //Opposite of above
            //420 / 240 = newWidth / height
            int newWidth = 420 * height / 240;
            int newX = width / 2 - newWidth / 2;
            background.drawBackground(newX, 0, newWidth, height);
        } else {
            background.drawBackground(0, 0, width, height);
        }
        //        RenderUtil.drawGradientLeftRight(0, 0, this.width, this.height, new Color(0xFFFFFF, 0x80), new Color(0xFFFFFF, 0x00));
        //        RenderUtil.drawGradientLeftRight(0, 0, this.width, this.height, new Color(0x00, 0x00), new Color(0xFFFFFF, 0xFF));

        // Draw sidebar gradients - draw two at full width and one at 1 pixel wide to get desired look
        drawGradientRect(width - 130, 0, width, height, Integer.MIN_VALUE, Integer.MIN_VALUE);
        drawGradientRect(width - 130, 0, width, height, Integer.MIN_VALUE, Integer.MIN_VALUE);
        drawGradientRect(width - 130, 0, width - 129, height, Integer.MIN_VALUE, Integer.MIN_VALUE);

        // Draw the Spoutcraft logo
        GL11.glPushMatrix();
        TextureUtil.bind(SPOUTCRAFT_LOGO_TEX);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glTranslatef(width - 125, 13, 0);
        GL11.glScalef(0.45f, 0.45f, 1.0f);
        GL11.glTranslatef(-width + 125, -13, 0);
        GL11.glEnable(GL11.GL_BLEND);
        RenderUtil.drawTexture(width - 125, 13, 256, 67);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glPopMatrix();

        // Draw the Copyright string
        ubuntu.setScale(0.17f);
        ubuntu.drawString("Copyright Mojang AB. Do not distribute!", 4, height - 5);
        super.drawScreen(par1, par2, par3);
    }

    @Override
    protected void keyTyped(char par1, int par2) {
    }

    private int getSidebarCenterX(int sidebarWidth, String text) {
        return ((width - (sidebarWidth / 2)) - RenderUtil.MINECRAFT.fontRenderer.getStringWidth(text)) + (RenderUtil.MINECRAFT.fontRenderer.getStringWidth(text) / 2);
    }
}
