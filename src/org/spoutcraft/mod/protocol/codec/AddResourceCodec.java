package org.spoutcraft.mod.protocol.codec;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

import cpw.mods.fml.common.FMLCommonHandler;
import org.spoutcraft.api.resource.Resource;
import org.spoutcraft.api.util.PNGDecoder;
import org.spoutcraft.mod.protocol.message.AddResourceMessage;

public class AddResourceCodec implements Codec<AddResourceMessage> {
	@Override
	public String getChannel() {
		return "SpoutcraftAddRes";
	}

	@Override
	public AddResourceMessage decode(ByteBuffer buffer) throws IOException {
		if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
			throw new IllegalStateException("Server is not allowed to recieve resources!");
		}
		return null;  //To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public ByteBuffer encode(AddResourceMessage message) throws IOException {
		if (FMLCommonHandler.instance().getEffectiveSide().isClient()) {
			throw new IllegalStateException("Client is not allowed to send the server resources!");
		}
		final Resource resource = message.getResource();
		final File textureFile = resource.getSource().toFile();
		if (!textureFile.exists()) {
			throw new IllegalStateException("Attempt to send a texture that does not exist!");
		}
		PNGDecoder decoder = new PNGDecoder(new FileInputStream(textureFile));
		final ByteBuffer buffer = ByteBuffer.allocateDirect(4 * decoder.getWidth() * decoder.getHeight());
		decoder.decode(buffer, decoder.getWidth() * 4, PNGDecoder.TextureFormat.RGBA);
		return buffer;
	}
}
