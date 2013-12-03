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
package org.spoutcraft.mod.protocol.codec;

import java.io.IOException;
import java.nio.ByteBuffer;

import cpw.mods.fml.relauncher.Side;
import org.apache.commons.lang3.SerializationUtils;
import org.spoutcraft.api.Prefab;
import org.spoutcraft.api.Spoutcraft;
import org.spoutcraft.api.addon.Addon;
import org.spoutcraft.api.protocol.codec.Codec;
import org.spoutcraft.api.util.ByteBufferUtil;
import org.spoutcraft.mod.protocol.message.AddPrefabMessage;

public class AddPrefabCodec implements Codec<AddPrefabMessage> {
    @Override
    public String getChannel() {
        return "SPC-AddPrefab";
    }

    @Override
    public AddPrefabMessage decode(Side side, ByteBuffer buffer) throws IOException {
        if (side.isServer()) {
            throw new IllegalStateException("The server is not allowed to receive prefabs");
        }
        final String addonIdentifier = ByteBufferUtil.readString(buffer, buffer.getInt());
        //TODO Sanity check needed and here?
        final Addon addon = Spoutcraft.getAddonManager().getAddon(addonIdentifier);
        if (addon == null) {
            return null;
        }
        final byte[] data = new byte[buffer.remaining()];
        buffer.get(data, 0, buffer.remaining());
        return new AddPrefabMessage(addon, (Prefab) SerializationUtils.deserialize(data));
    }

    @Override
    public ByteBuffer encode(Side side, AddPrefabMessage message) throws IOException {
        if (side.isClient()) {
            throw new IllegalStateException("The client is not allowed to send prefabs");
        }
        final String addonIdentifier = message.getAddon().getPrefab().getIdentifier();
        final byte[] data = SerializationUtils.serialize(message.getPrefab());
        final ByteBuffer buffer = ByteBuffer.allocate(4 + ByteBufferUtil.getSize(addonIdentifier) + data.length);
        buffer.putInt(addonIdentifier.length());
        ByteBufferUtil.writeString(buffer, addonIdentifier);
        buffer.put(data);
        return buffer;
    }
}
