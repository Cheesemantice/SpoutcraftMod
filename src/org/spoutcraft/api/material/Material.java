package org.spoutcraft.api.material;

import java.io.Serializable;

public class Material implements Serializable {
	private final String name;
	private final MapIndex color;

	public Material(String name, MapIndex color) {
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
		return "Material {name= " + name + ", color= " + color.name() + "}";
	}
}
