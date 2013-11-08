package org.spoutcraft.api;

import org.spoutcraft.api.block.BlockPrefabRegistry;
import org.spoutcraft.api.item.ItemPrefabRegistry;
import org.spoutcraft.api.logger.AbstractLogger;
import org.spoutcraft.api.material.MaterialPrefabRegistry;
import org.spoutcraft.api.resource.FileSystem;

/**
 * Represents the Spoutcraft core with access to necessary registries
 */
public final class Spoutcraft {
	private static AbstractLogger logger;
	private static BlockPrefabRegistry blockPrefabRegistry;
	private static ItemPrefabRegistry itemPrefabRegistry;
	private static MaterialPrefabRegistry materialPrefabRegistry;
	private static FileSystem fileSystem;
	private static boolean enabled = false;

	public static boolean isSpoutcraftEnabled() {
		return enabled;
	}

	public static AbstractLogger getLogger() {
		return logger;
	}

	public static BlockPrefabRegistry getBlockPrefabRegistry() {
		return blockPrefabRegistry;
	}

	public static ItemPrefabRegistry getItemPrefabRegistry() {
		return itemPrefabRegistry;
	}

	public static MaterialPrefabRegistry getMaterialPrefabRegistry() {
		return materialPrefabRegistry;
	}

	public static FileSystem getFileSystem() {
		return fileSystem;
	}

	/**
	 * INTERNAL USE ONLY *
	 */
	public static void enable() {
		enabled = true; //TODO Discover a better way to do this
	}

	public static AbstractLogger setLogger(AbstractLogger logger) {
		Spoutcraft.logger = logger;
		return logger;
	}

	public static BlockPrefabRegistry setBlockRegistry(BlockPrefabRegistry blockPrefabRegistry) {
		if (Spoutcraft.blockPrefabRegistry != null) {
			throw new IllegalStateException("Attempt to assign block registry twice!");
		}
		if (blockPrefabRegistry == null) {
			throw new IllegalStateException("Attempt to assign a null block registry!");
		}
		Spoutcraft.blockPrefabRegistry = blockPrefabRegistry;
		return blockPrefabRegistry;
	}

	public static ItemPrefabRegistry setItemPrefabRegistry(ItemPrefabRegistry itemPrefabRegistry) {
		if (Spoutcraft.itemPrefabRegistry != null) {
			throw new IllegalStateException("Attempt to assign item prefab registry twice!");
		}
		if (itemPrefabRegistry == null) {
			throw new IllegalStateException("Attempt to assign a null item prefab registry!");
		}
		Spoutcraft.itemPrefabRegistry = itemPrefabRegistry;
		return itemPrefabRegistry;
	}

	public static MaterialPrefabRegistry setMaterialRegistry(MaterialPrefabRegistry materialPrefabRegistry) {
		if (Spoutcraft.materialPrefabRegistry != null) {
			throw new IllegalStateException("Attempt to assign material registry twice!");
		}
		if (materialPrefabRegistry == null) {
			throw new IllegalStateException("Attempt to assign a null material registry!");
		}
		Spoutcraft.materialPrefabRegistry = materialPrefabRegistry;
		return materialPrefabRegistry;
	}

	public static FileSystem setFileSystem(FileSystem fileSystem) {
		if (Spoutcraft.fileSystem != null) {
			throw new IllegalStateException("Attempt to assign file system twice!");
		}
		if (fileSystem == null) {
			throw new IllegalStateException("Attempt to assign a null file system!");
		}
		Spoutcraft.fileSystem = fileSystem;
		return fileSystem;
	}
}
