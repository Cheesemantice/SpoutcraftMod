/**
 * This file is a part of Spoutcraft.
 *
 * Copyright (c) 2013 SpoutcraftDev <http://spoutcraft.org>
 * Spoutcraft is licensed under the MIT License.
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

public class ByteBufferUtil {
    /**
     * Writes a string to the buffer provided.
     *
     * @param buffer To write to
     * @param toWrite String to write
     * @throws IOException If writing fails to occur
     */
    public static void writeString(ByteBuffer buffer, String toWrite) throws IOException {
        buffer.put(toWrite.getBytes(Charsets.UTF_8));
    }

    /**
     * Reads a string from the buffer provided with a specified length.
     *
     * @param buffer Buffer to read from
     * @param length Length to read
     * @return The read string
     * @throws IOException If reading from the buffer fails
     */
    public static String readString(ByteBuffer buffer, int length) throws IOException {
        int oldPosition = buffer.position();
        buffer.limit(oldPosition + length);
        byte[] data = new byte[buffer.remaining()];
        buffer.get(data);
        buffer.clear();
        buffer.position(oldPosition + length);
        return new String(data, Charsets.UTF_8);
    }
}
