package org.spoutcraft.mod.item.special;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.Explosion;
import org.spoutcraft.api.item.ItemPrefab;

public class SpoutcraftEmblem extends ItemPrefab {
	public SpoutcraftEmblem() {
		super("spoutcraft_emblem", "Spoutcraft Emblem", 1, true);
	}

	@Override
	public boolean onLeftClickEntity(Side side, ItemStack stack, EntityPlayer player, Entity entity) {
		if (side.isServer()) {
			Explosion explosion = entity.worldObj.newExplosion(entity, entity.posX, entity.posY, entity.posZ, 25f, true, true);
			explosion.isFlaming = true;
			explosion.isSmoking = true;
			explosion.doExplosionA();
			explosion.doExplosionB(true);
		}
		return false;
	}
}
