package org.spoutcraft.mod.gui;

import net.minecraft.client.gui.GuiScreen;
import org.spoutcraft.api.gui.Screen;

public class CustomScreen extends GuiScreen {
	private final Screen screen;

	public CustomScreen(Screen screen) {
		this.screen = screen;
	}
}
