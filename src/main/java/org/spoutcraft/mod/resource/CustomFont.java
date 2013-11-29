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
package org.spoutcraft.mod.resource;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import net.minecraft.client.renderer.Tessellator;
import org.lwjgl.opengl.GL11;
import org.spoutcraft.api.util.TextureUtil;

public class CustomFont {
    public final int fontHeight;
    private final int fontTexture;
    private final int[] fontWidth;
    private final float[] fontWidthF;
    private float scale = 1F;
    private int red = 0xFF;
    private int green = 0xFF;
    private int blue = 0xFF;
    private int alpha = 0xFF;

    public CustomFont(Font fnt) {
        //Used for getting font metrics
        BufferedImage ctxImg = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D ctxGfx = ctxImg.createGraphics();
        ctxGfx.setFont(fnt);
        FontRenderContext ctx = ctxGfx.getFontRenderContext();
        fontWidth = new int[256];
        fontWidthF = new float[256];
        int maxWidth = 0;
        int maxHeight = 0;
        for (int i = 0; i < 256; i++) {
            Rectangle2D bounds = fnt.getStringBounds(((char) i) + "", ctx);
            fontWidth[i] = (int) Math.ceil(bounds.getWidth());
            maxWidth = Math.max(maxWidth, fontWidth[i]);
            maxHeight = Math.max(maxHeight, (int) Math.ceil(bounds.getHeight()));
        }
        fontHeight = maxHeight;
        int imgWidth = maxWidth * 16;
        int imgHeight = maxHeight * 16;
        for (int i = 0; i < 256; i++) {
            fontWidthF[i] = fontWidth[i] / (float) imgWidth;
        }
        BufferedImage fontImg = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = fontImg.createGraphics();
        g2d.setFont(fnt);
        g2d.setColor(new Color(1F, 1F, 1F, 0F));
        g2d.fillRect(0, 0, imgWidth, imgHeight); //Fill image with transparent white
        g2d.setColor(Color.WHITE);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
        for (int i = 0; i < 256; i++) {
            int x = (i % 16) * maxWidth;
            //int y = (i / 16) * maxHeight + maxHeight - fnt.getBaselineFor((char)i);
            //
            //The getY() of the string bounds returns negative, I'm pretty sure it has the baseline
            //as 0 or something. This works, so no worries. If for some reason it doesnt work, tell unknownloner
            int y = (i / 16) * maxHeight - (int) Math.ceil(fnt.getStringBounds(((char) i) + "", ctx).getY());
            g2d.drawString(((char) i) + "", x, y);
        }
        ctxGfx.dispose();
        g2d.dispose();
        this.fontTexture = TextureUtil.loadTexture(fontImg);
        TextureUtil.bind(this.fontTexture);
        TextureUtil.setMinFilter(GL11.GL_NEAREST);
        TextureUtil.setMagFilter(GL11.GL_NEAREST);
    }

    public CustomFont setFilter(int min, int mag) {
        TextureUtil.bind(this.fontTexture);
        TextureUtil.setMinFilter(min);
        TextureUtil.setMagFilter(mag);
        return this;
    }

    public void setColor(int r, int g, int b) {
        setColor(r, g, b, 0xFF);
    }

    public void setColor(int r, int g, int b, int a) {
        this.red = r;
        this.green = g;
        this.blue = b;
        this.alpha = a;
    }

    //This is basically the size relative to the cell height
    //So if you have a 32pt font, setting this to 16 will not
    //give you a scale of 0.5, it'll be something different.
    //Just try to stick with setScale
    public void setSize(int size) {
        this.scale = size / (float) fontHeight;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public void drawString(String str, int x, int y) {
        int len = str.length();
        float height = getHeight();

        TextureUtil.bind(fontTexture);
        Tessellator tes = Tessellator.instance;
        tes.startDrawingQuads();
        tes.setColorRGBA(red, green, blue, alpha);
        tes.setTranslation(x, y, 0);
        for (int i = 0; i < len; i++) {
            char c = str.charAt(i);
            float width = getWidth(c);
            if (width == 0) {
                continue;
            }
            float u0 = c % 16 / 16F;
            float u1 = u0 + getWidthF(c);
            float v0 = c / 16 / 16F;
            float v1 = v0 + 0.0625F; //cellHeight;

            tes.addVertexWithUV(0, 0, 0, u0, v0);
            tes.addVertexWithUV(0, height, 0, u0, v1);
            tes.addVertexWithUV(width, height, 0, u1, v1);
            tes.addVertexWithUV(width, 0, 0, u1, v0);
            tes.addTranslation(width, 0, 0);
        }
        tes.draw();
        tes.setColorRGBA(0xFF, 0xFF, 0xFF, 0xFF);
        tes.setTranslation(0, 0, 0);
    }

    public float getHeight() {
        return scale * fontHeight;
    }

    public int getWidth(String str) {
        int len = str.length();
        int width = 0;
        for (int i = 0; i < len; i++) {
            width += getWidth(str.charAt(i));
        }
        return width;
    }

    public float getWidth(char c) {
        if (c > 0xFF) {
            return 0;
        }
        return scale * this.fontWidth[c];
    }

    private float getWidthF(char c) {
        return this.fontWidthF[c];
    }
}
