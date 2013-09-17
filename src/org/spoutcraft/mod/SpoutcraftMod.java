package org.spoutcraft.mod;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spoutcraft.api.Spoutcraft;
import org.spoutcraft.api.block.Block;
import org.spoutcraft.api.material.MapIndex;
import org.spoutcraft.api.material.Material;
import org.spoutcraft.mod.block.SpoutcraftBlockRegistry;
import org.spoutcraft.mod.material.SpoutcraftMaterialRegistry;
import org.spoutcraft.mod.protocol.ProtocolRegistry;
import org.spoutcraft.mod.protocol.SpoutcraftConnectionHandler;
import org.spoutcraft.mod.protocol.SpoutcraftPacketHandler;
import org.spoutcraft.mod.protocol.codec.HelloCodec;
import org.spoutcraft.mod.protocol.message.HelloMessage;

@Mod (modid = "Spoutcraft", name = "Spoutcraft", version = "1.0.0")
@NetworkMod (clientSideRequired = true, serverSideRequired = true, channels = {"SpoutcraftHello", "SpoutcraftAddRes"}, packetHandler = SpoutcraftPacketHandler.class)
public class SpoutcraftMod {
	@Instance (value = "Spoutcraft")
	public static SpoutcraftMod instance;
	public static CreativeTabs spoutcraftTab = new CreativeTabs("spoutcraftTab") {
		public ItemStack getIconItemStack() {
			return new ItemStack(Item.appleRed, 1, 0);
		}
	};

	@EventHandler
	public void onEnable(FMLPostInitializationEvent event) {
		//Assign registries
		Spoutcraft.setBlockRegistry(new SpoutcraftBlockRegistry());
		Spoutcraft.setMaterialRegistry(new SpoutcraftMaterialRegistry());
		//Let the API know Spoutcraft is enabled
		Spoutcraft.enable();
		//Assign creative tab name
		LanguageRegistry.instance().addStringLocalization("itemGroup.spoutcraftTab", "en_US", "Spoutcraft");
		//Register packets
		ProtocolRegistry.register(HelloMessage.class, HelloCodec.class);
		//Assign connection handler
		NetworkRegistry.instance().registerConnectionHandler(new SpoutcraftConnectionHandler());
		//Test code
		Spoutcraft.getBlockRegistry().put(new Block("block", "TestBlock", new Material("TestMaterial", MapIndex.DIRT)));
	}

	public static CreativeTabs getSpoutcraftTab() {
		return spoutcraftTab;
	}
}
