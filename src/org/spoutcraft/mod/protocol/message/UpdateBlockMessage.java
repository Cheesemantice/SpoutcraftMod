package org.spoutcraft.mod.protocol.message;

import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.network.INetworkManager;
import org.spoutcraft.api.Spoutcraft;
import org.spoutcraft.api.block.BlockPrefab;
import org.spoutcraft.mod.block.SpoutcraftBlockPrefabRegistry;

public class UpdateBlockMessage implements Message {
	private final int id;
	private final BlockPrefab blockPrefab;

	public UpdateBlockMessage(int id, BlockPrefab blockPrefab) {
		this.id = id;
		this.blockPrefab = blockPrefab;
	}

	public int getId() {
		return id;
	}

	public BlockPrefab getBlock() {
		return blockPrefab;
	}

	@Override
	public void handle(Side side, INetworkManager manager, Player player) {
		if (side.isServer()) {
			throw new IllegalStateException("The server is not allowed to receive blocks");
		}
		Spoutcraft.getLogger().info("Received blockPrefab: " + blockPrefab + " with id: " + id + " from the server");
		((SpoutcraftBlockPrefabRegistry) Spoutcraft.getBlockPrefabRegistry()).prepareForRendering(null, blockPrefab);
	}
}
