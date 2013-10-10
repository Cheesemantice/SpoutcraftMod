package org.spoutcraft.api.resource.handler;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.spoutcraft.api.resource.ResourceHandler;
import org.spoutcraft.api.resource.type.Texture;

public class TextureHandler extends ResourceHandler<Texture> {
	public TextureHandler() {
		super("texture");
	}

	@Override
	public Texture load(InputStream in) throws IOException {
		return new Texture(ImageIO.read(in));
	}

	@Override
	public OutputStream save(Texture resource) {
		return null;
	}
}
