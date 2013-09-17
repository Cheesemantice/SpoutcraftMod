package org.spoutcraft.api.material;

public interface MaterialRegistry {
	/**
	 * Puts a new {@link org.spoutcraft.api.material.Material} into the registry.
	 * @param material The material to add
	 * @return The material
	 * @throws IllegalStateException If the material has been added before or if the material provided is null
	 */
	public Material add (Material material);

	/**
	 * Gets a material by its unique name
	 * @param name The name to lookup
	 * @return The material or null if not found
	 */
	public Material get(String name);

	/**
	 * Returns if this registry contains a material with the name provided
	 * @param name The name to lookup
	 * @return True if found, false if not
	 */
	public boolean contains(String name);
}
