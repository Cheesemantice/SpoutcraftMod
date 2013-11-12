package org.spoutcraft.mod.game;

import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spoutcraft.api.Spoutcraft;

public class CustomTabs extends CreativeTabs {
	public CustomTabs() {
		super("Spoutcraft");
		LanguageRegistry.instance().addStringLocalization("itemGroup.Spoutcraft", "en_US", "Spoutcraft");
	}

	@Override
	public ItemStack getIconItemStack() {
		return new ItemStack((Item) Spoutcraft.getItemPrefabRegistry().find("spoutcraft_emblem"), 1, 0);
	}
}
