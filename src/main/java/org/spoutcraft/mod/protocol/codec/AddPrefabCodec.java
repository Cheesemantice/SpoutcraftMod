package org.spoutcraft.mod.protocol.codec;

import java.io.IOException;
import java.nio.ByteBuffer;

import cpw.mods.fml.relauncher.Side;
import org.apache.commons.lang3.SerializationUtils;
import org.spoutcraft.api.Prefab;
import org.spoutcraft.mod.protocol.message.AddPrefabMessage;

public class AddPrefabCodec implements Codec<AddPrefabMessage> {
	@Override
	public String getChannel() {
		return "SPC-AddPrefab";
	}

	@Override
	public AddPrefabMessage decode(Side side, ByteBuffer buffer) throws IOException {
		if (side.isServer()) {
			throw new IllegalStateException("The server is not allowed to receive prefabs");
		}
		final byte[] data = new byte[buffer.remaining()];
		buffer.get(data, 0, buffer.remaining());
		return new AddPrefabMessage((Prefab) SerializationUtils.deserialize(data));
	}

	@Override
	public ByteBuffer encode(Side side, AddPrefabMessage message) throws IOException {
		if (side.isClient()) {
			throw new IllegalStateException("The client is not allowed to send prefabs");
		}
		return ByteBuffer.wrap(SerializationUtils.serialize(message.getPrefab()));
	}
}
