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
package org.spoutcraft.api.addon;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.logging.Level;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import cpw.mods.fml.relauncher.Side;
import org.spoutcraft.api.Spoutcraft;
import org.spoutcraft.api.exception.InvalidAddonException;
import org.spoutcraft.api.exception.InvalidDescriptionException;

public class AddonLoader {
    private static final String ADDON_JSON = "addon.info";
    private final Side side;
    private final Map<String, AddonClassLoader> loaders = new HashMap<>();

    public AddonLoader(Side side) {
        this.side = side;
    }

    public void enable(Addon addon) {
        if (addon.isEnabled()) {
            throw new IllegalStateException("Cannot enable addon [" + addon.getDescription().getIdentifier() + "], it has already been enabled.");
        }

        try {
            Spoutcraft.getLogger().info("Enabling [" + addon.getDescription().getName() + " " + addon.getDescription().getVersion() + "]...");
            addon.onEnable();
            addon.enable();
            Spoutcraft.getLogger().info("[" + addon.getDescription().getName() + "] enabled");
        } catch (Throwable t) {
            Spoutcraft.getLogger().log(Level.SEVERE, "Exception caught while enabling addon [" + addon.getDescription().getName() + "]", t);
        }

        loaders.put(addon.getDescription().getName(), addon.getClassLoader());
    }

    public void disable(Addon addon) {
        if (!addon.isEnabled()) {
            throw new IllegalStateException("Cannot disable addon [" + addon.getDescription().getName() + "], it has never been enabled.");
        }

        try {
            addon.disable();
            Spoutcraft.getLogger().info("Disabling [" + addon.getDescription().getName() + " " + addon.getDescription().getVersion() + "]...");
            addon.onDisable();
            Spoutcraft.getLogger().info("[" + addon.getDescription().getName() + "] disabled");
        } catch (Throwable t) {
            Spoutcraft.getLogger().log(Level.SEVERE, "Exception caught while disabling addon [" + addon.getDescription().getName() + "]", t);
        }
    }

    public Addon load(Path path) throws InvalidAddonException, InvalidDescriptionException {
        final AddonDescription description = create(path);
        Addon addon = null;
        AddonClassLoader loader;

        if (description.isValidMode(side)) {
            final Path dataPath = Paths.get(path.getParent().toString(), description.getIdentifier());
            try {
                loader = new AddonClassLoader(this.getClass().getClassLoader(), this);
                loader.addURL(path.toUri().toURL());
                Class<?> addonMain = Class.forName(description.getMain(), true, loader);
                Class<? extends Addon> addonClass = addonMain.asSubclass(Addon.class);
                Constructor<? extends Addon> constructor = addonClass.getConstructor();
                addon = constructor.newInstance();
                addon.initialize(side, this, description, loader, dataPath, path);
            } catch (Exception e) {
                throw new InvalidAddonException(e);
            }
            loader.setAddon(addon);
            loaders.put(description.getIdentifier(), loader);
        }
        return addon;
    }

    protected static AddonDescription create(Path path) throws InvalidAddonException, InvalidDescriptionException {
        if (!Files.exists(path)) {
            throw new InvalidAddonException(path.getFileName() + " does not exist!");
        }

        AddonDescription description;
        try(JarFile jar = new JarFile(path.toFile())){
            JarEntry entry = jar.getJarEntry(ADDON_JSON);

            if (entry == null) {
                throw new InvalidDescriptionException("Attempt to create an addon description failed because " + ADDON_JSON + " was not found in " + jar.getName());
            }

            final GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(AddonDescription.class, new AddonDescriptionJsonDeserializer());
            final Gson gson = builder.create();
            description = gson.fromJson(new InputStreamReader(jar.getInputStream(entry)), AddonDescription.class);
        } catch (IOException e) {
            throw new InvalidAddonException(e);
        }
        return description;
    }

    protected Class<?> getClassByName(final String name, final AddonClassLoader commonLoader) {
        for (String current : loaders.keySet()) {
            AddonClassLoader loader = loaders.get(current);
            if (loader == commonLoader) {
                continue;
            }
            try {
                Class<?> clazz = loader.findClass(name, false);
                if (clazz != null) {
                    return clazz;
                }
            } catch (ClassNotFoundException ignored) {
            }
        }
        return null;
    }
}
