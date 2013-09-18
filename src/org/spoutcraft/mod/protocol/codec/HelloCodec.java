package org.spoutcraft.mod.protocol.codec;

import java.io.IOException;
import java.nio.ByteBuffer;

import cpw.mods.fml.common.FMLCommonHandler;

import org.spoutcraft.mod.protocol.message.HelloMessage;
import org.spoutcraft.mod.protocol.util.ByteBufferUtils;

public class HelloCodec implements Codec<HelloMessage> {
	@Override
	public String getChannel() {
		return "SpoutcraftHello";
	}

	@Override
	public HelloMessage decode(ByteBuffer buffer) throws IOException {
		final String greeting = ByteBufferUtils.readString(buffer, buffer.capacity());
		return new HelloMessage(greeting);
	}

	@Override
	public ByteBuffer encode(HelloMessage message) throws IOException {
		final ByteBuffer buffer = ByteBuffer.allocate(message.getGreeting().length() * 2);
		ByteBufferUtils.writeString(buffer, message.getGreeting());
		return buffer;
	}

	@Override
	public String toString() {
		return "HelloCodec {side= " + FMLCommonHandler.instance().getEffectiveSide().name() + ", channel= " + getChannel() + "}";
	}
}
