package org.spoutcraft.api.block;

import org.spoutcraft.api.Prefab;
import org.spoutcraft.api.material.MaterialPrefab;

public class BlockPrefab extends Prefab {
	private final BlockType type;
	private final String displayName;
	private final MaterialPrefab prefab;
	private final float hardness;

	public BlockPrefab(String identifier, String displayName, MaterialPrefab prefab, float hardness) {
		this(identifier, BlockType.GENERIC, displayName, prefab, hardness);
	}

	public BlockPrefab(String identifier, BlockType type, String displayName, MaterialPrefab prefab, float hardness) {
		super(identifier);
		this.type = type;
		this.displayName = displayName;
		this.prefab = prefab;
		this.hardness = hardness;
	}

	public BlockType getType() {
		return type;
	}

	public String getDisplayName() {
		return displayName;
	}

	public MaterialPrefab getMaterialPrefab() {
		return prefab;
	}

	public float getHardness() {
		return hardness;
	}

	@Override
	public String toString() {
		final String NEW_LINE = System.getProperty("line.separator");
		final String parent = super.toString();
		final StringBuilder builder = new StringBuilder(parent.substring(0, parent.length() - 1));
		builder
				.append(" Type: " + type.name() + NEW_LINE)
				.append(" Display Name: " + displayName + NEW_LINE)
				.append(" " + prefab.toString() + NEW_LINE)
				.append(" Hardness: " + hardness + NEW_LINE)
				.append("}");
		return builder.toString();
	}
}
