package org.spoutcraft.mod.protocol.codec;

import java.io.IOException;
import java.net.URL;
import java.nio.ByteBuffer;

import cpw.mods.fml.relauncher.Side;
import org.apache.commons.lang3.SerializationUtils;
import org.spoutcraft.api.protocol.codec.Codec;
import org.spoutcraft.mod.protocol.message.DownloadLinkMessage;
import org.spoutcraft.mod.protocol.util.ByteBufferUtils;

public class DownloadLinkCodec implements Codec<DownloadLinkMessage> {
    @Override
    public String getChannel() {
        return "SPC-DownloadLink";
    }

    @Override
    public DownloadLinkMessage decode(Side side, ByteBuffer buffer) throws IOException {
        if (side.isServer()) {
            throw new IllegalStateException("Server is not allowed to receive links!");
        }
        final int identSize = buffer.getInt();
        final String addonIdentifier = ByteBufferUtils.readString(buffer, identSize);
        byte[] urlData = new byte[buffer.remaining()];
        buffer.get(urlData);
        final URL url = (URL) SerializationUtils.deserialize(urlData);
        return new DownloadLinkMessage(addonIdentifier, url);
    }

    @Override
    public ByteBuffer encode(Side side, DownloadLinkMessage message) throws IOException {
        if (side.isClient()) {
            throw new IllegalStateException("Client is not allowed to send links!");
        }
        final String addonIdentifier = message.getAddonIdentifier();
        final URL url = message.getUrl();
        final byte[] urlBytes = SerializationUtils.serialize(url);
        final ByteBuffer buffer = ByteBuffer.allocate(4 + addonIdentifier.getBytes("UTF-8").length + urlBytes.length);
        buffer.putInt(addonIdentifier.length());
        ByteBufferUtils.writeString(buffer, addonIdentifier);
        buffer.put(urlBytes);
        return buffer;
    }
}
