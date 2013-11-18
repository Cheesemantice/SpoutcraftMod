package org.spoutcraft.api.gui;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import org.spoutcraft.api.addon.Addon;

public class ScreenStack {
	private static final HashMap<Class<? extends Addon>, LinkedList<Screen>> ADDONS_BY_SCREENS = new HashMap<>();
	private Screen focused = null;

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

	public LinkedList<Screen> getAll(Class<? extends Addon> clazz) {
		if (clazz == null) {
			throw new IllegalArgumentException("Attempt to getAll a null addon class for the ScreenStack!");
		}
		return ADDONS_BY_SCREENS.get(clazz);
	}

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

	public boolean focus(Class<? extends Addon> clazz, String identifier) {
		onScreenFocus(focused);
		return (focused = get(clazz, identifier)) != null;
	}

	public void onScreenFocus(Screen previous) {

	}
}
