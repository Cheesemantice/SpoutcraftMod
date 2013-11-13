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

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import cpw.mods.fml.relauncher.Side;
import org.spoutcraft.api.Prefab;

public class AddonPrefab extends Prefab {
	private String name;
	private String version;
	private AddonMode mode;
	private String mainClassName;

	public AddonPrefab(String identifier, String name, String version, AddonMode mode, String mainClassName) {
		super(identifier);
		this.name = name;
		this.version = version;
		this.mode = mode;
		this.mainClassName = mainClassName;
	}

	public String getName() {
		return name;
	}

	public String getVersion() {
		return version;
	}

	public String getMain() {
		return mainClassName;
	}

	@Override
	public String toString() {
		final String NEW_LINE = System.getProperty("line.separator");
		final String parent = super.toString();
		final StringBuilder builder = new StringBuilder(parent.substring(0, parent.length() - 1));
		builder
				.append(" Name: " + name + NEW_LINE)
				.append(" Version: " + version + NEW_LINE)
				.append("}");
		return builder.toString();
	}

	public boolean isValidMode(Side side) {
		switch(mode) {
			case CLIENT:
				if (side.isClient()) {
					return true;
				}
				break;
			case SERVER:
				if (side.isServer()) {
					return true;
				}
				break;
			case BOTH:
				return true;
		}

		return false;
	}
}


final class AddonJsonDeserializer implements JsonDeserializer<AddonPrefab> {
	@Override
	public AddonPrefab deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		JsonObject jsonObject = json.getAsJsonObject();
		final String identifier = jsonObject.get("identifier").getAsString();
		final String name = jsonObject.get("name").getAsString();
		final String version = jsonObject.get("version").getAsString();
		final String main = jsonObject.get("main").getAsString();
		final String modeRaw = jsonObject.get("mode").getAsString();
		AddonMode mode;
		try {
			mode = AddonMode.valueOf(modeRaw);
		} catch (Exception e) {
			return null;
		}
		return new AddonPrefab(identifier, name, version, mode, main);
	}
}
