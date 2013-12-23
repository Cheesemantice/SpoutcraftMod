package org.spoutcraft.api.event.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spoutcraft.api.item.ItemPrefab;

public class ItemCreatedEvent extends ItemCreateEvent {
    public ItemCreatedEvent(ItemPrefab prefab, ItemStack stack, World world, EntityPlayer player) {
        super(prefab, stack, world, player);
    }
}
