package org.spoutcraft.mod.protocol.message;

import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.network.INetworkManager;
import org.spoutcraft.api.util.SerializableHashMap;

public class AddonListMessage implements Message {
	// Addon Identifier -> MD5
	private final SerializableHashMap<String, String> map;

	public AddonListMessage(SerializableHashMap<String, String> map) {
		this.map = map;
	}

	public SerializableHashMap<String, String> getMap() {
		return map;
	}

	@Override
	public void handle(Side side, INetworkManager manager, Player player) {
	}
}
