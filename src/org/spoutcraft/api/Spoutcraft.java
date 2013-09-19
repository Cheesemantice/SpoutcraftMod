package org.spoutcraft.api;

import org.spoutcraft.api.block.BlockRegistry;
import org.spoutcraft.api.logger.AbstractLogger;
import org.spoutcraft.api.material.MaterialRegistry;

/**
 * Represents the Spoutcraft core with access to necessary registries
 */
public final class Spoutcraft {
	private static AbstractLogger logger;
	private static BlockRegistry blockRegistry;
	private static MaterialRegistry materialRegistry;
	private static boolean enabled = false;

	public static boolean isSpoutcraftEnabled() {
		return enabled;
	}

	public static AbstractLogger getLogger() {
		return logger;
	}

	public static BlockRegistry getBlockRegistry() {
		return blockRegistry;
	}

	public static MaterialRegistry getMaterialRegistry() {
		return materialRegistry;
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

	public static BlockRegistry setBlockRegistry(BlockRegistry blockRegistry) {
		if (Spoutcraft.blockRegistry != null) {
			throw new IllegalStateException("Attempt to assign block registry twice!");
		}
		if (blockRegistry == null) {
			throw new IllegalStateException("Attempt to assign a null block registry!");
		}
		Spoutcraft.blockRegistry = blockRegistry;
		return blockRegistry;
	}

	public static MaterialRegistry setMaterialRegistry(MaterialRegistry materialRegistry) {
		if (Spoutcraft.materialRegistry != null) {
			throw new IllegalStateException("Attempt to assign material registry twice!");
		}
		if (materialRegistry == null) {
			throw new IllegalStateException("Attempt to assign a null material registry!");
		}
		Spoutcraft.materialRegistry = materialRegistry;
		return materialRegistry;
	}
}
