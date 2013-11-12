/**
 * This file is a part of Spoutcraft
 *
 * Copyright (c) 2013 SpoutcraftDev
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
import java.util.List;
import java.util.Set;

import cpw.mods.fml.common.FMLCommonHandler;
import org.spoutcraft.api.resource.FileSystem;
import org.spoutcraft.api.resource.ResourceHandler;

public class SpoutcraftFileSystem implements FileSystem {
	public static final Path ASSETS_DIR = Paths.get(System.getProperty("user.dir"), "assets" + File.separator + "spoutcraft");
	public static final Path TEXTURES_DIR = Paths.get(ASSETS_DIR.toString(), "textures");
	public static final Path BLOCK_TEXTURES_DIR = Paths.get(TEXTURES_DIR.toString(), "blocks");
	public static final Path ITEM_TEXTURES_DIR = Paths.get(TEXTURES_DIR.toString(), "items");

	public void init() throws IOException {
		if (!Files.exists(TEXTURES_DIR)) {
			Files.createDirectories(TEXTURES_DIR);
		}
		if (!Files.exists(BLOCK_TEXTURES_DIR)) {
			Files.createDirectories(BLOCK_TEXTURES_DIR);
		}
		if (!Files.exists(ITEM_TEXTURES_DIR)) {
			Files.createDirectories(ITEM_TEXTURES_DIR);
		}
	}

	@Override
	public <R> R get(Path path) {
		return null;
	}

	@Override
	public <R> R get(String uri) {
		return null;
	}

	@Override
	public <R> R get(URI uri) {
		return null;
	}

	@Override
	public <R> List<R> getResources(Path path) {
		return null;
	}

	@Override
	public <R> List<R> getResources(String uri) {
		return null;
	}

	@Override
	public <R> List<R> getResources(URI uri) {
		return null;
	}

	@Override
	public ResourceHandler getHandler(String token) {
		return null;
	}

	@Override
	public Set<ResourceHandler> getHandlers() {
		return null;
	}

	@Override
	public void register(ResourceHandler loader) {

	}

	@Override
	public <R> void send(R resource) {
		if (FMLCommonHandler.instance().getEffectiveSide().isClient()) {
			throw new IllegalStateException("The client may not send resources to the server!");
		}
	}
}
