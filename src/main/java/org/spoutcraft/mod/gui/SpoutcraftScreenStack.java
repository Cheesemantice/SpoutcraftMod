package org.spoutcraft.mod.gui;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import org.spoutcraft.api.addon.Addon;
import org.spoutcraft.api.gui.Screen;
import org.spoutcraft.api.gui.ScreenStack;

public class SpoutcraftScreenStack extends ScreenStack {
	private static final HashMap<Class<? extends Addon>, LinkedList<Screen>> ADDONS_BY_SCREENS = new HashMap<>();
	private Screen focused = null;

	@Override
	public Screen put(Class<? extends Addon> clazz, Screen screen) {
		if (clazz == null) {
			throw new IllegalArgumentException("Attempt to put a null addon class for the ScreenStack!");
		}
		if (screen == null) {
			throw new IllegalArgumentException("Attempt to put a null screen into the ScreenStack!");
		}
		LinkedList<Screen> addonScreens = ADDONS_BY_SCREENS.get(clazz);
		if (addonScreens == null) {
			addonScreens = new LinkedList<>();
			ADDONS_BY_SCREENS.put(clazz, addonScreens);
		}
		addonScreens.add(screen);
		return screen;
	}

	@Override
	public Screen get(Class<? extends Addon> clazz, String identifier) {
		if (clazz == null) {
			throw new IllegalArgumentException("Attempt to get a null addon class for the ScreenStack!");
		}
		if (identifier == null || identifier.isEmpty()) {
			throw new IllegalArgumentException("Attempt to get a screen from the screen stack but the identifier is invalid <" + identifier + ">");
		}
		LinkedList<Screen> addonScreens = ADDONS_BY_SCREENS.get(clazz);
		if (addonScreens != null && !addonScreens.isEmpty()) {
			for (Screen screen : addonScreens) {
				if (screen.getIdentifier().equals(identifier)) {
					return screen;
				}
			}
		}
		return null;
	}

	@Override
	public Screen remove(Class<? extends Addon> clazz, String identifier) {
		if (clazz == null) {
			throw new IllegalArgumentException("Attempt to remove a null addon class for the ScreenStack!");
		}
		if (identifier == null || identifier.isEmpty()) {
			throw new IllegalArgumentException("Attempt to remove a screen from the screen stack but the identifier is invalid <" + identifier + ">");
		}
		LinkedList<Screen> addonScreens = ADDONS_BY_SCREENS.get(clazz);
		Screen found = null;
		if (addonScreens != null && !addonScreens.isEmpty()) {
			Iterator<Screen> addonScreensIterator = addonScreens.iterator();
			while(addonScreensIterator.hasNext()) {
				found = addonScreensIterator.next();
				if (found.getIdentifier().equals(identifier)) {
					addonScreensIterator.remove();
				}
			}
		}
		return found;
	}

	@Override
	public LinkedList<Screen> getAll(Class<? extends Addon> clazz) {
		if (clazz == null) {
			throw new IllegalArgumentException("Attempt to getAll a null addon class for the ScreenStack!");
		}
		return ADDONS_BY_SCREENS.get(clazz);
	}

	@Override
	public Class<? extends Addon> getAddonClassFor(Screen screen) {
		if (screen == null) {
			throw new IllegalArgumentException("Attempt to get addon class for a null screen!");
		}
		for (Map.Entry<Class<? extends Addon>, LinkedList<Screen>> entry : ADDONS_BY_SCREENS.entrySet()) {
			for (Screen registered : entry.getValue()) {
				if (registered.equals(screen)) {
					return entry.getKey();
				}
			}
		}
		return null;
	}

	@Override
	public void show(Class<? extends Addon> clazz, Screen screen) {

	}

	@Override
	public void show(Class<? extends Addon> clazz, String identifier) {

	}
}
