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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.AnvilConverterException;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.stats.StatList;
import net.minecraft.world.storage.SaveFormatComparator;
import org.spoutcraft.api.Spoutcraft;
import org.spoutcraft.api.util.RenderUtil;

@SideOnly (Side.CLIENT)
public class SpoutcraftSingleplayer extends GuiScreen {
    private static SpoutcraftSimpleButton backButton, playButton, createButton, editButton;
    private static int selectedWorldId = -1;
    private static List saveList;
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
    private SpoutcraftWorldSlot worldSlot;
    private SpoutcraftBackground background = new SpoutcraftBackground();
    private GuiScreen parent;
    private FontRenderer fontRenderer = RenderUtil.MINECRAFT.fontRenderer;
    private String titleText = I18n.getString("menu.singleplayer");

    public SpoutcraftSingleplayer(GuiScreen parent) {
        this.parent = parent;
        background.selectBackground();
        titleText = I18n.getString("menu.singleplayer");
    }

    public void initGui() {
        final String backText = I18n.getString("gui.back");
        final String createText = I18n.getString("mco.create.world");
        final String editText = I18n.getString("selectServer.edit");
        final String playText = I18n.getString("mco.selectServer.play");
        int xPosition = fontRenderer.getStringWidth(playText) + 3;
        int yPosition = height - fontRenderer.FONT_HEIGHT - 1;

        try {
            getSaves();
        } catch (AnvilConverterException e) {
            Spoutcraft.getLogger().severe("Unable to load saves.");
        }

        backButton = new SpoutcraftSimpleButton(1, 3, yPosition, backText);
        playButton = new SpoutcraftSimpleButton(2, width - xPosition, yPosition, playText);
        xPosition += fontRenderer.getStringWidth(editText) + 6;
        editButton = new SpoutcraftSimpleButton(3, width - xPosition, yPosition, editText);
        xPosition += fontRenderer.getStringWidth(createText) + 6;
        createButton = new SpoutcraftSimpleButton(4, width - xPosition, yPosition, createText);

        buttonList.add(backButton);
        buttonList.add(playButton);
        buttonList.add(editButton);
        buttonList.add(createButton);

        worldSlot = new SpoutcraftWorldSlot(this);
        worldSlot.registerScrollButtons(saveList, 5, 6);
    }

    protected void actionPerformed(GuiButton button) {
        switch (button.id) {
            case 1: // Back
                RenderUtil.MINECRAFT.displayGuiScreen(parent);
                break;
            case 2: // Play
                joinWorld(selectedWorldId);
                break;
            case 3: // Edit
                break;
            case 4: // Create
                RenderUtil.MINECRAFT.displayGuiScreen(new SpoutcraftCreateWorld(this));
                break;
        }
    }

    @Override
    public void drawScreen(int par1, int par2, float par3) {
        // Draw the background with an overlay
        background.drawBackground(0, 0, width, height, Integer.MIN_VALUE, Integer.MIN_VALUE);

        // Draw our world slots
        worldSlot.drawScreen(par1, par2, par3);

        // Draw top and bottom gradients drawn times each to get desired darkness
        drawGradientRect(0, 0, width, fontRenderer.FONT_HEIGHT + 4, Integer.MIN_VALUE, Integer.MIN_VALUE);
        drawGradientRect(0, 0, width, fontRenderer.FONT_HEIGHT + 4, Integer.MIN_VALUE, Integer.MIN_VALUE);
        drawGradientRect(0, 0, width, fontRenderer.FONT_HEIGHT + 4, Integer.MIN_VALUE, Integer.MIN_VALUE);
        drawGradientRect(0, 0, width, fontRenderer.FONT_HEIGHT + 4, Integer.MIN_VALUE, Integer.MIN_VALUE);
        drawGradientRect(0, 0, width, fontRenderer.FONT_HEIGHT + 4, Integer.MIN_VALUE, Integer.MIN_VALUE);
        drawGradientRect(0, height - (fontRenderer.FONT_HEIGHT + 4), width, height, Integer.MIN_VALUE, Integer.MIN_VALUE);
        drawGradientRect(0, height - (fontRenderer.FONT_HEIGHT + 4), width, height, Integer.MIN_VALUE, Integer.MIN_VALUE);
        drawGradientRect(0, height - (fontRenderer.FONT_HEIGHT + 4), width, height, Integer.MIN_VALUE, Integer.MIN_VALUE);
        drawGradientRect(0, height - (fontRenderer.FONT_HEIGHT + 4), width, height, Integer.MIN_VALUE, Integer.MIN_VALUE);
        drawGradientRect(0, height - (fontRenderer.FONT_HEIGHT + 4), width, height, Integer.MIN_VALUE, Integer.MIN_VALUE);

        // Draw screen title string
        drawString(RenderUtil.MINECRAFT.fontRenderer, titleText, (width / 2) - (fontRenderer.getStringWidth(titleText) / 2), 3, 14737632);

        // Only enable when a world is selected
        playButton.enabled = selectedWorldId >= 0;
        editButton.enabled = selectedWorldId >= 0;

        super.drawScreen(par1, par2, par3);
    }

    protected static void joinWorld(int worldId) {
        RenderUtil.MINECRAFT.displayGuiScreen(null);

        if (selectedWorldId >= 0) {
            String fileName = getSaveFile(worldId);
            String worldName = getSaveName(worldId);

            if (fileName == null) {
                fileName = "World" + worldId;
            }

            if (worldName == null) {
                worldName = "World" + worldId;
            }

            if (RenderUtil.MINECRAFT.getSaveLoader().canLoadWorld(fileName)) {
                RenderUtil.MINECRAFT.launchIntegratedServer(fileName, worldName, null);
                RenderUtil.MINECRAFT.statFileWriter.readStat(StatList.loadWorldStat, 1);
            }
        }
    }

    protected static SpoutcraftSimpleButton getPlayButton() {
        return playButton;
    }

    protected static List getSaves() throws AnvilConverterException {
        saveList = RenderUtil.MINECRAFT.getSaveLoader().getSaveList();
        Collections.sort(saveList);
        selectedWorldId = -1;
        return saveList;
    }

    protected static List getSaveList() {
        return saveList;
    }

    protected static int getSelectedWorld() {
        return selectedWorldId;
    }

    protected static void setSelectedWorld(int worldId) {
        selectedWorldId = worldId;
    }

    protected static String getSaveFile(int id) {
        return ((SaveFormatComparator) saveList.get(id)).getFileName();
    }

    protected static String getSaveName(int worldId) {
        String saveName = ((SaveFormatComparator) saveList.get(worldId)).getDisplayName();

        if (saveName == null || saveName.isEmpty()) {
            saveName = I18n.getString("selectWorld.world") + " " + (worldId + 1);
        }

        return saveName;
    }

    protected static DateFormat getDateFormatter() {
        return simpleDateFormat;
    }
}
