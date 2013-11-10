package org.spoutcraft.mod.protocol.message;

import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.network.INetworkManager;
import org.spoutcraft.api.Prefab;
import org.spoutcraft.api.Spoutcraft;

public class UpdatePrefabMessage implements Message {
	private final Prefab prefab;

	public UpdatePrefabMessage(Prefab Prefab) {
		this.prefab = Prefab;
	}

	public Prefab getPrefab() {
		return prefab;
	}

	@Override
	public void handle(Side side, INetworkManager manager, Player player) {
		if (side.isServer()) {
			throw new IllegalStateException("The server is not allowed to receive prefabs");
		}
		Spoutcraft.getLogger().info("Received prefab from the server");
		Spoutcraft.getLogger().info(prefab.toString());
	}
}
