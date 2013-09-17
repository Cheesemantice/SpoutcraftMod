package org.spoutcraft.mod.protocol.codec;

import java.io.IOException;
import java.nio.ByteBuffer;

import org.spoutcraft.mod.protocol.message.HelloMessage;
import org.spoutcraft.mod.protocol.util.ByteBufferUtil;

public class HelloCodec implements Codec<HelloMessage> {
	@Override
	public String getChannel() {
		return "SpoutcraftHello";
	}

	@Override
	public HelloMessage decode(ByteBuffer buffer) throws IOException {
		return new HelloMessage(ByteBufferUtil.readString(buffer));
	}

	@Override
	public ByteBuffer encode(HelloMessage message) throws IOException {
		return ByteBufferUtil.writeString(message.getGreeting());
	}

	@Override
	public String toString() {
		return "HelloCodec {channel= " + getChannel() + "}";
	}
}
