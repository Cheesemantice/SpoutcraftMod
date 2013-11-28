/**
 * This file is a part of Spoutcraft.
 *
 * Copyright (c) 2013 SpoutcraftDev <http://spoutcraft.org>
 * Spoutcraft is licensed under the MIT License.
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
package org.spoutcraft.test.item;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spoutcraft.api.item.FoodPrefab;

public class TestFood extends FoodPrefab {
	public TestFood() {
		super("testfood", "Test Food", 5, 2, 10, false, true);
	}

	@Override
	public boolean onLeftClickEntity(Side side, ItemStack stack, EntityPlayer player, Entity entity) {
		return false;
	}

	@Override
	public ItemStack onItemRightClick(Side side, ItemStack stack, World world, EntityPlayer player) {
		if (side.isServer()) {
			player.addChatMessage("You right click'd with the prefab: " + getIdentifier());
		}
		return stack;
	}

	@Override
	public ItemStack onEaten(Side side, ItemStack stack, World world, EntityPlayer player) {
		if (side.isServer()) {
			world.createExplosion(player, player.posX, player.posY, player.posZ, 3f, true);
		}
		return stack;
	}
}
