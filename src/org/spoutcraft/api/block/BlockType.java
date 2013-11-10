package org.spoutcraft.api.block;

/**
 * An enum of choices to construct a {@link net.minecraft.block.Block} with in addition to the {@link BlockPrefab}.
 */
public enum BlockType {
	/**
	 * Generic means the prefab will construct a {@link net.minecraft.block.Block}
	 */
	GENERIC,
	/**
	 * Sand means the prefab will construct a {@link net.minecraft.block.BlockSand}
	 */
	SAND;
}
