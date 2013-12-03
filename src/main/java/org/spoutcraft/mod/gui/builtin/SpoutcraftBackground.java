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

import net.minecraft.util.ResourceLocation;
import org.spoutcraft.api.gl.Texture;
import org.spoutcraft.api.gl.TextureWrap;
import org.spoutcraft.api.util.Color;
import org.spoutcraft.api.util.RandomUtil;
import org.spoutcraft.api.util.RenderUtil;
import org.spoutcraft.api.util.TimeUtil;

import static org.lwjgl.opengl.GL11.*;

public class SpoutcraftBackground {
    private static final Texture BLUR_TEX = new Texture(420, 256);
    private Texture BACKGROUND_TEX = new Texture(randomlyChoose());

    static {
        BLUR_TEX.bind();
        Texture.setWrapU(TextureWrap.CLAMP_TO_EDGE);
        Texture.setWrapV(TextureWrap.CLAMP_TO_EDGE);
    }

    private static ResourceLocation randomlyChoose() {
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

    public void drawBackground(int x, int y, int width, int height) {
        drawBackground(x, y, width, height, null);
    }

    public void drawBackground(int x, int y, int width, int height, Color color) {
        drawBackgroundBlur(x, y, width, height);

        if (color != null) {
            RenderUtil.drawRect(x, y, width, height, color);
        }
    }

    //Applies blur effects and whatnot
    private void drawBackgroundBlur(int x, int y, int width, int height) {
        int dispWidth = RenderUtil.MINECRAFT.displayWidth;
        int dispHeight = RenderUtil.MINECRAFT.displayHeight;
        int viewWidth, viewHeight;
        if (dispWidth >= 420 && dispHeight >= 256) {
            //Screen is large enough for us to just
            //use the texture's size
            viewWidth = 420;
            viewHeight = 256;
        } else {
            if (dispWidth / (float) dispHeight > 420 / 256F) {
                //Screen skewed on x axis
                viewWidth = (int) (dispHeight / 256F * 420F);
                viewHeight = dispHeight;
            } else {
                //Screen skewed on y axis
                viewWidth = dispWidth;
                viewHeight = (int) (dispWidth / 420F * 256F);
            }
        }

        //The buffer texture doesn't change size
        //so we have to calculate what the boundaries are
        //for the texture coordinates
        float mU = viewWidth / 420F;
        float mV = viewHeight / 256F;

        BACKGROUND_TEX.bind();
        glColor3f(1, 1, 1);

        //Setup projection matrix to custom
        glMatrixMode(GL_PROJECTION);
        glPushMatrix();
        glLoadIdentity();
        glOrtho(0, 420, 256, 0, -1, 1);
        glMatrixMode(GL_MODELVIEW);
        glPushMatrix();
        glLoadIdentity();

        //Setup viewport for blurring
        glViewport(0, 0, viewWidth, viewHeight);
        RenderUtil.drawTexture(0, 0, 420, 256);
        BLUR_TEX.bind();
        glEnable(GL_BLEND);
        glColorMask(true, true, true, false);
        for (int i = 0; i < 1; i++) {
            glCopyTexSubImage2D(GL_TEXTURE_2D, 0, 0, 0, 0, 0, viewWidth, viewHeight);
            for (int j = -1; j <= 1; j++) {
                glColor4f(1, 1, 1, (1 / (float) (j + 2)));
                float texOff = (j / (float) viewWidth);
                //glCopyTexSubImage2D flips the texture vertically,
                //so we have to re-flip it here to keep it flipped the right way
                RenderUtil.drawTexture(0, 0, 420, 256, texOff, mV, mU + texOff, 0);
            }
        }
        glColorMask(true, true, true, true);
        glDisable(GL_BLEND);

        glMatrixMode(GL_PROJECTION);
        glPopMatrix();
        glMatrixMode(GL_MODELVIEW);
        glPopMatrix();
        glViewport(0, 0, dispWidth, dispHeight);
        //Also, because the last glCopyTexSubImage2D is not followed by
        //something to re-correct the texture, we have to re-flip it
        //again here to keep it flipped the right way.
        RenderUtil.drawTexture(x, y, width, height, 0, mV, mU, 0);
    }
}
