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
package org.spoutcraft.api.item;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class FoodPrefab extends ItemPrefab {
    private final int healAmount;
    private final int saturationModifier;
    private final boolean wolfFavorite;

    public FoodPrefab(String identifier, String displayName, int maxStackSize, int healAmount, int saturationModifier, boolean wolfFavorite, boolean showInCreativeTab) {
        super(identifier, displayName, maxStackSize, showInCreativeTab);
        this.healAmount = healAmount;
        this.saturationModifier = saturationModifier;
        this.wolfFavorite = wolfFavorite;
    }

    public int getHealAmount() {
        return healAmount;
    }

    public int getSaturationModifier() {
        return saturationModifier;
    }

    public boolean isWolfFavorite() {
        return wolfFavorite;
    }

    public ItemStack onEaten(Side side, ItemStack stack, World world, EntityPlayer player) {
        return stack;
    }

    @Override
    public String toString() {
        final String NEW_LINE = System.getProperty("line.separator");
        final String parent = super.toString();
        final StringBuilder builder = new StringBuilder(parent.substring(0, parent.length() - 1) + NEW_LINE);
        builder
                .append(" Heal Amount: " + healAmount + NEW_LINE)
                .append(" Saturation Modifier: " + saturationModifier + NEW_LINE)
                .append(" Wolf Favorite: " + wolfFavorite + NEW_LINE)
                .append("}");
        return builder.toString();
    }
}
