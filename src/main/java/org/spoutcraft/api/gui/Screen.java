package org.spoutcraft.api.gui;

import java.util.Collection;

import org.spoutcraft.api.gui.widget.Widget;

public abstract class Screen {
	public abstract String getIdentifier();

	public void add(Widget widget) {

	}

	public void addAll(Collection<Widget> widgets) {

	}

	public void remove(Widget widget) {

	}

	public void remove(String identifier) {

	}

	public boolean isTickable() {
		return true;
	}

	public void onTick(float dt) {

	}

	public void onRender() {

	}

	protected final void tick(float dt) {
		if (isTickable()) {
			onTick(dt);
		}
	}
}
