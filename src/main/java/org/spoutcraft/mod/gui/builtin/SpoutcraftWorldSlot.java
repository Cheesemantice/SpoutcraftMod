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

import java.util.Date;

import cpw.mods.fml.client.GuiScrollingList;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.world.storage.SaveFormatComparator;
import org.spoutcraft.api.util.RenderUtil;

@SideOnly (Side.CLIENT)
public class SpoutcraftWorldSlot extends GuiScrollingList {
    private SpoutcraftSingleplayer parent;

    public SpoutcraftWorldSlot(SpoutcraftSingleplayer parent) {
        super(RenderUtil.MINECRAFT, parent.width / 2 - 90, parent.height, RenderUtil.MINECRAFT.fontRenderer.FONT_HEIGHT + 4, parent.height - RenderUtil.MINECRAFT.fontRenderer.FONT_HEIGHT - 4, 0, 38);
        this.parent = parent;
    }

    protected int getSize() {
        return SpoutcraftSingleplayer.getSaveList().size();
    }

    protected void elementClicked(int worldId, boolean isDoubleClicked) {
        SpoutcraftSingleplayer.setSelectedWorld(worldId);
        boolean isSelected = SpoutcraftSingleplayer.getSelectedWorld() >= 0 && SpoutcraftSingleplayer.getSelectedWorld() < getSize();
        SpoutcraftSingleplayer.getPlayButton().enabled = isSelected;

        if (isDoubleClicked && isSelected) {
            SpoutcraftSingleplayer.joinWorld(worldId);
        }
    }

    protected boolean isSelected(int par1) {
        return par1 == SpoutcraftSingleplayer.getSelectedWorld();
    }

    protected int getContentHeight() {
        return SpoutcraftSingleplayer.getSaveList().size() * 38;
    }

    protected void drawBackground() {
    }

    protected void drawSlot(int worldId, int par2, int par3, int par4, Tessellator tessellator) {
        SaveFormatComparator saveFormatComparator = (SaveFormatComparator) SpoutcraftSingleplayer.getSaveList().get(worldId);
        String displayName = saveFormatComparator.getDisplayName();

        if (displayName == null || MathHelper.stringNullOrLengthZero(displayName)) {
            displayName = I18n.getString("selectWorld.world") + " " + (worldId + 1);
        }

        String fileName = saveFormatComparator.getFileName() + " (" + SpoutcraftSingleplayer.getDateFormatter().format(new Date(saveFormatComparator.getLastTimePlayed())) + ")";
        String worldInfo;

        if (saveFormatComparator.requiresConversion()) {
            worldInfo = I18n.getString("selectWorld.conversion");
        } else {
            worldInfo = I18n.getString("gameMode." + saveFormatComparator.getEnumGameType().toString().toLowerCase());

            if (saveFormatComparator.isHardcoreModeEnabled()) {
                worldInfo = EnumChatFormatting.DARK_RED + I18n.getString("gameMode.hardcore") + EnumChatFormatting.RESET;
            }

            if (saveFormatComparator.getCheatsEnabled()) {
                worldInfo = worldInfo + ", " + I18n.getString("selectWorld.cheats");
            }
        }

        parent.drawString(RenderUtil.MINECRAFT.fontRenderer, displayName, 4, par3 + 1, 16777215);
        parent.drawString(RenderUtil.MINECRAFT.fontRenderer, fileName, 4, par3 + 12, 8421504);
        parent.drawString(RenderUtil.MINECRAFT.fontRenderer, worldInfo, 4, par3 + 12 + 10, 8421504);
    }
}
