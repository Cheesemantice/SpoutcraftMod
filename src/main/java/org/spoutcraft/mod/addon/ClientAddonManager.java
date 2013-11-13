package org.spoutcraft.mod.addon;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.logging.Level;

import cpw.mods.fml.relauncher.Side;
import org.spoutcraft.api.Spoutcraft;
import org.spoutcraft.api.addon.Addon;
import org.spoutcraft.api.addon.AddonLoader;
import org.spoutcraft.api.addon.AddonManager;
import org.spoutcraft.api.exception.InvalidAddonException;
import org.spoutcraft.api.exception.InvalidPrefabException;

//TODO: Since we can allow Addons to be CLIENT only, those addons can be allowed to loaded on client init. Otherwise, addons would be loaded from the server when connecting to a server
//TODO: Might need some additional thought...
public class ClientAddonManager implements AddonManager {
	private final AddonLoader loader;
	private final Collection<Addon> addons = new ArrayList<>();

	public ClientAddonManager() {
		this.loader = new AddonLoader(Side.CLIENT);
	}

	@Override
	public Addon getAddon(String identifier) {
		if (identifier != null && !identifier.isEmpty()) {
			for (Addon addon : addons) {
				if (addon.getPrefab().getIdentifier().equalsIgnoreCase(identifier)) {
					return addon;
				}
			}
		}
		return null;
	}

	@Override
	public Collection<Addon> getAddons() {
		return Collections.unmodifiableCollection(addons);
	}

	@Override
	public Addon loadAddon(Path path) throws InvalidAddonException, InvalidPrefabException {
		final Addon addon = loader.load(path);
		if (addon != null) {
			addons.add(addon);
		}
		return addon;
	}

	@Override
	public Collection<Addon> loadAddons(Path path) throws IOException {
		if (!Files.isDirectory(path)) {
			throw new IllegalArgumentException("Path " + path + " is not a directory!");
		}

		for (Path jar : Files.newDirectoryStream(path, new DirectoryStream.Filter<Path>() {
			@Override
			public boolean accept(Path entry) {
				return !Files.isDirectory(entry) && entry.endsWith(".jar");
			}
		}))
		{
			Addon addon = null;
			try {
				addon = loadAddon(jar);
			} catch (Exception e) {
				Spoutcraft.getLogger().log(Level.SEVERE, "Unable to load <" + jar.getFileName() + "> in directory <" + path + "> -> " + e.getMessage(), e);
			}
			if (addon != null) {
				addons.add(addon);
			}
		}
		return Collections.unmodifiableCollection(addons);
	}

	@Override
	public void enable(Addon addon) {
		if (!addon.isEnabled()) {
			try {
				addon.getLoader().enable(addon);
			} catch (Exception e) {
				Spoutcraft.getLogger().log(Level.SEVERE, "An error occurred while enabling <" + addon.getPrefab().getIdentifier() + "> -> " + e.getMessage(), e);
			}
		}
	}

	@Override
	public void disable(Addon addon) {
		if (addon.isEnabled()) {
			try {
				addon.getLoader().disable(addon);
			} catch (Exception e) {
				Spoutcraft.getLogger().log(Level.SEVERE, "An error occurred while disabling <" + addon.getPrefab().getIdentifier() + "> -> " + e.getMessage(), e);
			}
		}
	}

	@Override
	public void disable() {
		for (Addon addon : addons) {
			disable(addon);
		}
	}

	@Override
	public void clear() {
		disable();
		addons.clear();
	}
}
