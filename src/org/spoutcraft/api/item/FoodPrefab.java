package org.spoutcraft.api.item;

public class FoodPrefab extends ItemPrefab {
	private final int healAmount;
	private final int saturationModifier;
	private final boolean wolfFavorite;

	public FoodPrefab(String name, String displayName, int maxStackSize, int healAmount, int saturationModifier, boolean wolfFavorite) {
		super(ItemType.FOOD, name, displayName, maxStackSize);
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
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		if (!super.equals(o)) {
			return false;
		}

		final FoodPrefab that = (FoodPrefab) o;

		if (healAmount != that.healAmount) {
			return false;
		}
		if (saturationModifier != that.saturationModifier) {
			return false;
		}
		if (wolfFavorite != that.wolfFavorite) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + healAmount;
		result = 31 * result + saturationModifier;
		result = 31 * result + (wolfFavorite ? 1 : 0);
		return result;
	}
}
