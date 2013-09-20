package org.spoutcraft.mod;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spoutcraft.api.Spoutcraft;
import org.spoutcraft.mod.block.SpoutcraftBlockRegistry;
import org.spoutcraft.mod.logger.SpoutcraftLogger;
import org.spoutcraft.mod.material.SpoutcraftMaterialRegistry;
import org.spoutcraft.mod.protocol.ProtocolRegistry;
import org.spoutcraft.mod.protocol.SpoutcraftConnectionHandler;
import org.spoutcraft.mod.protocol.SpoutcraftPacketHandler;
import org.spoutcraft.mod.protocol.codec.AddBlockCodec;
import org.spoutcraft.mod.protocol.codec.HelloCodec;
import org.spoutcraft.mod.protocol.message.AddBlockMessage;
import org.spoutcraft.mod.protocol.message.HelloMessage;

@Mod (modid = "Spoutcraft")
@NetworkMod (clientSideRequired = true, serverSideRequired = true, channels = {"SpoutcraftHello", "SpoutcraftAddRes", "SpoutcraftAddBlk"}, packetHandler = SpoutcraftPacketHandler.class)
public class SpoutcraftMod {
	@Instance (value = "Spoutcraft")
	public static SpoutcraftMod instance;
	public static CreativeTabs spoutcraftTab = new CreativeTabs("spoutcraftTab") {
		public ItemStack getIconItemStack() {
			return new ItemStack(Item.appleRed, 1, 0);
		}
	};

	@EventHandler
	public void onLoad(FMLPreInitializationEvent event) {
		Spoutcraft.setLogger(new SpoutcraftLogger());
		Spoutcraft.getLogger().init();
	}

	@EventHandler
	public void onEnable(FMLPostInitializationEvent event) {
		//Assign creative tab name
		LanguageRegistry.instance().addStringLocalization("itemGroup.spoutcraftTab", "en_US", "Spoutcraft");

		//Register messages
		ProtocolRegistry.register(HelloMessage.class, HelloCodec.class);
		ProtocolRegistry.register(AddBlockMessage.class, AddBlockCodec.class);

		//Assign registries
		Spoutcraft.setBlockRegistry(new SpoutcraftBlockRegistry());
		Spoutcraft.setMaterialRegistry(new SpoutcraftMaterialRegistry());

		//Let the API know Spoutcraft is enabled
		Spoutcraft.enable();

		//Setup connection handler
		NetworkRegistry.instance().registerConnectionHandler(new SpoutcraftConnectionHandler());

		if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
			Spoutcraft.getBlockRegistry().put(new Block("testblock", "TestBlock", new Material("TestMaterial", MapIndex.DIRT)));
		}
	}

	public static CreativeTabs getSpoutcraftTab() {
		return spoutcraftTab;
	}
}
