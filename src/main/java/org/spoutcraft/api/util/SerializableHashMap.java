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

public class SerializableHashMap<E extends Serializable, T extends Serializable> extends HashMap<E, T> implements Serializable {
	private static final long serialVersionUID = 1L;

	public SerializableHashMap() {
	}

	public SerializableHashMap(byte[] serialized) throws IOException, ClassNotFoundException {
		putAll(deserialize(serialized));
	}

	private void writeObject(ObjectOutputStream out) throws IOException {
		out.writeInt(size());

		for (E key : keySet()) {
			out.writeObject(key);
			out.writeObject(get(key));
		}
	}

	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		int num = in.readInt();

		for (int i = 0; i < num; i++) {
			@SuppressWarnings ("unchecked")
			E key = (E) in.readObject();
			@SuppressWarnings ("unchecked")
			T val = (T) in.readObject();

			put(key, val);
		}
	}

	public byte[] serialize() throws IOException {
		ObjectOutput out;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		out = new ObjectOutputStream(bos);
		out.writeObject(this);
		out.close();

		return bos.toByteArray();
	}

	@SuppressWarnings ("unchecked")
	private SerializableHashMap<E, T> deserialize(byte[] serialized) throws IOException, ClassNotFoundException {
		ByteArrayInputStream bosin = new ByteArrayInputStream(serialized);
		ObjectInput in = new ObjectInputStream(bosin);

		return (SerializableHashMap<E, T>) in.readObject();
	}
}
