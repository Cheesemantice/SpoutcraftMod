package org.spoutcraft.api;

public interface LinkedPrefabRegistry<T extends Prefab, U> extends PrefabRegistry<T> {
	/**
	 * Creates U from T. This will put the prefab into the registry.
	 *
	 * @param prefab The prefab used for construction
	 * @return U or null if construction failed
	 */
	public U create(T prefab);

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
