package org.spoutcraft.mod.block;

import java.util.concurrent.atomic.AtomicInteger;

import gnu.trove.map.hash.TIntObjectHashMap;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

import org.spoutcraft.api.block.Block;
import org.spoutcraft.api.block.BlockRegistry;
import org.spoutcraft.mod.material.SpoutcraftMaterial;

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
        final SpoutcraftBlock spoutcraftBlock = new SpoutcraftBlock(id, block, new SpoutcraftMaterial((block.getMaterial())));
		LanguageRegistry.addName(spoutcraftBlock, spoutcraftBlock.getApiBlock().getDisplayName());
		GameRegistry.registerBlock(spoutcraftBlock, block.getDisplayName());
		return registry.put(id, block);
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
