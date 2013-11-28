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
package org.spoutcraft.api.util;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.imageio.ImageIO;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;

public class RenderUtil {
    public static final Tessellator TESSELLATOR = Tessellator.instance;
    public static final Minecraft MINECRAFT = FMLClientHandler.instance().getClient();

    public static final int DIR_LEFTRIGHT = 0;
    public static final int DIR_UPDOWN = 1;
    public static final int DIR_RIGHTLEFT = 2;
    public static final int DIR_DOWNUP = 3;

    //We'll use VBOS!
    private static final int GRADIENT_BUFF = glGenBuffers();
    //Stride is (x,y,r,g,b,a) * 4 bytes per float
    private static final int GRADIENT_STRIDE = (2 + 4) * 4;
    //4 vertices in the buffer
    private static final int GRADIENT_SIZE = GRADIENT_STRIDE * 4;
    private static final int GRADIENT_VERT_OFF = 0;
    private static final int GRADIENT_COLOR_OFF = 2 * 4;

    public static void create2DRectangleModal(double x, double y, double width, double height, double zLevel) {
        TESSELLATOR.startDrawingQuads();
        TESSELLATOR.addVertexWithUV(x + 0, y + height, zLevel, 0, 1);
        TESSELLATOR.addVertexWithUV(x + width, y + height, zLevel, 1, 1);
        TESSELLATOR.addVertexWithUV(x + width, y + 0, zLevel, 1, 0);
        TESSELLATOR.addVertexWithUV(x + 0, y + 0, zLevel, 0, 0);
    }

    /**
     * Draws a gradient on the screen
     * @param x1 X coordinate of top left corner
     * @param y1 Y coordinate of top left corner
     * @param width Width of gradient
     * @param height Height of gradient
     * @param c1 First color of gradient
     * @param c2 Second color of gradient
     * @param direction Direction of gradient, either DIR_LEFTRIGHT, DIR_UPDOWN, DIR_RIGHTLEFT, or DIR_DOWNUP
     */
    public static void drawGradient(float x1, float y1, float width, float height, Color c1, Color c2, int direction) {
        if(direction == DIR_RIGHTLEFT || direction == DIR_DOWNUP) {
            Color tmpCol = c1;
            c1 = c2;
            c2 = tmpCol;
        }
        float x2 = x1 + width, y2 = y1 + height;
        float r1 = c1.getRF(), g1 = c1.getGF(), b1 = c1.getBF(), a1 = c1.getAF();
        float r2 = c2.getRF(), g2 = c2.getGF(), b2 = c2.getBF(), a2 = c2.getAF();


        glBindBuffer(GL_ARRAY_BUFFER, GRADIENT_BUFF);
        //Orphan previous buffer, allocate new one
        //See the buffer re-specification section on
        //http://www.opengl.org/wiki/Buffer_Object_Streaming
        //for more info on buffer oprhaning
        glBufferData(GL_ARRAY_BUFFER, GRADIENT_SIZE, GL_STREAM_DRAW);

        //We can directory upload data to the buffer
        //by mapping it into ram.
        ByteBuffer buff = glMapBuffer(GL_ARRAY_BUFFER, GL_WRITE_ONLY, GRADIENT_SIZE, null);
        FloatBuffer data = buff.asFloatBuffer();
        switch(direction) {
            case DIR_RIGHTLEFT:
            case DIR_LEFTRIGHT:
                data.put(new float[] {
                    x1, y1, r1, g1, b1, a1,
                    x1, y2, r1, g1, b1, a1,
                    x2, y2, r2, g2, b2, a2,
                    x2, y1, r2, g2, b2, a2
                });
                break;
            case DIR_DOWNUP:
            case DIR_UPDOWN:
                data.put(new float[] {
                    x1, y1, r1, g1, b1, a1,
                    x1, y2, r2, g2, b2, a2,
                    x2, y2, r2, g2, b2, a2,
                    x2, y1, r1, g1, b1, a1
                });
                break;
        }
        //Data is already in the buffer, don't need to flip or anything
        glUnmapBuffer(GL_ARRAY_BUFFER);

        //glBindBuffer(GL_ARRAY_BUFFER, GRADIENT_BUFF);
        glEnableClientState(GL_VERTEX_ARRAY);
        glEnableClientState(GL_COLOR_ARRAY);

        glVertexPointer(2, GL_FLOAT, GRADIENT_STRIDE, GRADIENT_VERT_OFF);
        glColorPointer(4, GL_FLOAT, GRADIENT_STRIDE, GRADIENT_COLOR_OFF);

        //Actually draw the stuff!

        //MC uses a flat shade model, we have to switch it
        glShadeModel(GL_SMOOTH);

        //MC generally expects GL_TEXTURE_2D to be enabled
        //We will disable it until we're done drawing
        glDisable(GL_TEXTURE_2D);
        glDrawArrays(GL_QUADS, 0, 4);
        glEnable(GL_TEXTURE_2D);
        glShadeModel(GL_FLAT);

        glDisableClientState(GL_VERTEX_ARRAY);
        glDisableClientState(GL_COLOR_ARRAY);
        //Unbind the buffer or OpenGL yells at us later
        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }

    /**
     * Draws a horizontal gradient on the screen
     * @param x X coordinate of top left corner
     * @param y Y coordinate of top left corner
     * @param width Width of gradient
     * @param height Height of gradient
     * @param left Left color of gradient
     * @param right Right color of gradient
     */
    public static void drawGradientLeftRight(float x, float y, float width, float height, Color left, Color right) {
        drawGradient(x, y, width, height, left, right, DIR_LEFTRIGHT);
    }

    /**
     * Draws a vertical gradient on the screen
     * @param x X coordinate of top left corner
     * @param y Y coordinate of top left corner
     * @param width Width of gradient
     * @param height Height of gradient
     * @param top Top color of gradient
     * @param bottom Bottom color of gradient
     */
    public static void drawGradientUpDown(float x, float y, float width, float height, Color top, Color bottom) {
        drawGradient(x, y, width, height, top, bottom, DIR_UPDOWN);
    }

}
