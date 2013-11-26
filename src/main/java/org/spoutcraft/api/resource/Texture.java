/**
 * This file is a part of Spoutcraft.
 *
 * Copyright (c) 2013 SpoutcraftDev <http://spoutcraft.org>
 * Spoutcraft is licensed under the MIT License.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.spoutcraft.api.resource;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javax.imageio.ImageIO;

public class Texture implements Serializable {
	private static final long serialVersionUID = 1L;
	private BufferedImage image = null;

	public Texture() {
	}

	public Texture(BufferedImage image) {
		this.image = image;
	}

	public BufferedImage getData() {
		return image;
	}

	private void writeObject(ObjectOutputStream out) throws IOException {
		if (image == null) {
			throw new IOException("Attempt to serialize Texture with null image data");
		}
		out.defaultWriteObject();
		ImageIO.write(image, "png", out); //PNG is lossless so its safe to use even if it isn't PNG in reality
	}

	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		in.defaultReadObject();
		image = ImageIO.read(in);
	}
}
