package org.spoutcraft.api.block;

import org.spoutcraft.api.material.Material;

public class Block {
	private final String name, displayName;
	private final Material material;

	public Block(final String name, final String displayName, final Material material) {
		this.name = name;
		this.displayName = displayName;
		this.material = material;
	}

	public String getName() {
		return name;
	}

	public String getDisplayName() {
		return displayName;
	}

	public Material getMaterial() {
		return material;
	}
}
