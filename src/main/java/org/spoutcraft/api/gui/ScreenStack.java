package org.spoutcraft.api.gui;

import java.util.LinkedList;

import org.spoutcraft.api.addon.Addon;
import org.spoutcraft.api.gui.result.FocusResult;

public abstract class ScreenStack {
	private Screen focused = null;

	public abstract Screen put(Class<? extends Addon> clazz, Screen screen);

	public abstract Screen get(Class<? extends Addon> clazz, String identifier);

	public abstract Screen remove(Class<? extends Addon> clazz, String identifier);

	public abstract LinkedList<Screen> getAll(Class<? extends Addon> clazz);

	public abstract Class<? extends Addon> getAddonClassFor(Screen screen);

	public abstract void show(Class<? extends Addon> clazz, Screen screen);

	public abstract void show(Class<? extends Addon> clazz, String identifier);

	public void focus(Class<? extends Addon> clazz, String identifier) {
		final Screen found = get(clazz, identifier);
		//No screen found
		if (found == null) {
			return;
		}
		//Trying to focus a screen that already has focus
		if (focused != null && focused.equals(found)) {
			return;
		}
		//A screen already has focus, fire callback for focus lost
		if (focused != null) {
			onFocusChange(FocusResult.LOST, focused);
		}
		//Fire callback for screen that just gained focus
		onFocusChange(FocusResult.GAINED, found);
	}

	public Screen getFocusedScreen() {
		return focused;
	}

	public void onFocusChange(FocusResult result, Screen screen) {

	}
}
