package org.spoutcraft.mod.protocol;

import net.minecraft.network.packet.Packet250CustomPayload;
import org.spoutcraft.mod.protocol.message.SpoutcraftMessage;

import java.io.ByteArrayOutputStream;

public class SpoutcraftPacket extends Packet250CustomPayload {
    private final SpoutcraftMessage toSend;

    public SpoutcraftPacket(SpoutcraftMessage toSend) {
        this.toSend = toSend;
        super.channel = toSend.getChannel();
        final ByteArrayOutputStream data;
        try {
            data = toSend.encode();
        } catch (Exception e) {
            throw new IllegalStateException("Failed to encode message: " + toSend);
        }
        super.data = data.toByteArray();
        super.length = data.size();
    }

    public SpoutcraftMessage getMessage() {
        return toSend;
    }
}
