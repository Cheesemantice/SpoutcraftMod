package org.spoutcraft.mod.protocol.message;

import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.network.INetworkManager;
import org.spoutcraft.api.resource.Resource;

public class AddResourceMessage implements Message {
	private final Resource resource;

	public AddResourceMessage(Resource resource) {
		this.resource = resource;
	}

	public Resource getResource() {
		return resource;
	}

	@Override
	public void handle(Side side, INetworkManager manager, Player player) {
	}
}
