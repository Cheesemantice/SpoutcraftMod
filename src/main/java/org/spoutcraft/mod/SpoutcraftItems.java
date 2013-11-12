package org.spoutcraft.mod;

import net.minecraft.item.Item;
import org.spoutcraft.api.Spoutcraft;
import org.spoutcraft.mod.item.special.SpoutcraftEmblem;
import org.spoutcraft.mod.item.special.VanillaEmblem;

public class SpoutcraftItems {
	public static final Item SPOUTCRAFT_EMBLEM;
	public static final Item VANILLA_EMBLEM;

	static {
		SPOUTCRAFT_EMBLEM = (Item) Spoutcraft.getItemPrefabRegistry().create(new SpoutcraftEmblem());
		VANILLA_EMBLEM = (Item) Spoutcraft.getItemPrefabRegistry().create(new VanillaEmblem());
	}
}
