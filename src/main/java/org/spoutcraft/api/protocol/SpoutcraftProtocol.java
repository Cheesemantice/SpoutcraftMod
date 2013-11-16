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
package org.spoutcraft.api.protocol;

import java.lang.reflect.Constructor;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import cpw.mods.fml.common.network.NetworkRegistry;
import org.spoutcraft.api.protocol.codec.Codec;
import org.spoutcraft.api.protocol.message.Message;
import org.spoutcraft.mod.protocol.SpoutcraftConnectionHandler;
import org.spoutcraft.mod.protocol.message.AddPrefabMessage;

public class SpoutcraftProtocol {
	private static final ConcurrentMap<Class<? extends Message>, Codec<?>> table;

	static {
		table = new ConcurrentHashMap<>(5, 1.0f);
	}

	public static void init() {
		NetworkRegistry.instance().registerConnectionHandler(new SpoutcraftConnectionHandler());
		register(AddPrefabMessage.class, org.spoutcraft.mod.protocol.codec.AddPrefabCodec.class);
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
