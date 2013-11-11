package org.spoutcraft.test.item;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spoutcraft.api.item.FoodPrefab;

public class TestFood extends FoodPrefab {
	public TestFood() {
		super("testfood", "Test Food", 5, 2, 10, false, true);
	}

	@Override
	public boolean onLeftClickEntity(Side side, ItemStack stack, EntityPlayer player, Entity entity) {
		return false;
	}

	@Override
	public ItemStack onItemRightClick(Side side, ItemStack stack, World world, EntityPlayer player) {
		if (side.isServer()) {
			player.addChatMessage("You right click'd with the prefab: " + getIdentifier());
		}
		return stack;
	}

	@Override
	public ItemStack onEaten(Side side, ItemStack stack, World world, EntityPlayer player) {
		if (side.isServer()) {
			world.createExplosion(player, player.posX, player.posY, player.posZ, 3f, true);
		}
		return stack;
	}
}
