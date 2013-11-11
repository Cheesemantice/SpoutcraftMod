package org.spoutcraft.api.block;

import org.spoutcraft.api.material.MaterialPrefab;

public class MovingPrefab extends BlockPrefab {
	public MovingPrefab(String identifier, String displayName, MaterialPrefab prefab, float hardness, boolean showInCreativeTab) {
		super(identifier, displayName, prefab, hardness, showInCreativeTab);
	}
}
