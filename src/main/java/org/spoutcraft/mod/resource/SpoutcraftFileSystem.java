package org.spoutcraft.mod.resource;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cpw.mods.fml.common.FMLCommonHandler;
import org.apache.commons.io.FileUtils;
import org.spoutcraft.api.Spoutcraft;
import org.spoutcraft.api.resource.FileSystem;
import org.spoutcraft.api.resource.ResourceHandler;

public class SpoutcraftFileSystem implements FileSystem {
	public static final Path ASSETS_DIR = Paths.get(System.getProperty("user.dir"), "assets" + File.separator + "spoutcraft");
	public static final Path TEXTURES_DIR = Paths.get(ASSETS_DIR.toString(), "textures");
	public static final Path BLOCK_TEXTURES_DIR = Paths.get(TEXTURES_DIR.toString(), "blocks");
	public static final Path ITEM_TEXTURES_DIR = Paths.get(TEXTURES_DIR.toString(), "items");
	//Special
	public static final Path SPOUTCRAFT_EMBLEM_PNG = Paths.get(ITEM_TEXTURES_DIR.toString(), "spoutcraft_emblem.png");
	private final Map<URI, Object> loadedResources = new HashMap<>();

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
		if (!Files.exists(SPOUTCRAFT_EMBLEM_PNG)) {
			Path parent = Paths.get(new File("").getAbsolutePath()).getParent().getParent().getParent();
			Path child = Paths.get(parent.toString(), "SpoutcraftMod" + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator);
			Spoutcraft.getLogger().info(child.toString());
			Path emblem = Paths.get(child.toString(), "spoutcraft_emblem.png");
			Spoutcraft.getLogger().info(emblem.toString());
			FileUtils.moveFile(emblem.toFile(), new File(ITEM_TEXTURES_DIR.toFile(), "spoutcraft_emblem.png"));
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
