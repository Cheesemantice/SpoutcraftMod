package org.spoutcraft.mod.protocol.message;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.network.INetworkManager;

import org.spoutcraft.api.Spoutcraft;
import org.spoutcraft.mod.protocol.SpoutcraftPacket;

public class HelloMessage implements Message {
	private final String greeting;

	public HelloMessage(String greeting) {
		this.greeting = greeting;
	}

	public String getGreeting() {
		return greeting;
	}

	@Override
	public void handle(Side side, INetworkManager manager, Player player) {
		Spoutcraft.getLogger().info(getGreeting());

		if (side == Side.CLIENT) {
			PacketDispatcher.sendPacketToServer(new SpoutcraftPacket(new HelloMessage("Hello server, this is the client")));
		}
	}
}
