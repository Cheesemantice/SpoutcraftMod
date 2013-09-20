package org.spoutcraft.mod.protocol.message;

import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.network.INetworkManager;

import org.spoutcraft.api.Spoutcraft;
import org.spoutcraft.api.block.Block;
import org.spoutcraft.api.material.Material;
import org.spoutcraft.mod.block.SpoutcraftBlockRegistry;

public class AddBlockMessage implements Message {
	private final int id;
	private final Block block;

	public AddBlockMessage(int id, Block block) {
		this.id = id;
		this.block = block;
	}

	public int getId() {
		return id;
	}

	public Block getBlock() {
		return block;
	}

	@Override
	public void handle(Side side, INetworkManager manager, Player player) {
		if (side.isServer()) {
			throw new IllegalStateException("The server is not allowed to receive blocks");
		}
		Spoutcraft.getLogger().info("Received block: " + block + " with id: " + id + " from the server");
		final Material material = block.getMaterial();
		if (!Spoutcraft.getMaterialRegistry().contains(material.getName())) {
			Spoutcraft.getMaterialRegistry().add(material);
		}
		((SpoutcraftBlockRegistry) Spoutcraft.getBlockRegistry()).register(id, block);
	}
}
