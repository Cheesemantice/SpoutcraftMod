package org.spoutcraft.mod.material;

import java.util.ArrayList;

import org.spoutcraft.api.material.Material;
import org.spoutcraft.api.material.MaterialRegistry;

public class SpoutcraftMaterialRegistry implements MaterialRegistry {
	private final ArrayList<Material> registry = new ArrayList<>();

	@Override
	public Material add(Material material) {
		if (material == null) {
			throw new IllegalStateException("Attempt to add null material to registry!");
		}
		if (contains(material.getName())) {
			throw new IllegalStateException("Attempt to add duplicate material to registry!");
		}
		registry.add(material);
		return material;
	}

	@Override
	public Material get(String name) {
		if (name != null && name.isEmpty()) {

			for (Material material : registry) {
				if (material.getName().equals(name)) {
					return material;
				}
			}
		}
		return null;
	}

	@Override
	public boolean contains(String name) {
		return get(name) != null;
	}
}
