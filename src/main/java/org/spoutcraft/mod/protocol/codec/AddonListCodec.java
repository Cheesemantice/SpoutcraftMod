/**
 * This file is part of SpoutcraftMod, licensed under the MIT License (MIT).
 *
 * Copyright (c) 2013 SpoutcraftDev <http://spoutcraft.org/>
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
package org.spoutcraft.mod.protocol.codec;

import java.io.IOException;

import cpw.mods.fml.relauncher.Side;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.spoutcraft.api.protocol.codec.Codec;
import org.spoutcraft.api.util.map.SerializableHashMap;
import org.spoutcraft.mod.protocol.message.AddonListMessage;

public class AddonListCodec implements Codec<AddonListMessage> {
    @Override
    public String getChannel() {
        return "SPC-AddonList";
    }

    @Override
    public AddonListMessage decode(Side side, ByteBuf buffer) throws IOException {
        final AddonListMessage message;
        try {
            message = new AddonListMessage(new SerializableHashMap<String, String>(buffer.array()));
        } catch (ClassNotFoundException e) {
            throw new IOException(e);
        }
        return message;
    }

    @Override
    public ByteBuf encode(Side side, AddonListMessage message) throws IOException {
        if (side.isServer()) {
            throw new IllegalStateException("Server does not send the addon list, it checks it");
        }
        return Unpooled.buffer().writeBytes(message.getMap().serialize());
    }
}
