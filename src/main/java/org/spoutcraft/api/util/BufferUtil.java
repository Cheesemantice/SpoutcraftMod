/**
 * This file is part of SpoutcraftMod, licensed under the MIT License (MIT).
 *
 * Copyright (c) 2013-2014 SpoutcraftDev <http://spoutcraft.org/>
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
package org.spoutcraft.api.util;

import java.io.IOException;
import java.nio.ByteBuffer;

import com.google.common.base.Charsets;
import io.netty.buffer.ByteBuf;

public class BufferUtil {
    /**
     * Reads an UTF8 string from a {@link io.netty.buffer.ByteBuf}.
     *
     * @param buf The byte buffer to read from
     * @return The read string
     * @throws IOException If the reading fails
     */
    public static String readUTF8(ByteBuf buf) throws IOException {
        // Read the string's length
        final int len = buf.readInt();
        final byte[] bytes = new byte[len];
        buf.readBytes(bytes);
        return new String(bytes, Charsets.UTF_8);
    }

    /**
     * Writes an UTF8 string to a {@link io.netty.buffer.ByteBuf}.
     *
     * @param buf The byte buffer to write too
     * @param value The string to write
     * @throws IOException If the writing fails
     */
    public static void writeUTF8(ByteBuf buf, String value) throws IOException {
        final byte[] bytes = value.getBytes(Charsets.UTF_8);
        if (bytes.length >= Short.MAX_VALUE) {
            throw new IOException("Attempt to write a string with a length greater than Short.MAX_VALUE to ByteBuf!");
        }
        // Write the string's length
        buf.writeInt(bytes.length);
        buf.writeBytes(bytes);
    }

    /**
     * Writes a UTF8 string to a {@link java.nio.ByteBuffer}
     *
     * @param buffer To write to
     * @param toWrite String to write
     * @throws IOException If writing fails to occur
     */
    public static void writeUTF8(ByteBuffer buffer, String toWrite) throws IOException {
        buffer.put(toWrite.getBytes(Charsets.UTF_8));
    }

    /**
     * Reads a UTF8 string from a {@link java.nio.ByteBuffer} with a specified length.
     *
     * @param buffer Buffer to read from
     * @param length Length to read
     * @return The read string
     * @throws IOException If reading from the buffer fails
     */
    public static String readUTF8(ByteBuffer buffer, int length) throws IOException {
        int oldPosition = buffer.position();
        buffer.limit(oldPosition + length);
        byte[] data = new byte[buffer.remaining()];
        buffer.get(data);
        buffer.clear();
        buffer.position(oldPosition + length);
        return new String(data, Charsets.UTF_8);
    }
}
