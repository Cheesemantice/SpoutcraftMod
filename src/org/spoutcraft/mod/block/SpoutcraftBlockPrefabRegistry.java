package org.spoutcraft.mod.block;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.network.INetworkManager;
import org.spoutcraft.api.PrefabRegistry;
import org.spoutcraft.api.Spoutcraft;
import org.spoutcraft.api.block.BlockPrefab;
import org.spoutcraft.mod.material.CustomMaterial;
import org.spoutcraft.mod.protocol.SpoutcraftPacket;
import org.spoutcraft.mod.protocol.message.UpdatePrefabMessage;

public class SpoutcraftBlockPrefabRegistry implements PrefabRegistry<BlockPrefab> {
	private static final ArrayList<BlockPrefab> REGISTRY = new ArrayList<>();
	private static final AtomicInteger ID_COUNTER = new AtomicInteger(0);
	//INTERNAL
	private static final HashMap<BlockPrefab, Block> PREFAB_BY_BLOCK = new HashMap<>();
	private static final int ID_START = 2000;

	@Override
	public BlockPrefab put(BlockPrefab prefab) {
		Spoutcraft.getLogger().info("Putting block prefab into registry");
		Spoutcraft.getLogger().info(prefab.toString());
		if (prefab == null) {
			throw new IllegalStateException("Attempt to add a null block prefab to the registry!");
		}
		final int id = ID_START + ID_COUNTER.incrementAndGet();
		final Block block;
		switch (prefab.getType()) {
			case SAND:
				block = new CustomSand(id, prefab, new CustomMaterial(prefab.getMaterialPrefab()));
				break;
			default:
				block = new CustomBlock(id, prefab, new CustomMaterial(prefab.getMaterialPrefab()));
		}
		REGISTRY.add(prefab);
		PREFAB_BY_BLOCK.put(prefab, block);
		//TODO Link ItemPrefab to BlockPrefab as an option
		GameRegistry.registerBlock(block, ItemBlock.class, prefab.getIdentifier(), "Spoutcraft");
		LanguageRegistry.addName(block, prefab.getDisplayName());
		if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
			PacketDispatcher.sendPacketToAllPlayers(new SpoutcraftPacket(new UpdatePrefabMessage(prefab)));
		}
		return prefab;
	}

	@Override
	public BlockPrefab get(String identifier) {
		for (BlockPrefab prefab : REGISTRY) {
			if (prefab.getIdentifier().equals(identifier)) {
				return prefab;
			}
		}
		return null;
	}

	/**
	 * Syncs the entire block registry to the client
	 *
	 * @param network The connected network
	 */
	public void sync(final INetworkManager network) {
		Spoutcraft.getLogger().info("Preparing to sync block registry");
		//TODO Scheduler and sending
		for (BlockPrefab prefab : REGISTRY) {
			Spoutcraft.getLogger().info("Syncing block prefab to client");
			Spoutcraft.getLogger().info(prefab.toString());
			network.addToSendQueue(new SpoutcraftPacket(new UpdatePrefabMessage(prefab)));
		}
	}
}
