package org.spoutcraft.api.resource;

import java.nio.file.Path;

public class Texture implements Resource {
	private final String name;
	private final Path source;

	public Texture(String name, Path source) {
		this.name = name;
		this.source = source;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Path getSource() {
		return source;
	}

	@Override
	public ResourceType getType() {
		return ResourceType.TEXTURE;
	}
}
