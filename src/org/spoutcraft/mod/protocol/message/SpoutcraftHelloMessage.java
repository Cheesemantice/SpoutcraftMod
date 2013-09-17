package org.spoutcraft.mod.protocol.message;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.network.INetworkManager;
import org.spoutcraft.mod.protocol.SpoutcraftPacket;

import java.io.*;

public class SpoutcraftHelloMessage extends SpoutcraftMessage {
    public SpoutcraftHelloMessage() {
        super("SpoutcraftHello");
    }

    @Override
    public ByteArrayOutputStream encode() throws Exception {
        final Side side = FMLCommonHandler.instance().getEffectiveSide();
        String helloWorld;
        if (side == Side.CLIENT) {
            helloWorld = "Hello from the client";
        } else if (side == Side.SERVER) {
            helloWorld = "Hello from the server";
        } else {
            helloWorld = "Hello from that shitty thing called Bukkit lol...";
        }
        final ByteArrayOutputStream toEncode = new ByteArrayOutputStream(helloWorld.length());
        final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(toEncode));
        writer.write(helloWorld);
        writer.flush();
        writer.close();
        return toEncode;
    }

    @Override
    public void handle(Side side, INetworkManager manager, ByteArrayInputStream data, Player player) throws Exception {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(data));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        if (side == Side.CLIENT) {
            PacketDispatcher.sendPacketToServer(new SpoutcraftPacket(this));
        }
    }
}
