package org.spoutcraft.api.block;

import java.io.Serializable;

import org.spoutcraft.api.material.MaterialPrefab;

public class BlockPrefab implements Serializable {
	private final String name, displayName;
	private final MaterialPrefab materialPrefab;

	public BlockPrefab(final String name, final String displayName, final MaterialPrefab materialPrefab) {
		this.name = name;
		this.displayName = displayName;
		this.materialPrefab = materialPrefab;
	}

	public String getName() {
		return name;
	}

	public String getDisplayName() {
		return displayName;
	}

	public MaterialPrefab getMaterialPrefab() {
		return materialPrefab;
	}

	@Override
	public String toString() {
		return "BlockPrefab {name= " + name + ", displayName= " + displayName + " materialPrefab= " + materialPrefab + "}";
	}
}
