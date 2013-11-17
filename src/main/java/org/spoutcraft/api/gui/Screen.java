package org.spoutcraft.api.gui;

import java.util.Collection;
import java.util.LinkedList;

import org.spoutcraft.api.addon.Addon;
import org.spoutcraft.api.gui.widget.Widget;

public abstract class Screen<T extends Addon> {
	private final Class<T> addonClass;
	private final LinkedList<Widget> widgets = new LinkedList<>();

	public Screen(Class<T> addonClass) {
		this.addonClass = addonClass;
	}

	public Class<T> getAddon() {
		return addonClass;
	}

	public void add(Widget widget) {
		widgets.add(widget);
	}

	public void addAll(Collection<Widget> widgets) {
		widgets.addAll(widgets);
	}

	public void onTick(float dt) {

	}

	public void onRender() {

	}
}
