package org.spoutcraft.mod.protocol.message;

import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.network.INetworkManager;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public abstract class SpoutcraftMessage {
    private final String channel;

	public SpoutcraftMessage(String channel) {
		this.channel = channel;
	}

	public String getChannel() {
		return channel;
	}

    public abstract ByteArrayOutputStream encode() throws Exception;

	public abstract void handle(Side side, INetworkManager manager, ByteArrayInputStream data, Player player) throws Exception;
}
