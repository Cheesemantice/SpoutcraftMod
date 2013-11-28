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

import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;
import org.spoutcraft.api.util.RandomUtil;
import org.spoutcraft.api.util.RenderUtil;
import org.spoutcraft.api.util.TimeUtil;

import org.lwjgl.opengl.GL11;

public class SpoutcraftBackground extends Gui {
	private static ResourceLocation location = selectBackground();

	private static ResourceLocation selectBackground() {
		switch (TimeUtil.getTime()) {
			case "day":
				return new ResourceLocation("spoutcraft", "textures/gui/title/background/day/background_" + RandomUtil.inclusive(1, 29) + ".jpg");
			case "evening":
				return new ResourceLocation("spoutcraft", "textures/gui/title/background/day/background_" + RandomUtil.inclusive(1, 29) + ".jpg");
			case "night":
				return new ResourceLocation("spoutcraft", "textures/gui/title/background/day/background_" + RandomUtil.inclusive(1, 29) + ".jpg");
			default:
				return new ResourceLocation("spoutcraft", "textures/gui/title/background/day/background_" + RandomUtil.inclusive(1, 29) + ".jpg");
		}
	}

	public void drawBackground(int x, int y, int width, int height, boolean overlay) {
		RenderUtil.MINECRAFT.getTextureManager().bindTexture(location);
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		RenderUtil.create2DRectangleModal(x, y, width, height, 0);
		RenderUtil.TESSELLATOR.draw();

		if (overlay) {
			drawGradientRect(0, 0, width, height, Integer.MAX_VALUE, 0);
		}
	}
}
