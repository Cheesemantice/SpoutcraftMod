package org.spoutcraft.mod.item.special;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spoutcraft.api.item.ItemPrefab;

public class SpoutcraftEmblem extends ItemPrefab {
	public SpoutcraftEmblem() {
		super("spoutcraft_emblem", "Spoutcraft Emblem", 1);
	}

	@Override
	public boolean onLeftClickEntity(Side side, ItemStack stack, EntityPlayer player, Entity entity) {
		return false;
	}

	@Override
	public ItemStack onItemRightClick(Side side, ItemStack stack, World world, EntityPlayer player) {
		return stack;
	}

	@Override
	public void onUpdate(Side side, ItemStack stack, World world, Entity entity, int slot, boolean isCurrentlyHeldItem) {
	}

	@Override
	public void onCraftOrSmelt(Side side, ItemStack stack, World world, EntityPlayer player) {
	}

	@Override
	public void onPlayerStoppedUsing(Side side, ItemStack stack, World world, EntityPlayer player, int ticksItemHasBeenUsed) {
	}
}
