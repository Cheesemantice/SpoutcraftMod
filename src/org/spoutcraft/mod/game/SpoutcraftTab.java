package org.spoutcraft.mod.game;

import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SpoutcraftTab extends CreativeTabs {
	public SpoutcraftTab() {
		super("Spoutcraft");
		LanguageRegistry.instance().addStringLocalization("itemGroup.Spoutcraft", "en_US", "Spoutcraft");
	}

	@Override
	public ItemStack getIconItemStack() {
		return new ItemStack(Item.appleRed, 1, 0);
	}
}
