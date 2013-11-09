package org.spoutcraft.mod.item;

import net.minecraft.item.ItemFood;
import org.spoutcraft.api.item.FoodPrefab;
import org.spoutcraft.api.item.SpoutcraftItem;
import org.spoutcraft.mod.SpoutcraftMod;

public class CustomFood extends ItemFood implements SpoutcraftItem {
	private final FoodPrefab prefab;

	public CustomFood(int id, FoodPrefab prefab) {
		super(id, prefab.getHealAmount(), prefab.getSaturationModifier(), prefab.isWolfFavorite());
		this.prefab = prefab;
		setCreativeTab(SpoutcraftMod.getCustomTabs());
		setUnlocalizedName("spoutcraft:" + prefab.getName());
		setTextureName("spoutcraft:" + prefab.getName());
		setMaxStackSize(prefab.getMaxStackSize());
	}

	@Override
	public FoodPrefab getPrefab() {
		return prefab;
	}
}
