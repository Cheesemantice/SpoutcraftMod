package org.spoutcraft.api.material;

public class Material {
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
}
