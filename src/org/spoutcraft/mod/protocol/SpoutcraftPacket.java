package org.spoutcraft.mod.protocol;

import java.io.IOException;

import net.minecraft.network.packet.Packet250CustomPayload;
import org.spoutcraft.mod.protocol.codec.Codec;
import org.spoutcraft.mod.protocol.message.Message;

public class SpoutcraftPacket extends Packet250CustomPayload {
	@SuppressWarnings ("unchecked")
	public SpoutcraftPacket(Message toSend) {
		final Codec codec = ProtocolRegistry.find(toSend.getClass());
		try {
			System.out.println("[Spoutcraft] Encoding codec: " + codec);
			super.channel = codec.getChannel();
			super.data = codec.encode(toSend).array();
		} catch (IOException e) {
			throw new IllegalStateException("Failed to encode message: " + toSend);
		}
		super.length = super.data.length;
	}
}
