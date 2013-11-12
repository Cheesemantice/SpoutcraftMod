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

import java.nio.ByteBuffer;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import org.spoutcraft.api.Spoutcraft;
import org.spoutcraft.mod.protocol.codec.Codec;
import org.spoutcraft.mod.protocol.message.Message;

public class SpoutcraftPacketHandler implements IPacketHandler {
	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
		final Codec codec = SpoutcraftProtocol.find(packet.channel);
		final Message message;
		try {
			Spoutcraft.getLogger().info("Decoding codec: " + codec);
			final ByteBuffer buffer = ByteBuffer.allocate(packet.data.length);
			buffer.put(packet.data);
			buffer.flip();
			message = codec.decode(FMLCommonHandler.instance().getEffectiveSide(), buffer);
		} catch (Exception e) {
			e.printStackTrace();
			throw new IllegalStateException("Error decoding codec: " + codec);
		}
		if (message != null) {
			Spoutcraft.getLogger().info("Handling message: " + message);
			message.handle(FMLCommonHandler.instance().getEffectiveSide(), manager, player);
		}
	}
}
