/**
 * This file is a part of Spoutcraft
 *
 * Copyright (c) 2013 SpoutcraftDev <http://spoutcraft.org>
 * Spoutcraft is licensed under the MIT License
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
package org.spoutcraft.mod.material;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import net.minecraft.block.material.Material;
import net.minecraft.network.INetworkManager;
import org.spoutcraft.api.LinkedPrefabRegistry;
import org.spoutcraft.api.Spoutcraft;
import org.spoutcraft.api.material.MaterialPrefab;
import org.spoutcraft.mod.protocol.SpoutcraftPacket;
import org.spoutcraft.mod.protocol.message.AddPrefabMessage;

public class SpoutcraftMaterialPrefabRegistry implements LinkedPrefabRegistry<MaterialPrefab, Material> {
	private static final ArrayList<MaterialPrefab> REGISTRY = new ArrayList<>();
	//INTERNAL
	private static final HashMap<MaterialPrefab, Material> PREFAB_BY_MATERIAL = new HashMap<>();

	@Override
	public MaterialPrefab put(MaterialPrefab prefab) {
		create(prefab);
		return prefab;
	}

	@Override
	public Material create(MaterialPrefab prefab) {
		if (prefab == null) {
			throw new IllegalStateException("Attempt made to put null material prefab into registry!");
		}

		final Material material = new CustomMaterial(prefab);
		REGISTRY.add(prefab);
		PREFAB_BY_MATERIAL.put(prefab, material);

		//TODO Materials need to be registered?
		if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
			PacketDispatcher.sendPacketToAllPlayers(new SpoutcraftPacket(new AddPrefabMessage(prefab)));
		}
		return material;
	}

	@Override
	public MaterialPrefab get(String identifier) {
		for (MaterialPrefab prefab : REGISTRY) {
			if (prefab.getIdentifier().equals(identifier)) {
				return prefab;
			}
		}
		return null;
	}

	@Override
	public Material find(MaterialPrefab prefab) {
		return prefab == null ? null : PREFAB_BY_MATERIAL.get(prefab);
	}

	@Override
	public Material find(String identifier) {
		if (identifier != null && !identifier.isEmpty()) {
			for (Map.Entry<MaterialPrefab, Material> entry : PREFAB_BY_MATERIAL.entrySet()) {
				if (entry.getKey().getIdentifier().equals(identifier)) {
					return entry.getValue();
				}
			}
		}
		return null;
	}

	/**
	 * Syncs the entire block registry to the client
	 *
	 * @param network The connected network
	 */
	public void sync(final INetworkManager network) {
		Spoutcraft.getLogger().info("Preparing to sync material registry");
		//TODO Scheduler and sending
		for (MaterialPrefab prefab : REGISTRY) {
			Spoutcraft.getLogger().info("Syncing material prefab to client");
			Spoutcraft.getLogger().info(prefab.toString());
			network.addToSendQueue(new SpoutcraftPacket(new AddPrefabMessage(prefab)));
		}
	}
}