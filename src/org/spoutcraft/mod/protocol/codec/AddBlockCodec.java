package org.spoutcraft.mod.protocol.codec;

import java.io.IOException;
import java.nio.ByteBuffer;

import cpw.mods.fml.relauncher.Side;
import org.apache.commons.lang3.SerializationUtils;
import org.spoutcraft.api.block.Block;
import org.spoutcraft.mod.protocol.message.AddBlockMessage;

public class AddBlockCodec implements Codec<AddBlockMessage> {
	@Override
	public String getChannel() {
		return "SpoutcraftAddBlk";
	}

	@Override
	public AddBlockMessage decode(Side side, ByteBuffer buffer) throws IOException {
		final int id = buffer.getInt();
		final byte[] data = new byte[buffer.remaining()];
		buffer.get(data, 0, buffer.remaining());
		final Block block = (Block) SerializationUtils.deserialize(data);
		return new AddBlockMessage(id, block);
	}

	@Override
	public ByteBuffer encode(Side side, AddBlockMessage message) throws IOException {
		final int id = message.getId();
		final Block block = message.getBlock();
		final byte[] serialized = SerializationUtils.serialize(block);
		final ByteBuffer buffer = ByteBuffer.allocate(4 + serialized.length);
		buffer.putInt(id);
		buffer.put(serialized);
		return buffer;
	}
}
