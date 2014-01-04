/**
 * This file is part of SpoutcraftMod, licensed under the MIT License (MIT).
 *
 * Copyright (c) 2013 SpoutcraftDev <http://spoutcraft.org/>
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
package org.spoutcraft.api.protocol.codec;

import java.io.IOException;

import io.netty.buffer.ByteBuf;
import org.spoutcraft.api.Spoutcraft;
import org.spoutcraft.api.protocol.message.Message;

public interface Codec<T extends Message> {
    public String getChannel();

    /**
     * Decodes a {@link ByteBuf} into a message
     *
     * @param game See {@link org.spoutcraft.api.Spoutcraft}
     * @param buffer the buffer to read from
     * @return the message fully encoded.
     * @throws IOException If any decoding fails on the buffer
     */
    public T decode(Spoutcraft game, ByteBuf buffer) throws IOException;

    /**
     * Encodes a {@link Message} into a {@link ByteBuf}.
     *
     * @param game See {@link org.spoutcraft.api.Spoutcraft}
     * @param message The message to encode
     * @return A buffer ready to be sent
     * @throws IOException If any data on the message fails to encode
     */
    public ByteBuf encode(Spoutcraft game, T message) throws IOException;
}
