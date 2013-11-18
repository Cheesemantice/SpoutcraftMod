package org.spoutcraft.api.gui;

import java.util.LinkedList;

import org.spoutcraft.api.addon.Addon;

public abstract class ScreenStack {
	private Screen focused = null;

	public abstract Screen put(Class<? extends Addon> clazz, Screen screen);

	public abstract Screen get(Class<? extends Addon> clazz, String identifier);

	public abstract Screen remove(Class<? extends Addon> clazz, String identifier);

	public abstract LinkedList<Screen> getAll(Class<? extends Addon> clazz);

	public abstract Class<? extends Addon> getAddonClassFor(Screen screen);

	public abstract void show(Class<? extends Addon> clazz, Screen screen);

	public abstract void show(Class<? extends Addon> clazz, String identifier);

	public boolean focus(Class<? extends Addon> clazz, String identifier) {
		onScreenFocus(focused);
		return (focused = get(clazz, identifier)) != null;
	}

	public void onScreenFocus(Screen previous) {

	}
}
