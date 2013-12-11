package org.spoutcraft.api.block;

import java.nio.file.Path;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.spoutcraft.api.material.MaterialPrefab;

public class ComplexBlockPrefab extends BlockPrefab {
    //TODO Check this
    public static final int RENDER_LEVEL = 10;

    private Path objPath;

    @SideOnly(Side.CLIENT)
    private IModelCustom model;

    public ComplexBlockPrefab(String identifier, String displayName, MaterialPrefab prefab, float hardness, int light, int lightOpacity, boolean showInCreativeTab, Path objPath) {
        super(identifier, displayName, prefab, hardness, light, lightOpacity, showInCreativeTab);
        if (FMLCommonHandler.instance().getEffectiveSide().isClient()) {
            //TODO Breakpoint this
            model = AdvancedModelLoader.loadModel(objPath.toString());
        }
    }

    @SideOnly(Side.CLIENT)
    public ComplexBlockPrefab(String identifier, String displayName, MaterialPrefab prefab, float hardness, int light, int lightOpacity, boolean showInCreativeTab, IModelCustom model) {
        super(identifier, displayName, prefab, hardness, light, lightOpacity, showInCreativeTab);
        this.model = model;
    }

    public Path getObjPath() {
        return objPath;
    }

    @SideOnly(Side.CLIENT)
    public IModelCustom getModel() {
        return model;
    }

    @SideOnly(Side.CLIENT)
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
        model.renderAll();
    }

    @SideOnly(Side.CLIENT)
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        model.renderAll();
        return true;
    }

    @SideOnly(Side.CLIENT)
    public boolean shouldRender3DInInventory() {
        return true;
    }
}
