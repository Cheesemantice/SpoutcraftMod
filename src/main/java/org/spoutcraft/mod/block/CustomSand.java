package org.spoutcraft.mod.block;

import net.minecraft.block.BlockSand;
import org.spoutcraft.api.block.MovingPrefab;
import org.spoutcraft.mod.SpoutcraftMod;
import org.spoutcraft.mod.material.CustomMaterial;

public class CustomSand extends BlockSand {
	private final MovingPrefab prefab;

	public CustomSand(int id, MovingPrefab prefab, CustomMaterial material) {
		super(id, material);
		this.prefab = prefab;
		setUnlocalizedName(prefab.getIdentifier());
		setTextureName("spoutcraft:" + prefab.getIdentifier());
		setHardness(prefab.getHardness());

		if (prefab.shouldShowInCreativeTab()) {
			setCreativeTab(SpoutcraftMod.getCustomTabs());
		}
	}

	public MovingPrefab getPrefab() {
		return prefab;
	}
}
