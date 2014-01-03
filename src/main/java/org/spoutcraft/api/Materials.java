package org.spoutcraft.api;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class Materials {
    public static final CustomMovingMaterial CUSTOM_MOVING = new CustomMovingMaterial();
    public static final CustomBlockMaterial CUSTOM_BLOCK = new CustomBlockMaterial();

    public static class CustomBlockMaterial extends Material {
        public CustomBlockMaterial() {
            super(MapColor.clayColor);
        }
    }

    public static class CustomMovingMaterial extends Material {
        public CustomMovingMaterial() {
            super(MapColor.clayColor);
        }
    }
}
