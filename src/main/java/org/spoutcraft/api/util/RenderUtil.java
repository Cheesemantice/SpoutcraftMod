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

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;

public class RenderUtil {
    public static final Tessellator TESSELLATOR = Tessellator.instance;
    public static final Minecraft MINECRAFT = FMLClientHandler.instance().getClient();

    public static void create2DRectangleModal(double x, double y, double width, double height, double zLevel) {
        TESSELLATOR.startDrawingQuads();
        TESSELLATOR.addVertexWithUV(x + 0, y + height, zLevel, 0, 1);
        TESSELLATOR.addVertexWithUV(x + width, y + height, zLevel, 1, 1);
        TESSELLATOR.addVertexWithUV(x + width, y + 0, zLevel, 1, 0);
        TESSELLATOR.addVertexWithUV(x + 0, y + 0, zLevel, 0, 0);
    }
}
