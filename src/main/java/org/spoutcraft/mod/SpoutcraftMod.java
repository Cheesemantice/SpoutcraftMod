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
package org.spoutcraft.mod;

import java.util.EnumSet;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.integrated.IntegratedServer;
import org.spoutcraft.api.LinkedPrefabRegistry;
import org.spoutcraft.api.Spoutcraft;
import org.spoutcraft.api.block.MovingPrefab;
import org.spoutcraft.api.material.MapIndex;
import org.spoutcraft.api.material.MaterialPrefab;
import org.spoutcraft.api.protocol.Protocol;
import org.spoutcraft.api.util.LanguageUtil;
import org.spoutcraft.mod.addon.ClientAddonManager;
import org.spoutcraft.mod.addon.ServerAddonManager;
import org.spoutcraft.mod.block.SpoutcraftBlockPrefabRegistry;
import org.spoutcraft.mod.gui.SpoutcraftMainMenu;
import org.spoutcraft.mod.item.SpoutcraftItemPrefabRegistry;
import org.spoutcraft.mod.item.special.SpoutcraftEmblem;
import org.spoutcraft.mod.item.special.VanillaEmblem;
import org.spoutcraft.mod.logger.SpoutcraftLogger;
import org.spoutcraft.mod.material.SpoutcraftMaterialPrefabRegistry;
import org.spoutcraft.mod.protocol.SpoutcraftConnectionHandler;
import org.spoutcraft.mod.protocol.SpoutcraftPacketHandler;
import org.spoutcraft.mod.protocol.message.AddPrefabMessage;
import org.spoutcraft.test.block.TestSand;
import org.spoutcraft.test.item.TestFood;
import org.spoutcraft.test.item.TestItem;

import org.lwjgl.opengl.Display;

@Mod (modid = "Spoutcraft")
@NetworkMod (clientSideRequired = true, serverSideRequired = true, channels = {"SPC-AddResource", "SPC-AddPrefab", "SPC-AddonList"}, packetHandler = SpoutcraftPacketHandler.class)
public class SpoutcraftMod {
	@Instance (value = "Spoutcraft")
	public static SpoutcraftMod instance;
	private static CustomTabs customTabs;

	@EventHandler
	public void onClientStarting(FMLInitializationEvent event) {
		Display.setTitle("Spoutcraft");

		// Setup logger
		Spoutcraft.setLogger(new SpoutcraftLogger());
		Spoutcraft.getLogger().init();

		// Prepare protocol
		bindCodecMessages();

		// TODO Load client addons
		Spoutcraft.setAddonManager(new ClientAddonManager());

		// Setup registries
		Spoutcraft.setBlockRegistry(new SpoutcraftBlockPrefabRegistry());
		Spoutcraft.setItemPrefabRegistry(new SpoutcraftItemPrefabRegistry());
		Spoutcraft.setMaterialRegistry(new SpoutcraftMaterialPrefabRegistry());

		// Setup creative tab
		customTabs = new CustomTabs();

		//registerHandlers();

		Spoutcraft.getItemPrefabRegistry().put(new SpoutcraftEmblem());
		Spoutcraft.getItemPrefabRegistry().put(new VanillaEmblem());

		final LinkedPrefabRegistry registry = Spoutcraft.getBlockPrefabRegistry();
		registry.put(new MovingPrefab("0b", "0 (Black)", new MaterialPrefab("testmaterial", MapIndex.DIRT), 0.5f, true));
		registry.put(new MovingPrefab("0w", "0 (White)", new MaterialPrefab("testmaterial", MapIndex.DIRT), 0.5f, true));
		registry.put(new MovingPrefab("1b", "1 (Black)", new MaterialPrefab("testmaterial", MapIndex.DIRT), 0.5f, true));
		registry.put(new MovingPrefab("1w", "1 (White)", new MaterialPrefab("testmaterial", MapIndex.DIRT), 0.5f, true));
		registry.put(new MovingPrefab("2b", "2 (Black)", new MaterialPrefab("testmaterial", MapIndex.DIRT), 0.5f, true));
		registry.put(new MovingPrefab("2w", "2 (White)", new MaterialPrefab("testmaterial", MapIndex.DIRT), 0.5f, true));
		registry.put(new MovingPrefab("3b", "3 (Black)", new MaterialPrefab("testmaterial", MapIndex.DIRT), 0.5f, true));
		registry.put(new MovingPrefab("3w", "3 (White)", new MaterialPrefab("testmaterial", MapIndex.DIRT), 0.5f, true));
		registry.put(new MovingPrefab("4b", "4 (Black)", new MaterialPrefab("testmaterial", MapIndex.DIRT), 0.5f, true));
		registry.put(new MovingPrefab("4w", "4 (White)", new MaterialPrefab("testmaterial", MapIndex.DIRT), 0.5f, true));
		registry.put(new MovingPrefab("5b", "5 (Black)", new MaterialPrefab("testmaterial", MapIndex.DIRT), 0.5f, true));
		registry.put(new MovingPrefab("5w", "5 (White)", new MaterialPrefab("testmaterial", MapIndex.DIRT), 0.5f, true));
		registry.put(new MovingPrefab("6b", "6 (Black)", new MaterialPrefab("testmaterial", MapIndex.DIRT), 0.5f, true));
		registry.put(new MovingPrefab("6w", "6 (White)", new MaterialPrefab("testmaterial", MapIndex.DIRT), 0.5f, true));
		registry.put(new MovingPrefab("7b", "7 (Black)", new MaterialPrefab("testmaterial", MapIndex.DIRT), 0.5f, true));
		registry.put(new MovingPrefab("7w", "7 (White)", new MaterialPrefab("testmaterial", MapIndex.DIRT), 0.5f, true));
		registry.put(new MovingPrefab("8b", "8 (Black)", new MaterialPrefab("testmaterial", MapIndex.DIRT), 0.5f, true));
		registry.put(new MovingPrefab("8w", "8 (White)", new MaterialPrefab("testmaterial", MapIndex.DIRT), 0.5f, true));
		registry.put(new MovingPrefab("9b", "9 (Black)", new MaterialPrefab("testmaterial", MapIndex.DIRT), 0.5f, true));
		registry.put(new MovingPrefab("9w", "9 (White)", new MaterialPrefab("testmaterial", MapIndex.DIRT), 0.5f, true));
	}

