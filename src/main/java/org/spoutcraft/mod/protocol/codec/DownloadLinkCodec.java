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
import java.net.URL;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.apache.commons.lang3.SerializationUtils;
import org.spoutcraft.api.Spoutcraft;
import org.spoutcraft.api.protocol.codec.Codec;
import org.spoutcraft.api.util.BufferUtil;
import org.spoutcraft.mod.protocol.message.DownloadLinkMessage;

public class DownloadLinkCodec implements Codec<DownloadLinkMessage> {
    @Override
    public String getChannel() {
        return "SPC-DownloadLink";
    }

    @Override
    public DownloadLinkMessage decode(Spoutcraft game, ByteBuf buffer) throws IOException {
        if (game.getSide().isServer()) {
            throw new IOException("Server is not allowed to receive links!");
        }
        final String addonIdentifier = BufferUtil.readUTF8(buffer);
        byte[] data = new byte[buffer.capacity() - buffer.readerIndex()];
        buffer.readBytes(data);
        final URL url = (URL) SerializationUtils.deserialize(data);
        return new DownloadLinkMessage(addonIdentifier, url);
    }

    @Override
    public ByteBuf encode(Spoutcraft game, DownloadLinkMessage message) throws IOException {
        if (game.getSide().isClient()) {
            throw new IOException("Client is not allowed to send links!");
        }
        final String addonIdentifier = message.getAddonIdentifier();
        final URL url = message.getUrl();
        final byte[] data = SerializationUtils.serialize(url);
        final ByteBuf buffer = Unpooled.buffer();
        BufferUtil.writeUTF8(buffer, addonIdentifier);
        buffer.writeBytes(data);
        return buffer;
    }
}
