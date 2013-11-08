package org.spoutcraft.mod.block;

import java.util.concurrent.atomic.AtomicInteger;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import gnu.trove.map.hash.TIntObjectHashMap;
import gnu.trove.procedure.TIntObjectProcedure;
import net.minecraft.item.ItemBlock;
import net.minecraft.network.INetworkManager;
import org.spoutcraft.api.Spoutcraft;
import org.spoutcraft.api.block.BlockPrefab;
import org.spoutcraft.api.block.BlockPrefabRegistry;
import org.spoutcraft.api.resource.type.Texture;
import org.spoutcraft.mod.material.CustomMaterial;
import org.spoutcraft.mod.protocol.SpoutcraftPacket;
import org.spoutcraft.mod.protocol.message.UpdateBlockMessage;

public class SpoutcraftBlockPrefabRegistry implements BlockPrefabRegistry {
	// ID -> Block
	private final TIntObjectHashMap<BlockPrefab> registry = new TIntObjectHashMap<>();
	private final AtomicInteger idCounter = new AtomicInteger(0);

	@Override
	public BlockPrefab put(BlockPrefab blockPrefab) {
		Spoutcraft.getLogger().info("Putting blockPrefab: " + blockPrefab + " into registry");
		if (blockPrefab == null) {
			throw new IllegalStateException("Attempt to add a null blockPrefab to the registry!");
		}
		if (contains(blockPrefab.getName())) {
			throw new IllegalStateException("An attempt was made to put a duplicate named blockPrefab in the registry!");
		}
		final int id = 2000 + idCounter.incrementAndGet();
		final CustomBlock customBlock = new CustomBlock(id, blockPrefab, new CustomMaterial(blockPrefab.getMaterialPrefab()));
		GameRegistry.registerBlock(customBlock, ItemBlock.class, blockPrefab.getName(), "Spoutcraft");
		LanguageRegistry.addName(customBlock, blockPrefab.getDisplayName());
		registry.put(id, blockPrefab);
		if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
			PacketDispatcher.sendPacketToAllPlayers(new SpoutcraftPacket(new UpdateBlockMessage(id, blockPrefab)));
		}
		return blockPrefab;
	}

	@Override
	public BlockPrefab get(String name) {
		for (BlockPrefab blockPrefab : registry.valueCollection()) {
			if (blockPrefab.getName().equals(name)) {
				return blockPrefab;
			}
		}
		return null;
	}

	@Override
	public boolean contains(String name) {
		return get(name) != null;
	}

	/**
	 * Syncs the entire block registry to the client
	 *
	 * @param network The connected network
	 */
	public void sync(final INetworkManager network) {
		Spoutcraft.getLogger().info("Preparing to sync block registry");
		registry.forEachEntry(new TIntObjectProcedure<BlockPrefab>() {
			@Override
			public boolean execute(int i, BlockPrefab blockPrefab) {
				Spoutcraft.getLogger().info("Sending blockPrefab: " + blockPrefab + " to network: " + network);
				network.addToSendQueue(new SpoutcraftPacket(new UpdateBlockMessage(i, blockPrefab)));
				return true;
			}
		});
	}

	public <R extends Texture> void prepareForRendering(R resource, BlockPrefab blockPrefab) {

	}
}
