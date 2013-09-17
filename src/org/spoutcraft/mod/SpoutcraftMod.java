package org.spoutcraft.mod;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.PacketDispatcher;
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
import org.spoutcraft.mod.protocol.SpoutcraftConnectionHandler;
import org.spoutcraft.mod.protocol.SpoutcraftPacket;
import org.spoutcraft.mod.protocol.SpoutcraftPacketHandler;

@Mod(modid = "Spoutcraft", name = "Spoutcraft", version = "1.0.0")
@NetworkMod(clientSideRequired = true, serverSideRequired = true, channels = {"SpoutcraftHello", "SpoutcraftAddRes"}, packetHandler = SpoutcraftPacketHandler.class)
public class SpoutcraftMod {
	@Instance(value = "Spoutcraft")
	public static SpoutcraftMod instance;

	public static CreativeTabs spoutcraftTab = new CreativeTabs("spoutcraftTab") {
		public ItemStack getIconItemStack() {
			return new ItemStack(Item.appleRed, 1, 0);
		}
	};

	@EventHandler
	public void onEnable(FMLPostInitializationEvent event) {
		Spoutcraft.enable();
		Spoutcraft.setBlockRegistry(new SpoutcraftBlockRegistry());
		Spoutcraft.setMaterialRegistry(new SpoutcraftMaterialRegistry());
		Spoutcraft.getBlockRegistry().put(new Block("block", "TestBlock", new Material("TestMaterial", MapIndex.DIRT)));
		LanguageRegistry.instance().addStringLocalization("itemGroup.spoutcraftTab", "en_US", "Spoutcraft");
        NetworkRegistry.instance().registerConnectionHandler(new SpoutcraftConnectionHandler());
	}

	public static CreativeTabs getSpoutcraftTab() {
		return spoutcraftTab;
	}
}
