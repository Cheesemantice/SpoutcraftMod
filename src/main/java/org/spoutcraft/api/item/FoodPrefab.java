package org.spoutcraft.api.item;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class FoodPrefab extends ItemPrefab {
	private final int healAmount;
	private final int saturationModifier;
	private final boolean wolfFavorite;

	public FoodPrefab(String identifier, String displayName, int maxStackSize, int healAmount, int saturationModifier, boolean wolfFavorite, boolean showInCreativeTab) {
		super(identifier, displayName, maxStackSize, showInCreativeTab);
		this.healAmount = healAmount;
		this.saturationModifier = saturationModifier;
		this.wolfFavorite = wolfFavorite;
	}

	public int getHealAmount() {
		return healAmount;
	}

	public int getSaturationModifier() {
		return saturationModifier;
	}

	public boolean isWolfFavorite() {
		return wolfFavorite;
	}

	public ItemStack onEaten(Side side, ItemStack stack, World world, EntityPlayer player) {
		return stack;
	}

	@Override
	public String toString() {
		final String NEW_LINE = System.getProperty("line.separator");
		final String parent = super.toString();
		final StringBuilder builder = new StringBuilder(parent.substring(0, parent.length() - 1) + NEW_LINE);
		builder
				.append(" Heal Amount: " + healAmount + NEW_LINE)
				.append(" Saturation Modifier: " + saturationModifier + NEW_LINE)
				.append(" Wolf Favorite: " + wolfFavorite + NEW_LINE)
				.append("}");
		return builder.toString();
	}
}
