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

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spoutcraft.api.item.FoodPrefab;
import org.spoutcraft.mod.SpoutcraftMod;

public class CustomFood extends ItemFood {
    private final FoodPrefab prefab;

    public CustomFood(int id, FoodPrefab prefab) {
        super(id, prefab.getHealAmount(), prefab.getSaturationModifier(), prefab.isWolfFavorite());
        this.prefab = prefab;
        setUnlocalizedName("spoutcraft:" + prefab.getIdentifier());
        setTextureName("spoutcraft:" + prefab.getIdentifier());
        setMaxStackSize(prefab.getMaxStackSize());

        if (prefab.shouldShowInCreativeTab()) {
            setCreativeTab(SpoutcraftMod.getCustomTabs());
        }
    }

    public FoodPrefab getPrefab() {
        return prefab;
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        super.onLeftClickEntity(stack, player, entity);
        return prefab.onLeftClickEntity(FMLCommonHandler.instance().getEffectiveSide(), stack, player, entity);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        super.onItemRightClick(par1ItemStack, par2World, par3EntityPlayer);
        return prefab.onItemRightClick(FMLCommonHandler.instance().getEffectiveSide(), par1ItemStack, par2World, par3EntityPlayer);
    }

    @Override
    public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) {
        super.onUpdate(par1ItemStack, par2World, par3Entity, par4, par5);
        prefab.onUpdate(FMLCommonHandler.instance().getEffectiveSide(), par1ItemStack, par2World, par3Entity, par4, par5);
    }

    @Override
    public void onCreated(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        super.onCreated(par1ItemStack, par2World, par3EntityPlayer);
        prefab.onCraftOrSmelt(FMLCommonHandler.instance().getEffectiveSide(), par1ItemStack, par2World, par3EntityPlayer);
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, int par4) {
        super.onPlayerStoppedUsing(par1ItemStack, par2World, par3EntityPlayer, par4);
        prefab.onPlayerStoppedUsing(FMLCommonHandler.instance().getEffectiveSide(), par1ItemStack, par2World, par3EntityPlayer, par4);
    }

    @Override
    public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        super.onEaten(par1ItemStack, par2World, par3EntityPlayer);
        prefab.onEaten(FMLCommonHandler.instance().getEffectiveSide(), par1ItemStack, par2World, par3EntityPlayer);
        return par1ItemStack;
    }
}
