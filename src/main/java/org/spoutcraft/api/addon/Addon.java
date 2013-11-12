package org.spoutcraft.api.addon;

import java.nio.file.Path;

import cpw.mods.fml.relauncher.Side;

/**
 * An addon is like a Mod or a Plugin (from Bukkit). It is the bridge between external code and Spoutcraft API's framework.
 *
 * The power of addons lie in the ability to use Spoutcraft API and Forge without needing to make a new Mod.
 */
public abstract class Addon {
	private AddonPrefabRegistry loader;
	private AddonPrefab prefab;
	private AddonClassLoader classLoader;
	private Path dataPath, root;
	private boolean enabled = false;

	public void initialize(AddonPrefabRegistry loader, AddonPrefab prefab, AddonClassLoader classLoader, Path dataPath, Path root) {
		this.loader = loader;
		this.prefab = prefab;
		this.classLoader = classLoader;
		this.dataPath = dataPath;
		this.root = root;
	}

	public void onLoad(Side side) {

	}

	public void onEnable(Side side) {

	}

	public AddonPrefab getPrefab() {
		return prefab;
	}

	protected void enable() {
		this.enabled = true;
	}

	protected void disable() {
		this.enabled = false;
	}
}
