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
package org.spoutcraft.mod.protocol;

import java.io.IOException;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.network.packet.Packet250CustomPayload;
import org.spoutcraft.api.protocol.Protocol;
import org.spoutcraft.api.protocol.codec.Codec;
import org.spoutcraft.api.protocol.message.Message;

public class SpoutcraftPacket extends Packet250CustomPayload {
    public SpoutcraftPacket() {

    }

    @SuppressWarnings ("unchecked")
    public SpoutcraftPacket(Message toSend) {
        final Codec codec = Protocol.find(toSend.getClass());
        try {
            super.channel = codec.getChannel();
            super.data = codec.encode(FMLCommonHandler.instance().getEffectiveSide(), toSend).array();
        } catch (IOException e) {
            throw new IllegalStateException("Failed to encode message: " + toSend);
        }
        super.length = super.data.length;
    }
}
