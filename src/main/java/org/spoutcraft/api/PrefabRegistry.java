package org.spoutcraft.api;

public interface PrefabRegistry<T extends Prefab, U> {
	/**
	 * Puts a new {@link Prefab} into the registry.
	 *
	 * @param prefab The prefab to put
	 * @return The prefab
	 * @throws IllegalStateException If the prefab has been added before or if the prefab provided is null
	 */
	public T put(T prefab);

	/**
	 * Creates U from T. This will put the prefab into the registry.
	 *
	 * @param prefab The prefab used for construction
	 * @return U or null if construction failed
	 */
	public U create(T prefab);

	/**
	 * Gets a T by its unique identifier
	 *
	 * @param identifier The identifier to lookup
	 * @return The prefab or null if not found
	 */
	public T get(String identifier);

	/**
	 * Finds U matched to T.
	 *
	 * @param prefab The prefab to look for the U match
	 * @return U or null
	 */
	public U find(T prefab);

	/**
	 * Finds U matched to a prefab's identifier
	 *
	 * @param identifier The prefab's identifier
	 * @return U or null
	 */
	public U find(String identifier);
}
