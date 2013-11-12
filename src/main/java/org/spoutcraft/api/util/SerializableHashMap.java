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
