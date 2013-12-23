package org.spoutcraft.api.event;

import com.flowpowered.events.SimpleEvent;
import org.spoutcraft.api.Prefab;

public abstract class PrefabEvent<T extends Prefab> extends SimpleEvent {
    private final T prefab;

    public PrefabEvent(T prefab) {
        this.prefab = prefab;
    }

    public T getPrefab() {
        return prefab;
    }
}
