package org.spoutcraft.api.block;

public interface BlockPrefabRegistry {
	/**
	 * Puts a new {@link BlockPrefab} into the registry.
	 *
	 * @param blockPrefab The block to put
	 * @return The block
	 * @throws IllegalStateException If the block has been added before or if the block provided is null
	 */
	public BlockPrefab put(BlockPrefab blockPrefab);

	/**
	 * Gets a block by its unique name
	 *
	 * @param name The name to lookup
	 * @return The block or null if not found
	 */
	public BlockPrefab get(String name);

	/**
	 * Returns if this registry contains a block with the name provided
	 *
	 * @param name The name to lookup
	 * @return True if found, false if not
	 */
	public boolean contains(String name);
}
