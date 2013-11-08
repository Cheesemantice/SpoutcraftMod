package org.spoutcraft.mod.protocol.codec;

import java.io.IOException;
import java.nio.ByteBuffer;

import cpw.mods.fml.relauncher.Side;
import org.apache.commons.lang3.SerializationUtils;
import org.spoutcraft.api.block.BlockPrefab;
import org.spoutcraft.mod.protocol.message.UpdateBlockMessage;

public class UpdateBlockCodec implements Codec<UpdateBlockMessage> {
	@Override
	public String getChannel() {
		return "SPC-UpdateBlock";
	}

	@Override
	public UpdateBlockMessage decode(Side side, ByteBuffer buffer) throws IOException {
		final int id = buffer.getInt();
		final byte[] data = new byte[buffer.remaining()];
		buffer.get(data, 0, buffer.remaining());
		final BlockPrefab blockPrefab = (BlockPrefab) SerializationUtils.deserialize(data);
		return new UpdateBlockMessage(id, blockPrefab);
	}

	@Override
	public ByteBuffer encode(Side side, UpdateBlockMessage message) throws IOException {
		final int id = message.getId();
		final BlockPrefab blockPrefab = message.getBlock();
		final byte[] serialized = SerializationUtils.serialize(blockPrefab);
		final ByteBuffer buffer = ByteBuffer.allocate(4 + serialized.length);
		buffer.putInt(id);
		buffer.put(serialized);
		return buffer;
	}
}
