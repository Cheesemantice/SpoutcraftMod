/**
 * This file is part of SpoutcraftMod, licensed under the MIT License (MIT).
 *
 * Copyright (c) 2013 SpoutcraftDev <http://spoutcraft.org/>
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

import net.minecraft.item.EnumToolMaterial;
import org.spoutcraft.api.addon.Addon;

public class AxePrefab extends ItemPrefab {
    private static final long serialVersionUID = 5904596344964845926L;
    private final EnumToolMaterial toolMaterial;

    public AxePrefab(Addon addon, String identifier, String displayName, int maxStackSize, boolean showInCreativeTab, EnumToolMaterial toolMaterial) {
        super(addon, identifier, displayName, maxStackSize, showInCreativeTab);
        this.toolMaterial = toolMaterial;
    }

    public EnumToolMaterial getToolMaterial() {
        return toolMaterial;
    }

    public float getStrVsBlock(net.minecraft.item.ItemStack par1ItemStack, net.minecraft.block.Block par2Block) {
        return 1f;
    }

    public boolean hitEntity(net.minecraft.item.ItemStack par1ItemStack, net.minecraft.entity.EntityLivingBase par2EntityLivingBase, net.minecraft.entity.EntityLivingBase par3EntityLivingBase) {
        return true;
    }

    public boolean onBlockDestroyed(net.minecraft.item.ItemStack par1ItemStack, net.minecraft.world.World par2World, int par3, int par4, int par5, int par6, net.minecraft.entity.EntityLivingBase par7EntityLivingBase) {
        return true;
    }

    public int getItemEnchantability() {
        return getToolMaterial().getEnchantability();
    }

    public String getToolMaterialName() {
        return getToolMaterial().toString();
    }

    public boolean getIsRepairable(net.minecraft.item.ItemStack par1ItemStack, net.minecraft.item.ItemStack par2ItemStack) {
        return false;
    }

    public float getStrVsBlock(net.minecraft.item.ItemStack stack, net.minecraft.block.Block block, int meta) {
        return 1f;
    }
}
