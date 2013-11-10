package org.spoutcraft.mod;

import java.io.IOException;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import net.minecraft.block.material.MapColor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatMessageComponent;
import net.minecraft.world.World;
import org.spoutcraft.api.Spoutcraft;
import org.spoutcraft.api.block.BlockPrefab;
import org.spoutcraft.api.block.BlockType;
import org.spoutcraft.api.item.FoodPrefab;
import org.spoutcraft.api.item.ItemPrefab;
import org.spoutcraft.api.material.MapIndex;
import org.spoutcraft.api.material.MaterialPrefab;
import org.spoutcraft.mod.block.SpoutcraftBlockPrefabRegistry;
import org.spoutcraft.mod.game.CustomTabs;
import org.spoutcraft.mod.item.SpoutcraftItemPrefabRegistry;
import org.spoutcraft.mod.logger.SpoutcraftLogger;
import org.spoutcraft.mod.material.SpoutcraftMaterialPrefabRegistry;
import org.spoutcraft.mod.protocol.SpoutcraftPacketHandler;
import org.spoutcraft.mod.protocol.SpoutcraftProtocol;
import org.spoutcraft.mod.resource.SpoutcraftFileSystem;

@Mod (modid = "Spoutcraft")
@NetworkMod (clientSideRequired = true, serverSideRequired = true, channels = {"SPC-AddResource", "SPC-UpdatePrefab"}, packetHandler = SpoutcraftPacketHandler.class)
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
			throw new IllegalStateException("Failure to initialize file system");
		}

		//Setup creative tab
		customTabs = new CustomTabs();

		//Test code
		//TODO Look into fixing generics so suppression isn't needed
		final MaterialPrefab testMaterial = new MaterialPrefab("TestMaterial", MapIndex.DIRT);
		Spoutcraft.getBlockPrefabRegistry().put(new BlockPrefab("testblock", "TestBlock", testMaterial, 0.5F));
		Spoutcraft.getItemPrefabRegistry().put(new ItemPrefab("testitem", "TestItem", 128));
		Spoutcraft.getItemPrefabRegistry().put(new FoodPrefab("testfood", "TestFood", 10, 1, 0, false) {
			@Override
			public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player) {
				player.addChatMessage("Thanks for eating a test item!");
				return stack;
			}
		});
		Spoutcraft.getBlockPrefabRegistry().put(new BlockPrefab("testsand", BlockType.SAND, "TestSand", testMaterial, 0.5F));
	}

	public static CustomTabs getCustomTabs() {
		return customTabs;
	}
}
