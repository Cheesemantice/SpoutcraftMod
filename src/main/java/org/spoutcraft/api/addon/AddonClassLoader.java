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

import java.net.URL;
import java.net.URLClassLoader;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AddonClassLoader extends URLClassLoader {
	private Addon addon;
	private final AddonLoader loader;
	private final Map<String, Class<?>> namesByClasses = new HashMap<>();
	private static final Map<String, Addon> CLASSES_IN_ADDONS = new HashMap<>();
	private static final Set<AddonClassLoader> LOADERS = new HashSet<>();

	public AddonClassLoader(ClassLoader forge, AddonLoader loader) {
		super(new URL[0], forge);
		this.loader = loader;
	}

	@Override
	protected void addURL(URL url) {
		super.addURL(url);
	}

	protected void setAddon(Addon addon) {
		if (addon != null) {
			throw new IllegalStateException("Cannot set an addon of an addon class loader twice!");
		}
		this.addon = addon;
		CLASSES_IN_ADDONS.put(addon.getClass().getName(), addon);
	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		return findClass(name, true);
	}

	protected Class<?> findClass(String name, boolean checkOtherAddons) throws ClassNotFoundException {
		Class<?> result = namesByClasses.get(name);

		if (result == null) {
			try {
				result = super.findClass(name);
			} catch (ClassNotFoundException ignored) {
			}

			if (result == null && checkOtherAddons) {
				result = loader.getClassByName(name, this);
			}

			if (result != null) {
				namesByClasses.put(name, result);
				CLASSES_IN_ADDONS.put(name, addon);
			} else {
				throw new ClassNotFoundException(name);
			}
		}

		return result;
	}

	public Set<String> getClassNames() {
		return Collections.unmodifiableSet(namesByClasses.keySet());
	}

	public Collection<Class<?>> getClasses() {
		return Collections.unmodifiableCollection(namesByClasses.values());
	}

	public static Addon getAddon(String className) {
		return CLASSES_IN_ADDONS.get(className);
	}

	public static Class<?> findAddonClass(String name) throws ClassNotFoundException {
		for (AddonClassLoader loader : LOADERS) {
			Class<?> clazz = loader.findClass(name);
			if (clazz != null) {
				return clazz;
			}
		}
		throw new ClassNotFoundException("Class " + name + " was unable to be found");
	}
}
