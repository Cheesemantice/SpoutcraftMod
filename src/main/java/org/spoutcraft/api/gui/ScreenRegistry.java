package org.spoutcraft.api.gui;

import java.util.Collection;

import org.spoutcraft.api.addon.Addon;

public interface ScreenRegistry {
	public Screen put(Screen screen);

	public Screen get(Class<Screen> screenClass);

	public Screen remove(Class<Screen> screenClass);

	public Collection<Screen> getAll(Class<Addon> addonClass);

	public void focus(Class<Screen> screenClass);
}
