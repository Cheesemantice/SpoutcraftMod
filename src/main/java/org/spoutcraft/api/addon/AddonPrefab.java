package org.spoutcraft.api.addon;

import org.spoutcraft.api.Prefab;

public class AddonPrefab extends Prefab {
	private final String displayName;

	public AddonPrefab(String identifier, String displayName) {
		super(identifier);
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}

	@Override
	public String toString() {
		final String NEW_LINE = System.getProperty("line.separator");
		final String parent = super.toString();
		final StringBuilder builder = new StringBuilder(parent.substring(0, parent.length() - 1));
		builder
				.append(" Display Name: " + displayName + NEW_LINE)
				.append("}");
		return builder.toString();
	}
}
