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
package org.spoutcraft.api.addon;

import java.nio.file.Path;

import cpw.mods.fml.relauncher.Side;

/**
 * An addon is like a Mod or a Plugin (from Bukkit). It is the bridge between external code and Spoutcraft API's framework.
 *
 * The power of addons lie in the ability to use Spoutcraft API and Forge without needing to make a new Mod.
 */
public abstract class Addon {
	private AddonPrefabRegistry loader;
	private AddonPrefab prefab;
	private AddonClassLoader classLoader;
	private Path dataPath, root;
	private boolean enabled = false;

	public void initialize(AddonPrefabRegistry loader, AddonPrefab prefab, AddonClassLoader classLoader, Path dataPath, Path root) {
		this.loader = loader;
		this.prefab = prefab;
		this.classLoader = classLoader;
		this.dataPath = dataPath;
		this.root = root;
	}

	public void onLoad(Side side) {

	}

	public void onEnable(Side side) {

	}

	public AddonPrefab getPrefab() {
		return prefab;
	}

	protected void enable() {
		this.enabled = true;
	}

	protected void disable() {
		this.enabled = false;
	}
}
