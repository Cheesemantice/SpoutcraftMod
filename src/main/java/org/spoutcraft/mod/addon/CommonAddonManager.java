/**
 * This file is part of SpoutcraftMod, licensed under the MIT License (MIT).
 *
 * Copyright (c) 2013 SpoutcraftDev <http://spoutcraft.org/>
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

import org.spoutcraft.api.Spoutcraft;
import org.spoutcraft.api.addon.Addon;
import org.spoutcraft.api.addon.AddonLoader;
import org.spoutcraft.api.addon.AddonManager;
import org.spoutcraft.api.addon.InternalAddon;
import org.spoutcraft.api.addon.InvalidAddonException;
import org.spoutcraft.api.addon.InvalidDescriptionException;

public class CommonAddonManager implements AddonManager {
    protected final Spoutcraft game;
    protected final AddonLoader loader;
    protected final Addon internal;
    protected final Collection<Addon> addons = new ArrayList<>();

    public CommonAddonManager(Spoutcraft game) {
        this.game = game;
        this.loader = new AddonLoader(game);
        this.internal = new InternalAddon(game);
        addons.add(internal);
    }

    @Override
    public Addon getAddon(String identifier) {
        if (isOfficialAddon(identifier)) {
            throw new IllegalStateException("Official internal addons are restricted to the mod, a developer is doing something bad!");
        }
        if (identifier != null && !identifier.isEmpty()) {
            for (Addon addon : addons) {
                if (addon.getDescription().getIdentifier().equalsIgnoreCase(identifier)) {
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
    public Addon loadAddon(Path path) throws InvalidAddonException, InvalidDescriptionException {
        final Addon addon = loader.load(path);
        addons.add(addon);
        return addon;
    }

    @Override
    public Collection<Addon> loadAddons(Path path) {
        if (!Files.isDirectory(path)) {
            throw new IllegalArgumentException("Path " + path + " is not a directory!");
        }

        final DirectoryStream<Path> stream;
        try {
            stream = Files.newDirectoryStream(path, new DirectoryStream.Filter<Path>() {
                @Override
                public boolean accept(Path entry) {
                    String fname = entry.getFileName().toString();
                    return !Files.isDirectory(entry) && fname.endsWith(".jar");
                }
            });
        } catch (IOException e) {
            throw new RuntimeException("IO error occurred while traversing addons dir", e);
        }

        for (Path jar : stream) {
            try {
                loadAddon(jar);
            } catch (Exception e) {
                game.getLogger().log(Level.SEVERE, "Unable to load [" + jar.getFileName() + "] in directory [" + path + "]", e);
            }
        }
        return Collections.unmodifiableCollection(addons);
    }

    @Override
    public void enable(Addon addon) {
        if (!addon.isEnabled()) {
            addon.getLoader().enable(addon);
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
            addon.getLoader().disable(addon);
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
        addons.add(internal);
    }

    public Addon getInternalAddon() {
        return internal;
    }

    public AddonLoader getLoader() {
        return loader;
    }

    private boolean isOfficialAddon(String identifier) {
        return "internal".equalsIgnoreCase(identifier);
    }
}
