/**
 * This file is part of SpoutcraftMod, licensed under the MIT License (MIT).
 *
 * Copyright (c) 2013 SpoutcraftDev <http://spoutcraft.org/>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
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
