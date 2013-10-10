package org.spoutcraft.mod.entity;

import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.world.World;

public class SpoutcraftEntity extends EntityZombie {
	public SpoutcraftEntity(World par1World) {
		super(par1World);
		this.setCustomNameTag("Spoutcraft Zombie");
	}

	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();
	}
}
