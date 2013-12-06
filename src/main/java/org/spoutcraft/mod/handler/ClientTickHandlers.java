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
package org.spoutcraft.mod.handler;

import java.util.EnumSet;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.*;
import org.spoutcraft.api.util.RenderUtil;
import org.spoutcraft.mod.gui.builtin.SpoutcraftMainMenu;

public class ClientTickHandlers {
    public static void start() {
        // Show our main menu
        TickRegistry.registerTickHandler(new ITickHandler() {
            @Override
            public void tickStart(EnumSet<TickType> type, Object... tickData) {
                final GuiScreen current = RenderUtil.MINECRAFT.currentScreen;
                if (current != null && current.getClass() == GuiMainMenu.class && current.getClass() != SpoutcraftMainMenu.class) {
                    RenderUtil.MINECRAFT.displayGuiScreen(new SpoutcraftMainMenu());
                }
            }

            @Override
            public void tickEnd(EnumSet<TickType> type, Object... tickData) {
            }

            @Override
            public EnumSet<TickType> ticks() {
                return EnumSet.of(TickType.CLIENT);
            }

            @Override
            public String getLabel() {
                return "Spoutcraft - Main Menu Hotswap";
            }
        }, Side.CLIENT);

        // Draw our watermark in game
        TickRegistry.registerTickHandler(new ITickHandler() {
            @Override
            public void tickStart(EnumSet<TickType> type, Object... tickData) {
            }

            @Override
            public void tickEnd(EnumSet<TickType> type, Object... tickData) {
                final GuiScreen current = RenderUtil.MINECRAFT.currentScreen;
                if (current == null) {
                    ScaledResolution scaledResolution = new ScaledResolution(RenderUtil.MINECRAFT.gameSettings, RenderUtil.MINECRAFT.displayWidth, RenderUtil.MINECRAFT.displayHeight);
                    int width = scaledResolution.getScaledWidth();
                    int height = scaledResolution.getScaledHeight();

                    // Draw Spoutcraft logo
                    GL11.glPushMatrix();
                    RenderUtil.MINECRAFT.getTextureManager().bindTexture(new ResourceLocation("spoutcraft", "textures/gui/title/spoutcraft.png"));
                    GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
                    GL11.glEnable(GL11.GL_BLEND);
                    GL11.glTranslatef(width - 45, height - 13, 0.0f);
                    GL11.glScalef(0.17f, 0.17f, 1.0f);
                    GL11.glTranslatef(-width + 45, -height + 13, 0.0f);
                    RenderUtil.create2DRectangleModal(width - 45, height - 13, 256, 67, 0);
                    RenderUtil.TESSELLATOR.draw();
                    GL11.glDisable(GL11.GL_BLEND);
                    GL11.glPopMatrix();

                    // Draw milestone string
                    GL11.glPushMatrix();
                    GL11.glTranslatef(width - 14, height - 8, 0.0f);
                    GL11.glScalef(0.50f, 0.50f, 1.0f);
                    GL11.glTranslatef(-width + 14, -height + 8, 0.0f);
                    RenderUtil.MINECRAFT.fontRenderer.drawString("Alpha", width - 14, height - 3, 16776960);
                    GL11.glPopMatrix();
                }
            }

            @Override
            public EnumSet<TickType> ticks() {
                return EnumSet.of(TickType.RENDER);
            }

            @Override
            public String getLabel() {
                return "Spoutcraft - Watermark";
            }
        }, Side.CLIENT);
    }
}
