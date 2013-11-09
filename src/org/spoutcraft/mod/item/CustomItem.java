package org.spoutcraft.mod.item;

import net.minecraft.item.Item;
import org.spoutcraft.api.item.ItemPrefab;
import org.spoutcraft.mod.SpoutcraftMod;

public class CustomItem extends Item {
	private final ItemPrefab prefab;

	public CustomItem(int id, ItemPrefab prefab) {
		super(id);
		this.prefab = prefab;
		setCreativeTab(SpoutcraftMod.getCustomTabs());
		setUnlocalizedName("spoutcraft:" + prefab.getName());
		setTextureName("spoutcraft:" + prefab.getName());
		setMaxStackSize(prefab.getMaxStackSize());
	}

	public ItemPrefab getPrefab() {
		return prefab;
	}
}
