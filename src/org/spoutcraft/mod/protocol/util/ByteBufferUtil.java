package org.spoutcraft.mod.protocol.util;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

public class ByteBufferUtil {
	public static Charset charset = Charset.forName("UTF-8");
	public static CharsetEncoder encoder = charset.newEncoder();
	public static CharsetDecoder decoder = charset.newDecoder();

	public static ByteBuffer writeString(String toWrite) throws IOException {
		return encoder.encode(CharBuffer.wrap(toWrite));
	}

	public static String readString(ByteBuffer buffer) throws IOException {
		return decoder.decode(buffer).toString();
	}
}
