package org.spoutcraft.mod.protocol.codec;

import java.io.IOException;
import java.nio.ByteBuffer;

import cpw.mods.fml.relauncher.Side;
import org.spoutcraft.mod.protocol.message.Message;

public interface Codec<T extends Message> {
	public String getChannel();

	/**
	 * Decodes a buffer into a message
	 *
	 * @param side The current side Forge is on (Client/Server)
	 * @param buffer the buffer to read from
	 * @return the message fully encoded.
	 * @throws IOException If any decoding fails on the buffer
	 */
	public T decode(Side side, ByteBuffer buffer) throws IOException;

	/**
	 * Encodes a {@link Message} into a {@link ByteBuffer}.
	 *
	 * @param side The current side Forge is on (Client/Server)
	 * @param message The message to encode
	 * @return A buffer ready to be sent
	 * @throws IOException If any data on the message fails to encode
	 */
	public ByteBuffer encode(Side side, T message) throws IOException;
}
