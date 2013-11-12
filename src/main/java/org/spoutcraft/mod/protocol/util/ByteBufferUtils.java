/**
 * This file is a part of Spoutcraft
 *
 * Copyright (c) 2013 SpoutcraftDev
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
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
