package org.spoutcraft.api;

import java.util.logging.Logger;

import org.junit.Test;
import org.spoutcraft.api.logger.SpoutcraftLogger;
import org.spoutcraft.mod.addon.ServerAddonManager;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;

public class AddonManagerTest {
    @Test
    public void test() {
        Spoutcraft.setLogger(new SpoutcraftLogger(Logger.getLogger("Spoutcraft")));
        final ServerAddonManager manager = (ServerAddonManager) Spoutcraft.setAddonManager(new ServerAddonManager());
        assertNotNull(manager.getAddon("spoutcraft"));
        assertNull(manager.getAddon("test"));
        assertEquals(manager.getAddon("spoutcraft"), manager.getInternalAddon());
    }
}
