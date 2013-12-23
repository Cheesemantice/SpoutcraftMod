package org.spoutcraft.api.event.item;

import org.spoutcraft.api.event.PrefabEvent;
import org.spoutcraft.api.item.ItemPrefab;

public abstract class ItemPrefabEvent extends PrefabEvent<ItemPrefab> {
    public ItemPrefabEvent(ItemPrefab prefab) {
        super(prefab);
    }
}
