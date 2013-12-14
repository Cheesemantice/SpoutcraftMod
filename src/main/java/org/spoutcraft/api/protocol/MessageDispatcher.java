package org.spoutcraft.api.protocol;

import java.io.IOException;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.packet.Packet250CustomPayload;
import org.spoutcraft.api.protocol.codec.Codec;
import org.spoutcraft.api.protocol.message.Message;

public class MessageDispatcher {
    public static void send(Message message) {
        PacketDispatcher.sendPacketToAllPlayers(create(message));
    }

    public static void send(Player player, Message message) {
        PacketDispatcher.sendPacketToPlayer(create(message), player);
    }

    public static void send(Message message, int dimension) {
        PacketDispatcher.sendPacketToAllInDimension(create(message), dimension);
    }

    @SuppressWarnings ("unchecked")
    public static Packet250CustomPayload create(Message message) {
        Codec codec = Protocol.find(message.getClass());
        if (codec == null) {
            throw new IllegalStateException("Attempt to send message with no bound codec!");
        }
        final ByteBuf encoded;
        try {
            encoded = codec.encode(FMLCommonHandler.instance().getEffectiveSide(), message);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to encode message: " + message, e);
        }
        final byte[] data = new byte[encoded.readableBytes()];
        encoded.readBytes(data);
        return new Packet250CustomPayload(codec.getChannel(), data);
    }
}
