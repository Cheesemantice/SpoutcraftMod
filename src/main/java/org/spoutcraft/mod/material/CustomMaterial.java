package org.spoutcraft.mod.material;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import org.spoutcraft.api.material.MaterialPrefab;

public class CustomMaterial extends Material {
	private final MaterialPrefab prefab;

	public CustomMaterial(MaterialPrefab prefab) {
		super(MapColor.mapColorArray[prefab.getMapIndex().getIndex()]);
		this.prefab = prefab;
	}

	public MaterialPrefab getPrefab() {
		return prefab;
	}
}
