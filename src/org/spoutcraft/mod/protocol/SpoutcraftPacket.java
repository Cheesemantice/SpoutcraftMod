package org.spoutcraft.mod.protocol;

import java.io.IOException;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.network.packet.Packet250CustomPayload;
import org.spoutcraft.api.Spoutcraft;
import org.spoutcraft.mod.protocol.codec.Codec;
import org.spoutcraft.mod.protocol.message.Message;

public class SpoutcraftPacket extends Packet250CustomPayload {
	@SuppressWarnings ("unchecked")
	public SpoutcraftPacket(Message toSend) {
		final Codec codec = ProtocolRegistry.find(toSend.getClass());
		try {
			Spoutcraft.getLogger().info("Encoding codec: " + codec);
			super.channel = codec.getChannel();
			super.data = codec.encode(FMLCommonHandler.instance().getEffectiveSide(), toSend).array();
		} catch (IOException e) {
			throw new IllegalStateException("Failed to encode message: " + toSend);
		}
		super.length = super.data.length;
	}
}
