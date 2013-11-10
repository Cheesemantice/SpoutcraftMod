package org.spoutcraft.mod.item.special;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spoutcraft.api.item.FoodPrefab;

public class VanillaEmblem extends FoodPrefab {
	public VanillaEmblem() {
		super("vanilla_emblem", "Vanilla Emblem", 64, 5, 5, true);
	}

	@Override
	public ItemStack onEaten(Side side, ItemStack stack, World world, EntityPlayer player) {
		if (side.isServer()) {
			player.addChatMessage("Eating that reminds you of the good ole days...");
		}
		return stack;
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
