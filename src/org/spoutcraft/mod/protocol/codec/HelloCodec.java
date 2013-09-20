package org.spoutcraft.mod.protocol.codec;

import java.io.IOException;
import java.nio.ByteBuffer;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import org.spoutcraft.mod.protocol.message.HelloMessage;
import org.spoutcraft.mod.protocol.util.ByteBufferUtils;

public class HelloCodec implements Codec<HelloMessage> {
	@Override
	public String getChannel() {
		return "SpoutcraftHello";
	}

	@Override
	public HelloMessage decode(Side side, ByteBuffer buffer) throws IOException {
		return new HelloMessage(ByteBufferUtils.readString(buffer, buffer.remaining()));
	}

	@Override
	public ByteBuffer encode(Side side, HelloMessage message) throws IOException {
		final ByteBuffer buffer = ByteBuffer.allocate(message.getGreeting().length() * 2);
		ByteBufferUtils.writeString(buffer, message.getGreeting());
		return buffer;
	}

	@Override
	public String toString() {
		return "HelloCodec {side= " + FMLCommonHandler.instance().getEffectiveSide().name() + ", channel= " + getChannel() + "}";
	}
}
