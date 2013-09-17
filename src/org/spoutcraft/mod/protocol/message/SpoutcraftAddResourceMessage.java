package org.spoutcraft.mod.protocol.message;

import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.network.INetworkManager;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class SpoutcraftAddResourceMessage extends SpoutcraftMessage {
    public SpoutcraftAddResourceMessage() {
        super("SpoutcraftAddRes");
    }

    @Override
    public ByteArrayOutputStream encode() {
        return new ByteArrayOutputStream(0);
    }

    @Override
    public void handle(Side side, INetworkManager manager, ByteArrayInputStream data, Player player) {

    }
}
