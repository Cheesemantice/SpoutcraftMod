package org.spoutcraft.api.item;

import java.io.Serializable;

public class ItemPrefab implements Serializable {
	private final String name, displayName;
	private final int maxStackSize;

	public ItemPrefab(String name, String displayName, int maxStackSize) {
		this.name = name;
		this.displayName = displayName;
		this.maxStackSize = maxStackSize;
	}

	public String getName() {
		return name;
	}

	public String getDisplayName() {
		return displayName;
	}

	public int getMaxStackSize() {
		return maxStackSize;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		final ItemPrefab that = (ItemPrefab) o;

		if (maxStackSize != that.maxStackSize) {
			return false;
		}

		if (!displayName.equals(that.displayName)) {
			return false;
		}

		if (!name.equals(that.name)) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		int result = name.hashCode();
		result = 31 * result + maxStackSize;
		return result;
	}

	@Override
	public String toString() {
		return "ItemPrefab {name= " + name + ", displayName: " + displayName + ", maxStackSize= " + maxStackSize + "}";
	}
}
