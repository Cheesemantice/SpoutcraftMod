package org.spoutcraft.mod.protocol.codec;

import java.io.IOException;
import java.nio.ByteBuffer;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;

import org.spoutcraft.mod.protocol.message.AddResourceMessage;

public class AddResourceCodec implements Codec<AddResourceMessage> {
	@Override
	public String getChannel() {
		return "SPC-UpdateBlock";
	}

	@Override
	public AddResourceMessage decode(Side side, ByteBuffer buffer) throws IOException {
		if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
			throw new IllegalStateException("Server is not allowed to receive resources!");
		}
		return null;
	}

	@Override
	public ByteBuffer encode(Side side, AddResourceMessage message) throws IOException {
		return null;
	}
}
