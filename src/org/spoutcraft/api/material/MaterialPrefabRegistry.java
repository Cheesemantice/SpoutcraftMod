package org.spoutcraft.api.material;

public interface MaterialPrefabRegistry {
	/**
	 * Puts a new {@link MaterialPrefab} into the registry.
	 *
	 * @param materialPrefab The materialPrefab to add
	 * @return The materialPrefab
	 * @throws IllegalStateException If the materialPrefab has been added before or if the materialPrefab provided is null
	 */
	public MaterialPrefab add(MaterialPrefab materialPrefab);

	/**
	 * Gets a material by its unique name
	 *
	 * @param name The name to lookup
	 * @return The material or null if not found
	 */
	public MaterialPrefab get(String name);

	/**
	 * Returns if this registry contains a material with the name provided
	 *
	 * @param name The name to lookup
	 * @return True if found, false if not
	 */
	public boolean contains(String name);
}
