package org.spoutcraft.mod.protocol;

import cpw.mods.fml.common.network.IConnectionHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.NetLoginHandler;
import net.minecraft.network.packet.NetHandler;
import net.minecraft.network.packet.Packet1Login;
import net.minecraft.server.MinecraftServer;
import org.spoutcraft.mod.protocol.message.SpoutcraftMessage;

public class SpoutcraftConnectionHandler implements IConnectionHandler {
    @Override
    public void playerLoggedIn(Player player, NetHandler netHandler, INetworkManager manager) {
        final SpoutcraftMessage message = ProtocolRegistry.get("SpoutcraftHello");
        if (message == null) {
            throw new IllegalStateException("SpoutcraftHelloWorldMessage is not found!");
        }
        PacketDispatcher.sendPacketToPlayer(new SpoutcraftPacket(message), player);
    }

    @Override
    public String connectionReceived(NetLoginHandler netHandler, INetworkManager manager) {
        return "";
    }

    @Override
    public void connectionOpened(NetHandler netClientHandler, String server, int port, INetworkManager manager) {

    }

    @Override
    public void connectionOpened(NetHandler netClientHandler, MinecraftServer server, INetworkManager manager) {

    }

    @Override
    public void connectionClosed(INetworkManager manager) {

    }

    @Override
    public void clientLoggedIn(NetHandler clientHandler, INetworkManager manager, Packet1Login login) {

    }
}
