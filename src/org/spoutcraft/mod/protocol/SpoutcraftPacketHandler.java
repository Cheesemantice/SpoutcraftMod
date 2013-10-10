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
