package org.spoutcraft.mod.item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.item.Item;
import net.minecraft.network.INetworkManager;
import org.spoutcraft.api.PrefabRegistry;
import org.spoutcraft.api.Spoutcraft;
import org.spoutcraft.api.item.FoodPrefab;
import org.spoutcraft.api.item.ItemPrefab;
import org.spoutcraft.mod.protocol.SpoutcraftPacket;
import org.spoutcraft.mod.protocol.message.UpdatePrefabMessage;

public class SpoutcraftItemPrefabRegistry implements PrefabRegistry<ItemPrefab> {
	private static final ArrayList<ItemPrefab> REGISTRY = new ArrayList<>();
	private static final AtomicInteger ID_COUNTER = new AtomicInteger(0);
	//INTERNAL
	private static final HashMap<ItemPrefab, Item> PREFAB_BY_ITEM = new HashMap<>();
	private static final int ID_START = 200;

	@Override
	public ItemPrefab put(ItemPrefab prefab) {
		Spoutcraft.getLogger().info("Putting item prefab into registry");
		Spoutcraft.getLogger().info(prefab.toString());
		final int id = ID_START + ID_COUNTER.incrementAndGet();
		final Item item;
		switch (prefab.getType()) {
			case FOOD:
				item = new CustomFood(id, (FoodPrefab) prefab);
				break;
			default:
				item = new CustomItem(id, prefab);
		}
		REGISTRY.add(prefab);
		PREFAB_BY_ITEM.put(prefab, item);
		GameRegistry.registerItem(item, prefab.getIdentifier(), "Spoutcraft");
		LanguageRegistry.addName(item, prefab.getDisplayName());
		if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
			PacketDispatcher.sendPacketToAllPlayers(new SpoutcraftPacket(new UpdatePrefabMessage(prefab)));
		}
		return prefab;
	}

	@Override
	public ItemPrefab get(String identifier) {
		for (ItemPrefab prefab : REGISTRY) {
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
		Spoutcraft.getLogger().info("Preparing to sync item registry");
		//TODO Scheduler and sending
		for (ItemPrefab prefab : REGISTRY) {
			Spoutcraft.getLogger().info("Syncing item prefab to client");
			Spoutcraft.getLogger().info(prefab.toString());
			network.addToSendQueue(new SpoutcraftPacket(new UpdatePrefabMessage(prefab)));
		}
	}
}
