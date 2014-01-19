/**
 * This file is part of SpoutcraftMod, licensed under the MIT License (MIT).
 *
 * Copyright (c) 2013-2014 SpoutcraftDev <http://spoutcraft.org/>
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
import java.util.HashMap;
import java.util.Map;

import cpw.mods.fml.common.network.NetworkRegistry;
import org.spoutcraft.api.Spoutcraft;
import org.spoutcraft.api.protocol.codec.Codec;
import org.spoutcraft.api.protocol.message.Message;

public class MessageCodecLookupService {
    private final Spoutcraft game;
    private final MessagePacketHandler handler;
    private final Map<Class<? extends Message>, Codec<?>> table;

    public MessageCodecLookupService(Spoutcraft game) {
        this.game = game;
        handler = new MessagePacketHandler(game);
        table = new HashMap<>(5, 1.0f);
    }

    public <T extends Message, C extends Codec<T>> void register(Class<T> clazz, Class<C> clazzz) {
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
        NetworkRegistry.instance().registerChannel(handler, c.getChannel());
    }

    @SuppressWarnings ("unchecked")
    public <T extends Message> Codec<T> find(Class<T> clazz) {
        return (Codec<T>) table.get(clazz);
    }

    @SuppressWarnings ({"unchecked", "rawtypes"})
    public <T extends Message> Codec<T> find(String channel) {
        for (Codec codec : table.values()) {
            if (codec.getChannel().equals(channel)) {
                return codec;
            }
        }
        return null;
    }
}
