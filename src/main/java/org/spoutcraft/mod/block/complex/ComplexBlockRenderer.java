/**
 * This file is a part of SpoutcraftMod.
 *
 * Copyright (c) 2013 SpoutcraftDev <http://spoutcraft.org>
 * SpoutcraftMod is licensed under the MIT License.
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
