package org.spoutcraft.mod.protocol;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.IConnectionHandler;
import cpw.mods.fml.common.network.Player;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.NetLoginHandler;
import net.minecraft.network.packet.NetHandler;
import net.minecraft.network.packet.Packet1Login;
import net.minecraft.server.MinecraftServer;

import org.spoutcraft.api.Spoutcraft;
import org.spoutcraft.api.block.Block;
import org.spoutcraft.api.material.MapIndex;
import org.spoutcraft.api.material.Material;

public class SpoutcraftConnectionHandler implements IConnectionHandler {
	@Override
	public void playerLoggedIn(Player player, NetHandler netHandler, INetworkManager manager) {
		Spoutcraft.getLogger().info("Player has logged in");
		//Test code
		if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
			Spoutcraft.getBlockRegistry().put(new Block("testblock", "TestBlock", new Material("TestMaterial", MapIndex.DIRT)));
		}
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