	@EventHandler
	@SuppressWarnings ("unchecked")
	public void onServerStarting(FMLServerStartingEvent event) {
		if (!(event.getServer() instanceof IntegratedServer)) {
			// Setup registries
			Spoutcraft.setBlockRegistry(new SpoutcraftBlockPrefabRegistry());
			Spoutcraft.setItemPrefabRegistry(new SpoutcraftItemPrefabRegistry());
			Spoutcraft.setMaterialRegistry(new SpoutcraftMaterialPrefabRegistry());

			Spoutcraft.setLogger(new SpoutcraftLogger());
			Spoutcraft.getLogger().init();

			// Prepare protocol
			bindCodecMessages();

			// Set addon manager
			// TODO Load server addons
			Spoutcraft.setAddonManager(new ServerAddonManager());
			// TODO Reflect GameRegistry and remove Spoutcraft blocks/items from clients when they disconnect...

			// Test code
			// TODO Look into fixing generics so suppression isn't needed
			Spoutcraft.getItemPrefabRegistry().put(new TestItem());
			Spoutcraft.getItemPrefabRegistry().put(new TestFood());
			Spoutcraft.getBlockPrefabRegistry().put(new TestSand());
		}
	}

	private void registerHandlers() {
		// Show our main menu
		TickRegistry.registerTickHandler(new ITickHandler() {
			@Override
			public void tickStart(EnumSet<TickType> type, Object... tickData) {
				if (type.equals(EnumSet.of(TickType.CLIENT))) {
					final GuiScreen current = Minecraft.getMinecraft().currentScreen;
					if (current != null && current.getClass() == GuiMainMenu.class && current.getClass() != SpoutcraftMainMenu.class) {
						FMLClientHandler.instance().getClient().displayGuiScreen(new SpoutcraftMainMenu());
					}
				}
			}

			@Override
			public void tickEnd(EnumSet<TickType> type, Object... tickData) {
			}

			@Override
			public EnumSet<TickType> ticks() {
				return EnumSet.of(TickType.CLIENT);
			}

			@Override
			public String getLabel() {
				return "Spoutcraft - Main Menu Hotswap";
			}
		}, Side.CLIENT);
	}

	private void bindCodecMessages() {
		NetworkRegistry.instance().registerConnectionHandler(new SpoutcraftConnectionHandler());
		Protocol.register(AddPrefabMessage.class, org.spoutcraft.mod.protocol.codec.AddPrefabCodec.class);
	}

	private class CustomTabs extends CreativeTabs {
		public CustomTabs() {
			super("Spoutcraft");
			LanguageUtil.add("itemGroup.Spoutcraft", "Spoutcraft");
		}

		@Override
		public ItemStack getIconItemStack() {
			return new ItemStack((Item) Spoutcraft.getItemPrefabRegistry().find("spoutcraft_emblem"), 1, 0);
		}
	}

	public static CustomTabs getCustomTabs() {
		return customTabs;
	}
}
