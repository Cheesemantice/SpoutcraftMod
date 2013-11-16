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

import cpw.mods.fml.client.GuiModList;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSelectWorld;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import org.spoutcraft.api.util.RandomUtil;
import org.spoutcraft.api.util.TimeUtil;

import org.lwjgl.opengl.GL11;

public class SpoutcraftMainMenu extends GuiScreen {
	private static ResourceLocation backgroundImage;
	private static ResourceLocation spoutcraftLogo = new ResourceLocation("spoutcraft", "textures/gui/title/spoutcraft.png");

	public SpoutcraftMainMenu() {
		switch (TimeUtil.getTime()) {
			case "day":
				backgroundImage = new ResourceLocation("spoutcraft", "textures/gui/title/background/day/background_" + RandomUtil.inclusive(1, 29) + ".jpg");
				break;
			case "evening":
				backgroundImage = new ResourceLocation("spoutcraft", "textures/gui/title/background/evening/background_" + RandomUtil.inclusive(1, 16) + ".jpg");
				break;
			case "night":
				backgroundImage = new ResourceLocation("spoutcraft", "textures/gui/title/background/night/background_" + RandomUtil.inclusive(1, 13) + ".jpg");
				break;
		}
	}

	public void initGui() {
		addButtons();
	}

	private void addButtons() {
		String singleplayerText = I18n.getString("menu.singleplayer");
		String multiplayerText = I18n.getString("menu.multiplayer");
		String optionsText = I18n.getString("menu.options");
		String modsText = I18n.getString("Mods");
		String quitText = I18n.getString("menu.quit");

		int normalFontColor = 14737632;
		int hoverFontColor = 5479876;
		int disabledFontColor = -6250336;

		final SpoutcraftSimpleButton singlePlayerButton = new SpoutcraftSimpleButton(1, getSidebarCenterX(110, singleplayerText), 50, singleplayerText, normalFontColor, hoverFontColor, disabledFontColor);
		final SpoutcraftSimpleButton multiplayerButton = new SpoutcraftSimpleButton(2, getSidebarCenterX(110, multiplayerText), 67, multiplayerText, normalFontColor, hoverFontColor, disabledFontColor);
		final SpoutcraftSimpleButton optionsButton = new SpoutcraftSimpleButton(3, getSidebarCenterX(110, optionsText), 84, optionsText, normalFontColor, hoverFontColor, disabledFontColor);
		final SpoutcraftSimpleButton modsButton = new SpoutcraftSimpleButton(4, getSidebarCenterX(110, modsText), 101, modsText, normalFontColor, hoverFontColor, disabledFontColor);
		final SpoutcraftSimpleButton quitButton = new SpoutcraftSimpleButton(5, getSidebarCenterX(110, quitText), height - 15, quitText, normalFontColor, hoverFontColor, disabledFontColor);

		buttonList.add(singlePlayerButton);
		buttonList.add(multiplayerButton);
		buttonList.add(optionsButton);
		buttonList.add(modsButton);
		buttonList.add(quitButton);
	}

	protected void actionPerformed(GuiButton button) {
		switch (button.id) {
			case 1: // Singleplayer
				mc.displayGuiScreen(new GuiSelectWorld(this));
				break;
			case 2: // Multiplayer
				mc.displayGuiScreen(new GuiMultiplayer(this));
				break;
			case 3: // Options
				mc.displayGuiScreen(new GuiOptions(this, mc.gameSettings));
				break;
			case 4: // Mods
				mc.displayGuiScreen(new GuiModList(this));
				break;
			case 5: // Quit
				mc.shutdown();
				break;
		}
	}

	@Override
	public void drawScreen(int par1, int par2, float par3) {
		// Draw the background
		mc.getTextureManager().bindTexture(backgroundImage);
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		drawImage(0, 0, width, height);

		// Draw sidebar gradient - draw one at full width and one at 1 pixel wide to get desired look
		drawGradientRect(width - 110, 0, width, height, Integer.MIN_VALUE, Integer.MIN_VALUE);
		drawGradientRect(width - 110, 0, width - 109, height, Integer.MIN_VALUE, Integer.MIN_VALUE);

		// Draw the Spoutcraft logo
		GL11.glPushMatrix();
		mc.getTextureManager().bindTexture(spoutcraftLogo);
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		GL11.glEnable(GL11.GL_BLEND);
		drawTexturedModalRect(width - 105, 13, 0, 0, 96, 24);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glPopMatrix();

		// Draw the Copyright string
		GL11.glPushMatrix();
		GL11.glTranslatef(2f, height - 8f, 0.0f);
		GL11.glScalef(0.80f, 0.80f, 1.0f);
		GL11.glTranslatef(-2f, -(height - 8f), 0.0f);
		drawString(mc.fontRenderer, "Copyright Mojang AB. Do not distribute!", 2, height - 8, 14737632);
		GL11.glPopMatrix();

		super.drawScreen(par1, par2, par3);
	}

	@Override
	protected void keyTyped(char par1, int par2) {
	}

	private void drawImage(int x, int y, int width, int height) {
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV(x, y + height, 0, 0.0, 1.0);
		tessellator.addVertexWithUV(x + width, y + height, 0, 1.0, 1.0);
		tessellator.addVertexWithUV(x + width, y, 0, 1.0, 0.0);
		tessellator.addVertexWithUV(x, y, 0, 0.0, 0.0);
		tessellator.draw();
	}

	private int getSidebarCenterX(int sidebarWidth, String text) {
		return ((width - (sidebarWidth / 2)) - mc.fontRenderer.getStringWidth(text)) + (mc.fontRenderer.getStringWidth(text) / 2);
	}
}
