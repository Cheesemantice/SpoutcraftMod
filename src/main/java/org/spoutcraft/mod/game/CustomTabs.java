package org.spoutcraft.mod.game;

import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spoutcraft.mod.SpoutcraftItems;

public class CustomTabs extends CreativeTabs {
	public CustomTabs() {
		super("Spoutcraft");
		LanguageRegistry.instance().addStringLocalization("itemGroup.Spoutcraft", "en_US", "Spoutcraft");
	}

	@Override
	public ItemStack getIconItemStack() {
		return new ItemStack(SpoutcraftItems.SPOUTCRAFT_EMBLEM, 1, 0);
	}
}
