package org.spoutcraft.mod.material;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class SpoutcraftMaterial extends Material {
	private final org.spoutcraft.api.material.Material apiMaterial;

	public SpoutcraftMaterial(org.spoutcraft.api.material.Material apiMaterial) {
		super(MapColor.mapColorArray[apiMaterial.getMapColorIndex().getIndex()]);
		this.apiMaterial = apiMaterial;
	}

	public org.spoutcraft.api.material.Material getApiMaterial() {
		return apiMaterial;
	}
}
