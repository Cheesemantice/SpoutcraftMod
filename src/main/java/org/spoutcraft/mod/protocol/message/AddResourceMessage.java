package org.spoutcraft.mod.protocol.message;

import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.network.INetworkManager;

public class AddResourceMessage<R> implements Message {
	private final R resource;

	public AddResourceMessage(R resource) {
		this.resource = resource;
	}

	public R getResource() {
		return resource;
	}

	@Override
	public void handle(Side side, INetworkManager manager, Player player) {
	}
}
