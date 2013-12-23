package org.spoutcraft.api.event.block;

import org.spoutcraft.api.block.BlockPrefab;
import org.spoutcraft.api.event.PrefabEvent;

public abstract class BlockPrefabEvent extends PrefabEvent<BlockPrefab> {
    public BlockPrefabEvent(BlockPrefab prefab) {
        super(prefab);
    }
}
