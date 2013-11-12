package org.spoutcraft.api.addon;

import java.net.URL;
import java.net.URLClassLoader;

public class AddonClassLoader extends URLClassLoader {
	private final Addon addon;

	public AddonClassLoader(ClassLoader forge, Addon addon) {
		super(new URL[0], forge);
		this.addon = addon;
	}

	public Addon getAddon() {
		return addon;
	}
}
