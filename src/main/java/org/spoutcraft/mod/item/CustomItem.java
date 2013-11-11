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
		setUnlocalizedName("spoutcraft:" + prefab.getIdentifier());
		setTextureName("spoutcraft:" + prefab.getIdentifier());
		setMaxStackSize(prefab.getMaxStackSize());

		if (prefab.shouldShowInCreativeTab()) {
			setCreativeTab(SpoutcraftMod.getCustomTabs());
		}
	}

	public ItemPrefab getPrefab() {
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
}
