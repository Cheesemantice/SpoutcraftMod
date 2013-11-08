package org.spoutcraft.mod.material;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import org.spoutcraft.api.material.MaterialPrefab;

public class CustomMaterial extends Material {
	private final MaterialPrefab apiMaterialPrefab;

	public CustomMaterial(MaterialPrefab apiMaterialPrefab) {
		super(MapColor.mapColorArray[apiMaterialPrefab.getMapColorIndex().getIndex()]);
		this.apiMaterialPrefab = apiMaterialPrefab;
	}

	public MaterialPrefab getApiPrefab() {
		return apiMaterialPrefab;
	}
}
