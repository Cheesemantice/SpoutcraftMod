package org.spoutcraft.api.event.item;

import com.flowpowered.events.Cancellable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spoutcraft.api.item.ItemPrefab;

public class ItemCreateEvent extends ItemPrefabEvent implements Cancellable {
    private final ItemStack stack;
    private final World world;
    private final EntityPlayer player;

    public ItemCreateEvent(ItemPrefab prefab, ItemStack stack, World world, EntityPlayer player) {
        super(prefab);
        this.stack = stack;
        this.world = world;
        this.player = player;
    }

    public EntityPlayer getPlayer() {
        return player;
    }

    public World getWorld() {
        return world;
    }

    public ItemStack getStack() {
        return stack;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        super.setCancelled(cancelled);
    }
}
