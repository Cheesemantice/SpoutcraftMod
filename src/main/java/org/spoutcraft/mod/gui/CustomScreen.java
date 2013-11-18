package org.spoutcraft.mod.gui;

import java.util.Collection;
import java.util.LinkedList;

import net.minecraft.client.gui.GuiScreen;
import org.spoutcraft.api.gui.Screen;
import org.spoutcraft.api.gui.widget.Widget;

public class CustomScreen extends GuiScreen implements Screen {
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
