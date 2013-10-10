package org.spoutcraft.mod;

import java.io.IOException;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.RenderZombie;
import net.minecraft.entity.EntityEggInfo;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.src.ModLoader;
import net.minecraft.world.biome.BiomeGenBase;
import org.spoutcraft.api.Spoutcraft;
import org.spoutcraft.api.block.Block;
import org.spoutcraft.api.material.MapIndex;
import org.spoutcraft.api.material.Material;
import org.spoutcraft.mod.block.SpoutcraftBlockRegistry;
import org.spoutcraft.mod.entity.SpoutcraftEntity;
import org.spoutcraft.mod.entity.SpoutcraftEntityRendererAdapter;
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
		EntityRegistry.registerModEntity(SpoutcraftEntity.class, "SpoutcraftZombie", 2000, this, 5, 5, true);
		RenderingRegistry.registerEntityRenderingHandler(SpoutcraftEntity.class, new RenderZombie());
		LanguageRegistry.instance().addStringLocalization("entity.Spoutcraft.SpoutcraftZombie.name", "en_US", "Spoutcraft Zombie");
		EntityList.IDtoClassMapping.put(2000, SpoutcraftEntity.class);
		EntityList.entityEggs.put(2000, new EntityEggInfo(2000,  0x2e5c00, 0x2e5c00));
	}

	public static SpoutcraftTab getSpoutcraftTab() {
		return spoutcraftTab;
	}
}
