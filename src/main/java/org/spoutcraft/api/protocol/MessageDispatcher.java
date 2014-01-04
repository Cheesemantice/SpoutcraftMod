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
package org.spoutcraft.api.protocol;

import java.io.IOException;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.packet.Packet250CustomPayload;
import org.spoutcraft.api.Spoutcraft;
import org.spoutcraft.api.protocol.codec.Codec;
import org.spoutcraft.api.protocol.message.Message;

public class MessageDispatcher {
    private final MessageCodecLookupService messageCodecLookupService;
    private final Spoutcraft game;

    public MessageDispatcher(Spoutcraft game) {
        this.game = game;
        messageCodecLookupService = new MessageCodecLookupService(game);
    }

    public MessageCodecLookupService getProtocol() {
        return messageCodecLookupService;
    }

    public void send(Message message) {
        PacketDispatcher.sendPacketToAllPlayers(create(message));
    }

    public void send(Player player, Message message) {
        PacketDispatcher.sendPacketToPlayer(create(message), player);
    }

    public void send(Message message, int dimension) {
        PacketDispatcher.sendPacketToAllInDimension(create(message), dimension);
    }

    @SuppressWarnings ("unchecked")
    public Packet250CustomPayload create(Message message) {
        Codec codec = messageCodecLookupService.find(message.getClass());
        if (codec == null) {
            throw new IllegalStateException("Attempt to send message with no bound codec!");
        }
        final ByteBuf encoded;
        try {
            encoded = codec.encode(game, message);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to encode message: " + message, e);
        }
        final byte[] data = new byte[encoded.readableBytes()];
        encoded.readBytes(data);
        return new Packet250CustomPayload(codec.getChannel(), data);
    }
}
