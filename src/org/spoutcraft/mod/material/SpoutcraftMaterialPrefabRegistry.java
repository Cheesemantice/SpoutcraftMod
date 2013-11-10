package org.spoutcraft.mod.material;

import java.util.ArrayList;
import java.util.HashMap;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import net.minecraft.block.material.Material;
import net.minecraft.network.INetworkManager;
import org.spoutcraft.api.PrefabRegistry;
import org.spoutcraft.api.Spoutcraft;
import org.spoutcraft.api.material.MaterialPrefab;
import org.spoutcraft.mod.protocol.SpoutcraftPacket;
import org.spoutcraft.mod.protocol.message.UpdatePrefabMessage;

public class SpoutcraftMaterialPrefabRegistry implements PrefabRegistry<MaterialPrefab> {
	private static final ArrayList<MaterialPrefab> REGISTRY = new ArrayList<>();
	//INTERNAL
	private static final HashMap<MaterialPrefab, Material> PREFAB_BY_MATERIAL = new HashMap<>();

	@Override
	public MaterialPrefab put(MaterialPrefab prefab) {
		final Material material;
		switch (prefab.getType()) {
			default:
				material = new CustomMaterial(prefab);
		}
		REGISTRY.add(prefab);
		PREFAB_BY_MATERIAL.put(prefab, material);
		//TODO Materials need to be registered?
		if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
			PacketDispatcher.sendPacketToAllPlayers(new SpoutcraftPacket(new UpdatePrefabMessage(prefab)));
		}
		return prefab;
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
			network.addToSendQueue(new SpoutcraftPacket(new UpdatePrefabMessage(prefab)));
		}
	}
}