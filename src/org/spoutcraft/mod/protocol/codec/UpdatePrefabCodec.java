package org.spoutcraft.mod.protocol.codec;

import java.io.IOException;
import java.nio.ByteBuffer;

import cpw.mods.fml.relauncher.Side;
import org.apache.commons.lang3.SerializationUtils;
import org.spoutcraft.api.Prefab;
import org.spoutcraft.mod.protocol.message.UpdatePrefabMessage;

public class UpdatePrefabCodec implements Codec<UpdatePrefabMessage> {
	@Override
	public String getChannel() {
		return "SPC-UpdatePrefab";
	}

	@Override
	public UpdatePrefabMessage decode(Side side, ByteBuffer buffer) throws IOException {
		final byte[] data = new byte[buffer.remaining()];
		buffer.get(data, 0, buffer.remaining());
		return new UpdatePrefabMessage((Prefab) SerializationUtils.deserialize(data));
	}

	@Override
	public ByteBuffer encode(Side side, UpdatePrefabMessage message) throws IOException {
		return ByteBuffer.wrap(SerializationUtils.serialize(message.getPrefab()));
	}
}
