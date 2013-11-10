package org.spoutcraft.api.item;

public class FoodPrefab extends ItemPrefab {
	private final int healAmount;
	private final int saturationModifier;
	private final boolean wolfFavorite;

	public FoodPrefab(String identifier, String displayName, int maxStackSize, int healAmount, int saturationModifier, boolean wolfFavorite) {
		super(identifier, ItemType.FOOD, displayName, maxStackSize);
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
