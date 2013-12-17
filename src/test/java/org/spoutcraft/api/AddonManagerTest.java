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
package org.spoutcraft.api;

import java.util.logging.Logger;

import org.junit.Test;
import org.spoutcraft.api.logger.SpoutcraftLogger;
import org.spoutcraft.mod.addon.ServerAddonManager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class AddonManagerTest {
    @Test
    public void test() {
        if (Spoutcraft.getLogger() == null) {
            Spoutcraft.setLogger(new SpoutcraftLogger(Logger.getLogger("Spoutcraft")));
        }
        ServerAddonManager manager = (ServerAddonManager) Spoutcraft.getAddonManager();
        if (manager == null) {
            manager = (ServerAddonManager) Spoutcraft.setAddonManager(new ServerAddonManager());
        }
        assertNotNull(manager.getAddon("spoutcraft"));
        assertNull(manager.getAddon("test"));
        assertEquals(manager.getAddon("spoutcraft"), manager.getInternalAddon());
    }
}
