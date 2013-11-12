package org.spoutcraft.mod.block;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.network.INetworkManager;
import org.spoutcraft.api.LinkedPrefabRegistry;
import org.spoutcraft.api.Spoutcraft;
import org.spoutcraft.api.block.BlockPrefab;
import org.spoutcraft.api.block.MovingPrefab;
import org.spoutcraft.mod.material.CustomMaterial;
import org.spoutcraft.mod.protocol.SpoutcraftPacket;
import org.spoutcraft.mod.protocol.message.AddPrefabMessage;

public class SpoutcraftBlockPrefabRegistry implements LinkedPrefabRegistry<BlockPrefab, Block> {
	private static final ArrayList<BlockPrefab> REGISTRY = new ArrayList<>();
	private static final AtomicInteger ID_COUNTER = new AtomicInteger(0);
	//INTERNAL
	private static final HashMap<BlockPrefab, Block> PREFAB_BY_BLOCK = new HashMap<>();
	private static final int ID_START = 2000;

	@Override
	public BlockPrefab put(BlockPrefab prefab) {
		create(prefab);
		return prefab;
	}

	@Override
	public Block create(BlockPrefab prefab) {
		if (prefab == null) {
			throw new IllegalStateException("Attempt made to put null block prefab into registry!");
		}

		CustomMaterial material = (CustomMaterial) Spoutcraft.getMaterialPrefabRegistry().find(prefab.getMaterialPrefab());
		if (material == null) {
			material = (CustomMaterial) Spoutcraft.getMaterialPrefabRegistry().create(prefab.getMaterialPrefab());
		}

		final int id = ID_START + ID_COUNTER.incrementAndGet();
		final Block block;
		if (prefab instanceof MovingPrefab) {
			block = new CustomSand(id, (MovingPrefab) prefab, material);
		} else {
			block = new CustomBlock(id, prefab, material);
		}

		REGISTRY.add(prefab);
		PREFAB_BY_BLOCK.put(prefab, block);

		//TODO Link ItemPrefab to BlockPrefab as an option
		GameRegistry.registerBlock(block, ItemBlock.class, prefab.getIdentifier(), "Spoutcraft");
		LanguageRegistry.addName(block, prefab.getDisplayName());
		if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
			PacketDispatcher.sendPacketToAllPlayers(new SpoutcraftPacket(new AddPrefabMessage(prefab)));
		}

		return block;
	}

	@Override
	public BlockPrefab get(String identifier) {
		if (identifier != null && !identifier.isEmpty()) {
			for (BlockPrefab prefab : REGISTRY) {
				if (prefab.getIdentifier().equals(identifier)) {
					return prefab;
				}
			}
		}
		return null;
	}

	@Override
	public Block find(BlockPrefab prefab) {
		return prefab == null ? null : PREFAB_BY_BLOCK.get(prefab);
	}

	@Override
	public Block find(String identifier) {
		if (identifier != null && !identifier.isEmpty()) {
			for (Map.Entry<BlockPrefab, Block> entry : PREFAB_BY_BLOCK.entrySet()) {
				if (entry.getKey().getIdentifier().equals(identifier)) {
					return entry.getValue();
				}
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
			network.addToSendQueue(new SpoutcraftPacket(new AddPrefabMessage(prefab)));
		}
	}
}
