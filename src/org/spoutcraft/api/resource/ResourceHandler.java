package org.spoutcraft.api.resource;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public abstract class ResourceHandler<R> {
	private final String token;

	public ResourceHandler(String token) {
		this.token = token;
	}

	public abstract R load(InputStream in) throws IOException;

	public abstract OutputStream save(R resource);

	public String getToken() {
		return token;
	}
}
