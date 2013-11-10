package org.spoutcraft.test.item;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spoutcraft.api.item.ItemPrefab;

public class TestItem extends ItemPrefab {
	public TestItem() {
		super("testitem", "Test Item", 100);
	}

	@Override
	public ItemStack onItemRightClick(Side side, ItemStack stack, World world, EntityPlayer player) {
		if (side.isServer()) {
			world.createExplosion(player, player.posX, player.posY, player.posZ, 3f, true);
		}
		return stack;
	}
}
