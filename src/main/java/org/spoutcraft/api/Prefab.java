package org.spoutcraft.api;

import java.io.Serializable;

/**
 * A Prefab is a descriptor, by extending this class and providing characteristics you can use it to build:
 *
 * - {@link net.minecraft.block.Block} - {@link net.minecraft.item.Item} - {@link net.minecraft.block.material.Material}
 *
 * Prefabs are sent over the wire and constructed into real Minecraft classes.
 */
public abstract class Prefab implements Serializable {
	private static final long serialVersionUID = 1L;
	private final String identifier;

	public Prefab(String identifier) {
		this.identifier = identifier;
	}

	public String getIdentifier() {
		return identifier;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Prefab)) {
			return false;
		}

		final Prefab prefab = (Prefab) o;

		if (!identifier.equals(prefab.identifier)) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		return identifier.hashCode();
	}

	@Override
	public String toString() {
		final String NEW_LINE = System.getProperty("line.separator");
		final StringBuilder builder = new StringBuilder();
		builder
				.append(getClass().getName() + " {" + NEW_LINE)
				.append(" Identifier: " + getIdentifier() + NEW_LINE)
				.append("}");
		return builder.toString();
	}
}
