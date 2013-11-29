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
package org.spoutcraft.api.resource;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Map;

import org.spoutcraft.api.addon.Addon;

public interface FileSystem {
    public void init() throws IOException;

    public void send(Class<? extends Addon> clazz, Path path);

    public void send(Class<? extends Addon> clazz, String uri);

    public <R> R get(Class<? extends Addon> clazz, String name);

    public <R> Collection<R> getAllFor(Class<? extends Addon> clazz);

    public <R> Map<Class<? extends Addon>, R> getAll();
}
