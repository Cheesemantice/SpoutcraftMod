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
package org.spoutcraft.api.addon;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import cpw.mods.fml.relauncher.Side;

public final class AddonDescription {
    private final String identifier;
    private final String name;
    private final String version;
    private final AddonMode mode;
    private final String mainClassName;

    public AddonDescription(String identifier, String name, String version, AddonMode mode, String mainClassName) {
        this.identifier = identifier;
        this.name = name;
        this.version = version;
        this.mode = mode;
        this.mainClassName = mainClassName;
    }

    public String getIdentifier() {
        return identifier;
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

    public boolean isValidMode(Side side) {
        switch (mode) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final AddonDescription that = (AddonDescription) o;

        return identifier.equals(that.identifier);
    }

    @Override
    public int hashCode() {
        return identifier.hashCode();
    }

    @Override
    public String toString() {
        final String NEW_LINE = System.getProperty("line.separator");
        return (getClass().getName() + " {" + NEW_LINE) + " Identifier: " + identifier + NEW_LINE + " Name: " + name + NEW_LINE + " Version: " + version + NEW_LINE + "}";
    }
}

final class AddonDescriptionJsonDeserializer implements JsonDeserializer<AddonDescription> {
    @Override
    public AddonDescription deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
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
            throw new JsonParseException(modeRaw + " is not a valid addon mode [CLIENT, SERVER, BOTH]");
        }
        return new AddonDescription(identifier, name, version, mode, main);
    }
}
