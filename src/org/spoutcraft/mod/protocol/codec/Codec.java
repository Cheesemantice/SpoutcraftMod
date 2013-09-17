package org.spoutcraft.mod.protocol.codec;

import java.io.IOException;
import java.nio.ByteBuffer;

import org.spoutcraft.mod.protocol.message.Message;

public interface Codec<T extends Message> {
	public String getChannel();

	public T decode(ByteBuffer buffer) throws IOException;

	public ByteBuffer encode(T message) throws IOException;
}
