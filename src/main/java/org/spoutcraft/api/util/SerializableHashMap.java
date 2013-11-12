/**
 * This file is a part of Spoutcraft
 *
 * Copyright (c) 2013 SpoutcraftDev
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
package org.spoutcraft.api.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class SerializableHashMap<E extends Serializable, T extends Serializable> extends HashMap<E, T> implements Serializable {
	private static final long serialVersionUID = 1L;

	public SerializableHashMap() {
	}

	public SerializableHashMap(byte[] serialized) throws IOException, ClassNotFoundException {
		putAll(deserialize(serialized));
	}

	public byte[] serialize() throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutput out = new ObjectOutputStream(bos);
		out.writeObject(this);
		out.close();

		return bos.toByteArray();
	}

	@SuppressWarnings ("unchecked")
	private SerializableHashMap<E, T> deserialize(byte[] serialized) throws IOException, ClassNotFoundException {
		ObjectInput in = new ObjectInputStream(new ByteArrayInputStream(serialized));

		return (SerializableHashMap<E, T>) in.readObject();
	}

	private void writeObject(ObjectOutputStream out) throws IOException {
		out.writeInt(size());

		for (Map.Entry<E, T> entry : entrySet()) {
			out.writeObject(entry.getKey());
			out.writeObject(entry.getValue());
		}
	}

	@SuppressWarnings ("unchecked")
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		int num = in.readInt();

		for (int i = 0; i < num; i++) {
			E key = (E) in.readObject();
			T val = (T) in.readObject();

			put(key, val);
		}
	}
}
