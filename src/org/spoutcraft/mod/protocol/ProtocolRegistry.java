package org.spoutcraft.mod.protocol;

import java.lang.reflect.Constructor;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.spoutcraft.mod.protocol.codec.Codec;
import org.spoutcraft.mod.protocol.message.Message;

public class ProtocolRegistry {
	//Message -> Codec
	private static final ConcurrentMap<Class<? extends Message>, Codec<?>> table;

	static {
		table = new ConcurrentHashMap<>(20, 1.0f);
	}

	public static <T extends Message, C extends Codec<T>> void register(Class<T> clazz, Class<C> clazzz) {
		Constructor<C> constructor;
		try {
			constructor = clazzz.getConstructor();
		} catch (NoSuchMethodException e) {
			throw new IllegalStateException("Attempt to register codec with non-empty constructor!");
		}
		final C c;
		try {
			c = constructor.newInstance();
		} catch (Exception e) {
			throw new IllegalStateException("Could not create instance of codec: " + clazzz);
		}
		table.put(clazz, c);
	}

	@SuppressWarnings ("unchecked")
	public static <T extends Message> Codec<T> find(Class<T> clazz) {
		return (Codec<T>) table.get(clazz);
	}

	@SuppressWarnings ("unchecked")
	public static <T extends Message> Codec<T> find(String channel) {
		for (Codec codec : table.values()) {
			if (codec.getChannel().equals(channel)) {
				return codec;
			}
		}
		return null;
	}
}
