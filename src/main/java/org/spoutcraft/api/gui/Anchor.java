/**
 * This file is part of SpoutcraftMod, licensed under the MIT License (MIT).
 *
 * Copyright (c) 2013-2014 SpoutcraftDev <http://spoutcraft.org/>
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
package org.spoutcraft.api.gui;

import java.util.HashMap;

public enum Anchor {
	/**
	 * Using Anchor positions allows you to auto-align a GUI object based on 
	 * pre-set coordinates on the screen that isn't locked to a static
	 * x & y location on the screen
	 * Anchor positions are relative positions that float on the screen to the
	 * same relative location based on the current screen width and height.
	 * 
	 * Objects draw from top/left corner.
	 */
	TOP_LEFT(0), // x = 0, y = 0
	TOP_CENTER(1), // screenwidth/2 + x, y
	TOP_RIGHT(2), // screenwidth + x, y
	CENTER_LEFT(3), // x, screenheight/2 + y
	CENTER_CENTER(4), // screenwidth/2 + x, screenheight/2 + y
	CENTER_RIGHT(5), // screenwidth + x, screenheight/2 + y
	BOTTOM_LEFT(6), // x, screenheight + y
	BOTTOM_CENTER(7), // screenwidth/2 + x, screenheight + y
	BOTTOM_RIGHT(8), // screenwidth + x, screenheight + y
	SCALE(9), // Scales
	;

	private final int id;
	
	Anchor(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	private static final HashMap<Integer, Anchor> lookupId = new HashMap<Integer, Anchor>();

	static {
		for (Anchor t : values()) {
			lookupId.put(t.getId(), t);
		}
	}

	public static Anchor getAnchorFromId(int id) {
		return lookupId.get(id);
	}
}
