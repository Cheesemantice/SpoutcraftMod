package org.spoutcraft.mod;

import java.io.IOException;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

import org.spoutcraft.api.Spoutcraft;
import org.spoutcraft.api.block.Block;
import org.spoutcraft.api.material.MapIndex;
import org.spoutcraft.api.material.Material;
import org.spoutcraft.mod.block.SpoutcraftBlockRegistry;
import org.spoutcraft.mod.game.SpoutcraftTab;
import org.spoutcraft.mod.logger.SpoutcraftLogger;
import org.spoutcraft.mod.material.SpoutcraftMaterialRegistry;
import org.spoutcraft.mod.protocol.SpoutcraftPacketHandler;
import org.spoutcraft.mod.protocol.SpoutcraftProtocol;
import org.spoutcraft.mod.resource.SpoutcraftFileSystem;

@Mod(modid = "Spoutcraft")
@NetworkMod(clientSideRequired = true, serverSideRequired = true, channels = {"SPC-AddResource", "SPC-UpdateBlock"}, packetHandler = SpoutcraftPacketHandler.class)
public class SpoutcraftMod {
	@Instance(value = "Spoutcraft")
	public static SpoutcraftMod instance;
	private static SpoutcraftTab spoutcraftTab;

	@EventHandler
	public void onLoad(FMLPostInitializationEvent event) {
		//Let the API know Spoutcraft is enabled
		Spoutcraft.enable();

		//Setup logger
		Spoutcraft.setLogger(new SpoutcraftLogger());
		Spoutcraft.getLogger().init();

		//Setup registries
		Spoutcraft.setBlockRegistry(new SpoutcraftBlockRegistry());
		Spoutcraft.setFileSystem(new SpoutcraftFileSystem());
		Spoutcraft.setMaterialRegistry(new SpoutcraftMaterialRegistry());

		//Init protocol
		SpoutcraftProtocol.init();

		//Init file system
		try {
			((SpoutcraftFileSystem) Spoutcraft.getFileSystem()).init();
		} catch (IOException ioe) {
			throw new IllegalStateException("Failure to initialize file system");
		}

		//Setup creative tab
		spoutcraftTab = new SpoutcraftTab();

		//Test code
		Spoutcraft.getBlockRegistry().put(new Block("testblock", "TestBlock", new Material("TestMaterial", MapIndex.DIRT)));
	}

	public static SpoutcraftTab getSpoutcraftTab() {
		return spoutcraftTab;
	}
}
