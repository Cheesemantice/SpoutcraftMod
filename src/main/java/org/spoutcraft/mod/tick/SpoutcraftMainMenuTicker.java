package org.spoutcraft.mod.tick;

import java.util.EnumSet;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import org.spoutcraft.mod.gui.SpoutcraftMainMenu;

public class SpoutcraftMainMenuTicker implements ITickHandler {
	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		if (type.equals(EnumSet.of(TickType.CLIENT))) {
			final GuiScreen current = Minecraft.getMinecraft().currentScreen;
			if (current instanceof GuiMainMenu && current.getClass() != SpoutcraftMainMenu.class) {
				Minecraft.getMinecraft().displayGuiScreen(new SpoutcraftMainMenu());
			}
		}
	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.CLIENT);
	}

	@Override
	public String getLabel() {
		return null;
	}
}
