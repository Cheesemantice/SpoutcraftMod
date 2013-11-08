package org.spoutcraft.api.material;

import java.io.Serializable;

public class MaterialPrefab implements Serializable {
	private final String name;
	private final MapIndex color;

	public MaterialPrefab(String name, MapIndex color) {
		this.name = name;
		this.color = color;
	}

	public String getName() {
		return name;
	}

	public MapIndex getMapColorIndex() {
		return color;
	}

	@Override
	public String toString() {
		return "MaterialPrefab {name= " + name + ", color= " + color.name() + "}";
	}
}
