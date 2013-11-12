package org.spoutcraft.api.addon;

import org.spoutcraft.api.LinkedPrefabRegistry;

public class AddonPrefabRegistry implements LinkedPrefabRegistry<AddonPrefab, Addon> {
	@Override
	public AddonPrefab put(AddonPrefab prefab) {
		return null;
	}

	@Override
	public AddonPrefab get(String identifier) {
		return null;
	}

	@Override
	public Addon create(AddonPrefab prefab) {
		return null;
	}

	@Override
	public Addon find(AddonPrefab prefab) {
		return null;
	}

	@Override
	public Addon find(String identifier) {
		return null;
	}

	public void enable(Addon addon) {

	}
}
