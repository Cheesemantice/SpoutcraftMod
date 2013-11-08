package org.spoutcraft.api.item;

public interface ItemPrefabRegistry {
	/**
	 * Puts a new {@link ItemPrefab} into the registry.
	 *
	 * @param prefab The prefab to put
	 * @return The prefab
	 * @throws IllegalStateException If the prefab has been added before or if the prefab provided is null
	 */
	public ItemPrefab put(ItemPrefab prefab);

	/**
	 * Gets an {@link ItemPrefab} by its unique name
	 *
	 * @param name The name to lookup
	 * @return The prefab or null if not found
	 */
	public ItemPrefab get(String name);

	/**
	 * Returns if this registry contains an {@link ItemPrefab} with the name provided
	 *
	 * @param name The name to lookup
	 * @return True if found, false if not
	 */
	public boolean contains(String name);
}
