package org.spoutcraft.api.resource;

import java.io.Serializable;
import java.nio.file.Path;

public interface Resource extends Serializable {
	public String getName();

	public Path getSource();

	public ResourceType getType();
}
