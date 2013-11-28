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
package org.spoutcraft.mod.resource;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.spoutcraft.api.addon.Addon;
import org.spoutcraft.api.util.RenderUtil;

public class ClientFileSystem extends ServerFileSystem {
    public static final Path ASSETS_DIR = Paths.get(BASE_DIR.toString(), "assets" + File.separator + "spoutcraft");
    public static final Path TEXTURES_DIR = Paths.get(ASSETS_DIR.toString(), "textures");
    public static final Path BLOCK_TEXTURES_DIR = Paths.get(TEXTURES_DIR.toString(), "blocks");
    public static final Path ITEM_TEXTURES_DIR = Paths.get(TEXTURES_DIR.toString(), "items");
    //Addon -> URI/Resource
    private static final Map<Class<? extends Addon>, Map<URI, Object>> ADDON_RESOURCES = new HashMap<>();

    @Override
    public void init() throws IOException {
        super.init();

        if (!Files.exists(BLOCK_TEXTURES_DIR)) {
            Files.createDirectories(BLOCK_TEXTURES_DIR);
        }
        if (!Files.exists(ITEM_TEXTURES_DIR)) {
            Files.createDirectories(ITEM_TEXTURES_DIR);
        }
    }

    @Override
    public void send(Class<? extends Addon> clazz, Path path) {
        if (!RenderUtil.MINECRAFT.isIntegratedServerRunning()) {
            throw new IllegalStateException("Client cannot send resources to the server!");
        }
        //TODO Handle SinglePlayer sending
    }

    @Override
    public void send(Class<? extends Addon> clazz, String uri) {
        if (!RenderUtil.MINECRAFT.isIntegratedServerRunning()) {
            throw new IllegalStateException("Client cannot send resources to the server!");
        }
        //TODO Handle SinglePlayer sending
    }

    @Override
    public <R> R get(Class<? extends Addon> clazz, String name) {
        return null;
    }

    @Override
    public <R> Collection<R> getAllFor(Class<? extends Addon> clazz) {
        return null;
    }

    @Override
    public <R> Map<Class<? extends Addon>, R> getAll() {
        return null;
    }
}
