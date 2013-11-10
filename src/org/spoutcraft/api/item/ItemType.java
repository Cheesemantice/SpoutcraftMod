package org.spoutcraft.api.item;

/**
 * An enum of choices to construct a {@link net.minecraft.item.Item} with in addition to the {@link ItemPrefab}.
 */
public enum ItemType {
	/**
	 * Generic means the prefab will construct a {@link net.minecraft.item.Item}
	 */
	GENERIC,
	/**
	 * FOOD means the prefab will construct a {@link net.minecraft.item.ItemFood}
	 */
	FOOD;
}
