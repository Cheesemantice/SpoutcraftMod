package org.spoutcraft.api;

public interface PrefabRegistry <T extends Prefab> {
	/**
	 * Puts a new {@link Prefab} into the registry.
	 *
	 * @param prefab The prefab to put
	 * @return The prefab
	 * @throws IllegalStateException If the prefab has been added before or if the prefab provided is null
	 */
	public T put(T prefab);

	/**
	 * Gets a T by its unique identifier
	 *
	 * @param identifier The identifier to lookup
	 * @return The prefab or null if not found
	 */
	public T get(String identifier);
}
