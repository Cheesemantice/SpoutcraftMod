package org.spoutcraft.mod.block;

import java.util.concurrent.atomic.AtomicInteger;

import gnu.trove.map.hash.TIntObjectHashMap;
import gnu.trove.procedure.TIntObjectProcedure;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.ItemBlock;
import net.minecraft.network.INetworkManager;

import org.spoutcraft.api.Spoutcraft;
import org.spoutcraft.api.block.Block;
import org.spoutcraft.api.block.BlockRegistry;
import org.spoutcraft.api.resource.type.Texture;
import org.spoutcraft.mod.material.SpoutcraftMaterial;
import org.spoutcraft.mod.protocol.SpoutcraftPacket;
import org.spoutcraft.mod.protocol.message.UpdateBlockMessage;

public class SpoutcraftBlockRegistry implements BlockRegistry {
	// ID -> Block
	private final TIntObjectHashMap<Block> registry = new TIntObjectHashMap<>();
	private final AtomicInteger idCounter = new AtomicInteger(0);

	@Override
	public Block put(Block block) {
		Spoutcraft.getLogger().info("Putting block: " + block + " into registry");
		if (block == null) {
			throw new IllegalStateException("Attempt to add a null block to the registry!");
		}
		if (contains(block.getName())) {
			throw new IllegalStateException("An attempt was made to put a duplicate named block in the registry!");
		}
		final int id = 2000 + idCounter.incrementAndGet();
		final SpoutcraftBlock spoutcraftBlock = new SpoutcraftBlock(id, block, new SpoutcraftMaterial(block.getMaterial()));
		GameRegistry.registerBlock(spoutcraftBlock, ItemBlock.class, block.getName(), "Spoutcraft");
		LanguageRegistry.addName(spoutcraftBlock, block.getDisplayName());
		registry.put(id, block);
		if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
			PacketDispatcher.sendPacketToAllPlayers(new SpoutcraftPacket(new UpdateBlockMessage(id, block)));
		}
		return block;
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

	/**
	 * Syncs the entire block registry to the client
	 * @param network The connected network
	 */
	public void sync(final INetworkManager network) {
		Spoutcraft.getLogger().info("Preparing to sync block registry");
		registry.forEachEntry(new TIntObjectProcedure<Block>() {
			@Override
			public boolean execute(int i, Block block) {
				Spoutcraft.getLogger().info("Sending block: " + block + " to network: " + network);
				network.addToSendQueue(new SpoutcraftPacket(new UpdateBlockMessage(i, block)));
				return true;
			}
		});
	}

	public <R extends Texture> void prepareForRendering(R resource, Block block) {

	}
}
