package org.spoutcraft.mod;

import java.io.IOException;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import org.spoutcraft.api.Spoutcraft;
import org.spoutcraft.mod.block.SpoutcraftBlockPrefabRegistry;
import org.spoutcraft.mod.game.CustomTabs;
import org.spoutcraft.mod.item.SpoutcraftItemPrefabRegistry;
import org.spoutcraft.mod.item.special.SpoutcraftEmblem;
import org.spoutcraft.mod.item.special.VanillaEmblem;
import org.spoutcraft.mod.logger.SpoutcraftLogger;
import org.spoutcraft.mod.material.SpoutcraftMaterialPrefabRegistry;
import org.spoutcraft.mod.protocol.SpoutcraftPacketHandler;
import org.spoutcraft.mod.protocol.SpoutcraftProtocol;
import org.spoutcraft.mod.resource.SpoutcraftFileSystem;
import org.spoutcraft.test.block.TestSand;
import org.spoutcraft.test.item.TestFood;
import org.spoutcraft.test.item.TestItem;

@Mod (modid = "Spoutcraft")
@NetworkMod (clientSideRequired = true, serverSideRequired = true, channels = {"SPC-AddResource", "SPC-AddPrefab", "SPC-AddonList"}, packetHandler = SpoutcraftPacketHandler.class)
public class SpoutcraftMod {
	@Instance (value = "Spoutcraft")
	public static SpoutcraftMod instance;
	private static CustomTabs customTabs;

	@EventHandler
	@SuppressWarnings ("unchecked")
	public void onLoad(FMLPostInitializationEvent event) {
		//Setup logger
		Spoutcraft.setLogger(new SpoutcraftLogger());
		Spoutcraft.getLogger().init();

		//Setup registries
		Spoutcraft.setFileSystem(new SpoutcraftFileSystem());
		Spoutcraft.setBlockRegistry(new SpoutcraftBlockPrefabRegistry());
		Spoutcraft.setItemPrefabRegistry(new SpoutcraftItemPrefabRegistry());
		Spoutcraft.setMaterialRegistry(new SpoutcraftMaterialPrefabRegistry());

		//Init protocol
		SpoutcraftProtocol.init();

		//Init file system
		try {
			((SpoutcraftFileSystem) Spoutcraft.getFileSystem()).init();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		//Setup creative tab
		customTabs = new CustomTabs();

		//Special
		Spoutcraft.getItemPrefabRegistry().create(new SpoutcraftEmblem());
		Spoutcraft.getItemPrefabRegistry().create(new VanillaEmblem());

		//Test code
		//TODO Look into fixing generics so suppression isn't needed
		Spoutcraft.getItemPrefabRegistry().put(new TestItem());
		Spoutcraft.getItemPrefabRegistry().put(new TestFood());
		Spoutcraft.getBlockPrefabRegistry().put(new TestSand());
	}

	public static CustomTabs getCustomTabs() {
		return customTabs;
	}
}
