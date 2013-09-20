package org.spoutcraft.mod.block;

import java.util.concurrent.atomic.AtomicInteger;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;

import gnu.trove.map.hash.TIntObjectHashMap;
import org.spoutcraft.api.block.Block;
import org.spoutcraft.api.block.BlockRegistry;
import org.spoutcraft.mod.material.SpoutcraftMaterial;
import org.spoutcraft.mod.protocol.SpoutcraftPacket;
import org.spoutcraft.mod.protocol.message.AddBlockMessage;

public class SpoutcraftBlockRegistry implements BlockRegistry {
	private final int idStart = 2000; // TODO Verify if this is fine
	// ID -> Block
	private final TIntObjectHashMap<Block> registry = new TIntObjectHashMap<>();
	private final AtomicInteger idCounter = new AtomicInteger(0);

	@Override
	public Block put(Block block) {
		if (block == null) {
			throw new IllegalStateException("Attempt to add a null block to the registry!");
		}
		if (contains(block.getName())) {
			throw new IllegalStateException("An attempt was made to put a duplicate named block in the registry!");
		}
		final int id = idStart + idCounter.incrementAndGet();
		final Block put = registry.put(id, block);
		register(id, block);
		if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
			PacketDispatcher.sendPacketToAllPlayers(new SpoutcraftPacket(new AddBlockMessage(id, block)));
		}
		return put;
	}

	public void register(int id, Block block) {
		if (FMLCommonHandler.instance().getEffectiveSide().isClient()) {
			if (block == null) {
				throw new IllegalStateException("Attempt to add a null block to the registry!");
			}
			if (contains(block.getName())) {
				throw new IllegalStateException("An attempt was made to put a duplicate named block in the registry!");
			}
		}
		final SpoutcraftBlock spoutcraftBlock = new SpoutcraftBlock(id, block, new SpoutcraftMaterial(block.getMaterial()));
		LanguageRegistry.addName(spoutcraftBlock, block.getDisplayName());
		GameRegistry.registerBlock(spoutcraftBlock, block.getDisplayName());
	}

	@Override
	public Block get(String name) {
		for (Block block : registry.valueCollection()) {
			if (block.getName().equals(name)) {
				return block;
			}
		}
		return null;
	}

	@Override
	public boolean contains(String name) {
		return get(name) != null;
	}
}
