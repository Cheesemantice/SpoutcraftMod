package org.spoutcraft.mod.protocol.codec;

import java.io.IOException;
import java.nio.ByteBuffer;

import cpw.mods.fml.relauncher.Side;
import org.spoutcraft.api.util.SerializableHashMap;
import org.spoutcraft.mod.protocol.message.AddonListMessage;

public class AddonListCodec implements Codec<AddonListMessage> {
	@Override
	public String getChannel() {
		return "SPC-ListAddon";
	}

	@Override
	public AddonListMessage decode(Side side, ByteBuffer buffer) throws IOException {
		final AddonListMessage message;
		try {
			message = new AddonListMessage(new SerializableHashMap<String, String>(buffer.array()));
		} catch (Exception e) {
			throw new IllegalStateException("Deserialization failed while decoding " + this);
		}
		return message;
	}

	@Override
	public ByteBuffer encode(Side side, AddonListMessage message) throws IOException {
		if (side.isServer()) {
			throw new IllegalStateException("Server does not send the addon list, it checks it");
		}
		return ByteBuffer.wrap(message.getMap().serialize());
	}
}
