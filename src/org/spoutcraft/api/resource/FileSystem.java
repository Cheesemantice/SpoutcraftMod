package org.spoutcraft.api.resource;

import java.net.URI;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;

public interface FileSystem {
	public <R> R get(Path path);

	public <R> R get(String uri);

	public <R> R get(URI uri);

	public <R> List<R> getResources(Path path);

	public <R> List<R> getResources(String uri);

	public <R> List<R> getResources(URI uri);

	public ResourceHandler getHandler(String token);

	public Set<ResourceHandler> getHandlers();

	public void register(ResourceHandler handler);

	public <R> void send(R resource);
}
