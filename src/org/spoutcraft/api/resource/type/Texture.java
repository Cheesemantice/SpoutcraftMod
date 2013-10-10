package org.spoutcraft.api.resource.type;

import java.awt.image.BufferedImage;
import java.io.Serializable;

public class Texture implements Serializable {
	private final BufferedImage image;

	public Texture(BufferedImage image) {
		this.image = image;
	}

	public BufferedImage getData() {
		return image;
	}
}
