package org.spoutcraft.mod.block;

import net.minecraft.block.Block;
import org.spoutcraft.api.block.BlockPrefab;
import org.spoutcraft.mod.SpoutcraftMod;
import org.spoutcraft.mod.material.CustomMaterial;

public class CustomBlock extends Block {
	private final BlockPrefab prefab;

	public CustomBlock(int id, BlockPrefab prefab, CustomMaterial material) {
		super(id, material);
		this.prefab = prefab;
		setUnlocalizedName(prefab.getIdentifier());
		setTextureName("spoutcraft:" + prefab.getIdentifier());
		setHardness(prefab.getHardness());

		if (prefab.shouldShowInCreativeTab()) {
			setCreativeTab(SpoutcraftMod.getCustomTabs());
		}
	}

	public BlockPrefab getPrefab() {
		return prefab;
	}
}
