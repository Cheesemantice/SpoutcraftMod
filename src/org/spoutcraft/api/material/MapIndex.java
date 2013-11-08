package org.spoutcraft.api.material;

/**
 * An enum of color indexes that are used when the client renders an in-game map. <p/> Unfortunately Minecraft limits the amount of color indexes so the following are the only available choices.
 */
public enum MapIndex {
	AIR(0),
	GRASS(1),
	SAND(2),
	CLOTH(3),
	TNT(4),
	ICE(5),
	IRON(6),
	FOLIAGE(7),
	SNOW(8),
	CLAY(9),
	DIRT(10),
	STONE(11),
	WATER(12),
	WOOD(13);
	private final int index;

	MapIndex(int index) {
		this.index = index;
	}

	public int getIndex() {
		return index;
	}
}
