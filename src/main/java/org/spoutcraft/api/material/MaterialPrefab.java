package org.spoutcraft.api.material;

import org.spoutcraft.api.Prefab;

public class MaterialPrefab extends Prefab {
	private final MapIndex index;

	public MaterialPrefab(String identifier, MapIndex index) {
		super(identifier);
		this.index = index;
	}

	public MapIndex getMapIndex() {
		return index;
	}

	@Override
	public String toString() {
		final String NEW_LINE = System.getProperty("line.separator");
		final String parent = super.toString();
		final StringBuilder builder;
		if (parent.endsWith("}")) {
			builder = new StringBuilder(parent.substring(0, parent.length() - 2) + NEW_LINE); //TODO CHECK THIS
			builder.insert(builder.indexOf("Identifier: " + super.getIdentifier()), " ");
			builder
					.append("  Map Index: " + index.name() + NEW_LINE)
					.append(" }");
		} else {
			builder = new StringBuilder(parent.substring(0, parent.length() - 1) + NEW_LINE);
			builder
					.append(" Map Index: " + index.name() + NEW_LINE)
					.append("}");
		}
		return builder.toString();
	}
}
