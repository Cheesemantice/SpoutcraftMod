package org.spoutcraft.mod.block.complex;

import net.minecraft.block.Block;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import org.spoutcraft.api.block.ComplexBlockPrefab;
import org.spoutcraft.mod.SpoutcraftMod;
import org.spoutcraft.mod.material.CustomMaterial;

public class ComplexCustomBlock extends Block {
    private final ComplexBlockPrefab prefab;

    public ComplexCustomBlock(int id, ComplexBlockPrefab prefab, CustomMaterial material) {
        super(id, material);
        this.prefab = prefab;
        setUnlocalizedName(prefab.getIdentifier());
        setTextureName("spoutcraft:" + prefab.getIdentifier());
        setHardness(prefab.getHardness());

        if (prefab.shouldShowInCreativeTab()) {
            setCreativeTab(SpoutcraftMod.getCustomTabs());
        }

    }

    @Override
    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        return prefab.getLightValue(world, x, y, z);
    }

    @Override
    public int getLightOpacity(World world, int x, int y, int z) {
        return prefab.getLightOpacity(world, x, y, z);
    }

    public ComplexBlockPrefab getPrefab() {
        return prefab;
    }
}
