package org.spoutcraft.mod.protocol;

import java.nio.ByteBuffer;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import org.spoutcraft.mod.protocol.codec.Codec;
import org.spoutcraft.mod.protocol.message.Message;

public class SpoutcraftPacketHandler implements IPacketHandler {
	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
		final Codec codec = ProtocolRegistry.find(packet.channel);
		final Message message;
		try {
			System.out.println("[Spoutcraft] Decoding codec: " + codec);
			final ByteBuffer buffer = ByteBuffer.allocate(packet.data.length);
			buffer.put(packet.data);
			message = codec.decode(buffer);
		} catch (Exception e) {
			throw new IllegalStateException("Error decoding codec: " + codec);
		}
		if (message != null) {
			System.out.println("[Spoutcraft] Handling message: " + message);
			message.handle(FMLCommonHandler.instance().getEffectiveSide(), manager, player);
		}
	}
}
