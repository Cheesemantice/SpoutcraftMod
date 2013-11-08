package org.spoutcraft.mod.item;

import java.util.concurrent.atomic.AtomicInteger;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import gnu.trove.map.hash.TIntObjectHashMap;
import org.spoutcraft.api.Spoutcraft;
import org.spoutcraft.api.item.ItemPrefab;
import org.spoutcraft.api.item.ItemPrefabRegistry;

public class SpoutcraftItemPrefabRegistry implements ItemPrefabRegistry {
	// ID -> Block
	private final TIntObjectHashMap<ItemPrefab> registry = new TIntObjectHashMap<>();
	private final AtomicInteger idCounter = new AtomicInteger(0);

	@Override
	public ItemPrefab put(ItemPrefab prefab) {
		Spoutcraft.getLogger().info("Putting item prefab: " + prefab + " into registry");
		if (prefab == null) {
			throw new IllegalStateException("Attempt to add a null item prefab to the registry!");
		}
		if (contains(prefab.getName())) {
			throw new IllegalStateException("An attempt was made to put a duplicate named item prefab in the registry!");
		}
		final int id = 1000 + idCounter.incrementAndGet();
		final CustomItem customItem = new CustomItem(id, prefab);
		GameRegistry.registerItem(customItem, prefab.getName());
		LanguageRegistry.addName(customItem, prefab.getDisplayName());
		registry.put(id, prefab);
		//TODO Send AddItemMessage to all players
		return prefab;
	}

	@Override
	public ItemPrefab get(String name) {
		for (ItemPrefab prefab : registry.valueCollection()) {
			if (prefab.getName().equals(name)) {
				return prefab;
			}
		}
		return null;
	}

	@Override
	public boolean contains(String name) {
		return get(name) != null;
	}
}
