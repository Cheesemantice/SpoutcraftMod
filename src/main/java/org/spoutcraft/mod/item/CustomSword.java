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
package org.spoutcraft.mod.item;

import net.minecraft.item.ItemSword;
import org.spoutcraft.api.item.SwordPrefab;
import org.spoutcraft.mod.SpoutcraftMod;

public class CustomSword extends ItemSword {
    private final SwordPrefab prefab;

    CustomSword(int id, SwordPrefab prefab) {
        super(id, prefab.getToolMaterial());
        this.prefab = prefab;
        setUnlocalizedName("spoutcraft:" + prefab.getIdentifier());
        setTextureName("spoutcraft:" + prefab.getIdentifier());
        setMaxStackSize(prefab.getMaxStackSize());

        if (prefab.shouldShowInCreativeTab()) {
            setCreativeTab(SpoutcraftMod.getCustomTabs());
        }
    }

    protected SwordPrefab getPrefab() {
        return prefab;
    }

    @Override
    public float getStrVsBlock(net.minecraft.item.ItemStack par1ItemStack, net.minecraft.block.Block par2Block) {
        return prefab.getStrVsBlock(par1ItemStack, par2Block);
    }

    @Override
    public boolean hitEntity(net.minecraft.item.ItemStack par1ItemStack, net.minecraft.entity.EntityLivingBase par2EntityLivingBase, net.minecraft.entity.EntityLivingBase par3EntityLivingBase) {
        super.hitEntity(par1ItemStack, par2EntityLivingBase, par3EntityLivingBase);
        return prefab.hitEntity(par1ItemStack, par2EntityLivingBase, par3EntityLivingBase);
    }

    @Override
    public boolean onBlockDestroyed(net.minecraft.item.ItemStack par1ItemStack, net.minecraft.world.World par2World, int par3, int par4, int par5, int par6, net.minecraft.entity.EntityLivingBase par7EntityLivingBase) {
        super.onBlockDestroyed(par1ItemStack, par2World, par3, par4, par5, par6, par7EntityLivingBase);
        return prefab.onBlockDestroyed(par1ItemStack, par2World, par3, par4, par5, par6, par7EntityLivingBase);
    }

    @Override
    public int getItemEnchantability() {
        return prefab.getItemEnchantability();
    }

    @Override
    public String getToolMaterialName() {
        return prefab.getToolMaterialName();
    }

    @Override
    public boolean getIsRepairable(net.minecraft.item.ItemStack par1ItemStack, net.minecraft.item.ItemStack par2ItemStack) {
        return prefab.getIsRepairable(par1ItemStack, par2ItemStack);
    }

    public float getStrVsBlock(net.minecraft.item.ItemStack stack, net.minecraft.block.Block block, int meta) {
        return prefab.getStrVsBlock(stack, block, meta);
    }
}
