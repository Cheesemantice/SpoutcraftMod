package org.spoutcraft.mod.protocol;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import org.spoutcraft.mod.protocol.message.SpoutcraftMessage;

import java.io.ByteArrayInputStream;

public class SpoutcraftPacketHandler implements IPacketHandler {
	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
        final SpoutcraftMessage message = ProtocolRegistry.get(packet.channel);
        if (message != null) {
            try {
                message.handle(FMLCommonHandler.instance().getEffectiveSide(), manager, new ByteArrayInputStream(packet.data), player);
            } catch (Exception ex) {
                throw new IllegalStateException("Failed to handle message: " + message);
            }
        }
	}
}
