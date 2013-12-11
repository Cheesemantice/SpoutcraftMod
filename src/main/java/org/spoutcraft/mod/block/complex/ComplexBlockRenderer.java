package org.spoutcraft.mod.block.complex;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import org.spoutcraft.api.block.ComplexBlockPrefab;

public class ComplexBlockRenderer implements ISimpleBlockRenderingHandler {
    private final ComplexBlockPrefab prefab;

    public ComplexBlockRenderer(ComplexBlockPrefab prefab) {
        this.prefab = prefab;
    }

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
        prefab.renderInventoryBlock(block, metadata, modelID, renderer);
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        return prefab.renderWorldBlock(world, x, y, z, block, modelId, renderer);
    }

    @Override
    public boolean shouldRender3DInInventory() {
        return prefab.shouldRender3DInInventory();
    }

    @Override
    public int getRenderId() {
        return ComplexBlockPrefab.RENDER_LEVEL;
    }
}
