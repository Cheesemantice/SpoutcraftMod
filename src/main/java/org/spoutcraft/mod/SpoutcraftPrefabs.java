package org.spoutcraft.mod;

import org.spoutcraft.api.Spoutcraft;
import org.spoutcraft.mod.item.special.SpoutcraftEmblem;
import org.spoutcraft.mod.item.special.VanillaEmblem;

public class SpoutcraftPrefabs {
	public static final SpoutcraftEmblem SPOUTCRAFT_EMBLEM = new SpoutcraftEmblem();
	public static final VanillaEmblem VANILLA_EMBLEM = new VanillaEmblem();

	public static void init() {
		Spoutcraft.getItemPrefabRegistry().put(VANILLA_EMBLEM);
		Spoutcraft.getItemPrefabRegistry().put(SPOUTCRAFT_EMBLEM);
	}
}
