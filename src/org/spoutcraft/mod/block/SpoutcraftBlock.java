package org.spoutcraft.mod.block;

import net.minecraft.block.Block;
import org.spoutcraft.mod.SpoutcraftMod;
import org.spoutcraft.mod.material.SpoutcraftMaterial;

public class SpoutcraftBlock extends Block {
	private final org.spoutcraft.api.block.Block apiBlock;

	public SpoutcraftBlock(int id, org.spoutcraft.api.block.Block apiBlock, SpoutcraftMaterial material) {
		super(id, material);
		this.apiBlock = apiBlock;
		setUnlocalizedName(apiBlock.getName());
		setCreativeTab(SpoutcraftMod.getSpoutcraftTab());
	}

	public org.spoutcraft.api.block.Block getApiBlock() {
		return apiBlock;
	}
}
