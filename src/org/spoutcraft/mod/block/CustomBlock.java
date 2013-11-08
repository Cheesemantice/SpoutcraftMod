package org.spoutcraft.mod.block;

import net.minecraft.block.Block;
import org.spoutcraft.api.block.BlockPrefab;
import org.spoutcraft.mod.SpoutcraftMod;
import org.spoutcraft.mod.material.CustomMaterial;

public class CustomBlock extends Block {
	private final BlockPrefab apiBlockPrefab;

	public CustomBlock(int id, BlockPrefab apiBlockPrefab, CustomMaterial material) {
		super(id, material);
		this.apiBlockPrefab = apiBlockPrefab;
		setUnlocalizedName(apiBlockPrefab.getName());
		setCreativeTab(SpoutcraftMod.getCustomTabs());
		func_111022_d("Spoutcraft:" + apiBlockPrefab.getName());
	}

	public BlockPrefab getApiPrefab() {
		return apiBlockPrefab;
	}
}
