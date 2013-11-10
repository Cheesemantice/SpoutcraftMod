package org.spoutcraft.mod.item;

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
		setCreativeTab(SpoutcraftMod.getCustomTabs());
		setUnlocalizedName("spoutcraft:" + prefab.getIdentifier());
		setTextureName("spoutcraft:" + prefab.getIdentifier());
		setMaxStackSize(prefab.getMaxStackSize());
	}

	public FoodPrefab getPrefab() {
		return prefab;
	}

	@Override
	public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		super.onEaten(par1ItemStack, par2World, par3EntityPlayer);
		return prefab.onEaten(par1ItemStack, par2World, par3EntityPlayer);
	}
}
