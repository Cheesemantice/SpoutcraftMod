package org.spoutcraft.mod.protocol.packet;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import cpw.mods.fml.relauncher.Side;

public abstract class SpoutcraftPacket {
	private final String channel;

	public SpoutcraftPacket (String channel) {
		this.channel = channel;
	}

	public String getChannel() {
		return channel;
	}

	public abstract ByteArrayInputStream decode(Side side);

	public abstract ByteArrayOutputStream encode(Side side);

	public abstract void handle(Side side);
}
