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
