package org.spoutcraft.api.block;

public interface BlockRegistry {
	/**
	 * Puts a new {@link org.spoutcraft.api.block.Block} into the registry.
	 * @param block The block to put
	 * @return The block
	 * @throws IllegalStateException If the block has been added before or if the block provided is null
	 */
	public Block put(Block block);

	/**
	 * Gets a block by its unique name
	 * @param name The name to lookup
	 * @return The block or null if not found
	 */
	public Block get(String name);

	/**
	 * Returns if this registry contains a block with the name provided
	 * @param name The name to lookup
	 * @return True if found, false if not
	 */
	public boolean contains(String name);
}
