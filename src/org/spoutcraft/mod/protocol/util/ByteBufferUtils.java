package org.spoutcraft.mod.protocol.util;

import java.io.IOException;
import java.nio.ByteBuffer;

public class ByteBufferUtils {
	/**
	 * Writes a string to the buffer provided. <p/> This method will grow the buffer if capacity is exceeded
	 *
	 * @param buffer To write to
	 * @param toWrite String to write
	 * @throws IOException If writing fails to occur
	 */
	public static void writeString(ByteBuffer buffer, String toWrite) throws IOException {
		//TODO Encoding?
		buffer.asCharBuffer().put(toWrite);
	}

	/**
	 * Reads a string from the buffer provided with a specified length. <p/> This method assumes the buffer's position is set to the start position to read the string.
	 *
	 * @param buffer Buffer to read from
	 * @param length Length to read
	 * @return The read string
	 * @throws IOException If reading from the buffer fails
	 */
	public static String readString(ByteBuffer buffer, int length) throws IOException {
		buffer.limit(buffer.position() + length);
		final String decoded = buffer.asCharBuffer().toString();
		buffer.clear();
		buffer.position(buffer.position() + length);
		return decoded;
	}
}
