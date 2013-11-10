package org.spoutcraft.api.item;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spoutcraft.api.Prefab;

public abstract class ItemPrefab extends Prefab {
	private final ItemType type;
	private final String displayName;
	private final int maxStackSize;

	public ItemPrefab(String identifier, String displayName, int maxStackSize) {
		this(identifier, ItemType.GENERIC, displayName, maxStackSize);
	}

	public ItemPrefab(String identifier, ItemType type, String displayName, int maxStackSize) {
		super(identifier);
		this.type = type;
		this.displayName = displayName;
		this.maxStackSize = maxStackSize;
	}

	public ItemType getType() {
		return type;
	}

	public String getDisplayName() {
		return displayName;
	}

	public int getMaxStackSize() {
		return maxStackSize;
	}

	public abstract ItemStack onItemRightClick(Side side, ItemStack stack, World world, EntityPlayer player);

	@Override
	public String toString() {
		final String NEW_LINE = System.getProperty("line.separator");
		final String parent = super.toString();
		final StringBuilder builder = new StringBuilder(parent.substring(0, parent.length() - 1) + NEW_LINE);
		builder
				.append(" Type: " + type.name() + NEW_LINE)
				.append(" Display Name: " + displayName + NEW_LINE)
				.append(" Max Stack Size: " + maxStackSize + NEW_LINE)
				.append("}");
		return builder.toString();
	}
}
