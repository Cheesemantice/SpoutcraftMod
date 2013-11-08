package org.spoutcraft.mod.material;

import java.util.ArrayList;

import org.spoutcraft.api.material.MaterialPrefab;
import org.spoutcraft.api.material.MaterialPrefabRegistry;

public class SpoutcraftMaterialPrefabRegistry implements MaterialPrefabRegistry {
	private final ArrayList<MaterialPrefab> registry = new ArrayList<>();

	@Override
	public MaterialPrefab add(MaterialPrefab materialPrefab) {
		if (materialPrefab == null) {
			throw new IllegalStateException("Attempt to add null materialPrefab to registry!");
		}
		if (contains(materialPrefab.getName())) {
			throw new IllegalStateException("Attempt to add duplicate materialPrefab to registry!");
		}
		registry.add(materialPrefab);
		return materialPrefab;
	}

	@Override
	public MaterialPrefab get(String name) {
		if (name != null && name.isEmpty()) {

			for (MaterialPrefab materialPrefab : registry) {
				if (materialPrefab.getName().equals(name)) {
					return materialPrefab;
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
