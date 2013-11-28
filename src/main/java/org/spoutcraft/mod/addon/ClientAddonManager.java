/**
 * This file is a part of Spoutcraft.
 *
 * Copyright (c) 2013 SpoutcraftDev <http://spoutcraft.org>
 * Spoutcraft is licensed under the MIT License.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
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
import org.spoutcraft.api.addon.SpoutcraftAddon;
import org.spoutcraft.api.exception.InvalidAddonException;
import org.spoutcraft.api.exception.InvalidPrefabException;

public class ClientAddonManager implements AddonManager {
    private final AddonLoader loader;
    private final Collection<Addon> addons = new ArrayList<>();
    private final SpoutcraftAddon spoutcraftAddon = new SpoutcraftAddon(Side.CLIENT);

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

        addons.add(spoutcraftAddon);
        for (Path jar : Files.newDirectoryStream(path, new DirectoryStream.Filter<Path>() {
            @Override
            public boolean accept(Path entry) {
                return !Files.isDirectory(entry) && entry.endsWith(".jar");
            }
        })) {
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
    public void enable() {
        for (Addon addon : addons) {
            enable(addon);
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
