package org.spoutcraft.api.protocol.message;

import org.spoutcraft.api.addon.Addon;

public abstract class AddonMessage implements Message {
    private final Addon addon;

    public AddonMessage(Addon addon) {
        this.addon = addon;
    }

    public Addon getAddon() {
        return addon;
    }
}
