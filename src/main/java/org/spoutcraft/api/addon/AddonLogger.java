package org.spoutcraft.api.addon;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.spoutcraft.api.Spoutcraft;

public class AddonLogger extends Logger {
	public AddonLogger(Addon addon) {
		super(addon.getPrefab().getName(), null);
		setLevel(Level.ALL);
		setParent(Spoutcraft.getLogger());
	}
}
