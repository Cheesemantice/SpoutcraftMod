package org.spoutcraft.api;

import org.spoutcraft.api.block.BlockPrefab;
import org.spoutcraft.api.item.ItemPrefab;
import org.spoutcraft.api.logger.AbstractLogger;
import org.spoutcraft.api.material.MaterialPrefab;
import org.spoutcraft.api.resource.FileSystem;

/**
 * Represents the Spoutcraft core with access to necessary registries
 */
public final class Spoutcraft {
	private static AbstractLogger logger;
	private static LinkedPrefabRegistry<? extends BlockPrefab, ?> blockPrefabRegistry;
	private static LinkedPrefabRegistry<? extends ItemPrefab, ?> itemPrefabRegistry;
	private static LinkedPrefabRegistry<? extends MaterialPrefab, ?> materialPrefabRegistry;
	private static FileSystem fileSystem;

	public static AbstractLogger getLogger() {
		return logger;
	}

	public static LinkedPrefabRegistry getBlockPrefabRegistry() {
		return blockPrefabRegistry;
	}

	public static LinkedPrefabRegistry getItemPrefabRegistry() {
		return itemPrefabRegistry;
	}

	public static LinkedPrefabRegistry getMaterialPrefabRegistry() {
		return materialPrefabRegistry;
	}

	public static FileSystem getFileSystem() {
		return fileSystem;
	}

	/**
	 * INTERNAL USE ONLY
	 */
	public static AbstractLogger setLogger(AbstractLogger logger) {
		Spoutcraft.logger = logger;
		return logger;
	}

	public static LinkedPrefabRegistry setBlockRegistry(LinkedPrefabRegistry<? extends BlockPrefab, ?> prefabRegistry) {
		if (Spoutcraft.blockPrefabRegistry != null) {
			throw new IllegalStateException("Attempt to assign block registry twice!");
		}
		if (prefabRegistry == null) {
			throw new IllegalStateException("Attempt to assign a null block registry!");
		}
		Spoutcraft.blockPrefabRegistry = prefabRegistry;
		return prefabRegistry;
	}

	public static LinkedPrefabRegistry setItemPrefabRegistry(LinkedPrefabRegistry<? extends ItemPrefab, ?> itemPrefabRegistry) {
		if (Spoutcraft.itemPrefabRegistry != null) {
			throw new IllegalStateException("Attempt to assign item prefab registry twice!");
		}
		if (itemPrefabRegistry == null) {
			throw new IllegalStateException("Attempt to assign a null item prefab registry!");
		}
		Spoutcraft.itemPrefabRegistry = itemPrefabRegistry;
		return itemPrefabRegistry;
	}

	public static LinkedPrefabRegistry setMaterialRegistry(LinkedPrefabRegistry<? extends MaterialPrefab, ?> materialPrefabRegistry) {
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
