package org.spoutcraft.mod.item;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spoutcraft.api.item.ItemPrefab;
import org.spoutcraft.mod.SpoutcraftMod;

public class CustomItem extends Item {
	private final ItemPrefab prefab;

	public CustomItem(int id, ItemPrefab prefab) {
		super(id);
		this.prefab = prefab;
		setCreativeTab(SpoutcraftMod.getCustomTabs());
		setUnlocalizedName("spoutcraft:" + prefab.getIdentifier());
		setTextureName("spoutcraft:" + prefab.getIdentifier());
		setMaxStackSize(prefab.getMaxStackSize());
	}

	public ItemPrefab getPrefab() {
		return prefab;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		super.onItemRightClick(par1ItemStack, par2World, par3EntityPlayer);
		return prefab.onItemRightClick(FMLCommonHandler.instance().getEffectiveSide(), par1ItemStack, par2World, par3EntityPlayer);
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
		super.onLeftClickEntity(stack, player, entity);
		return prefab.onLeftClickEntity(FMLCommonHandler.instance().getEffectiveSide(), stack, player, entity);
	}
}
