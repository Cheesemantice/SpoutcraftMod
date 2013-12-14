package org.spoutcraft.api;

import java.util.logging.Logger;

import cpw.mods.fml.relauncher.Side;
import org.junit.Test;
import org.spoutcraft.api.addon.Addon;
import org.spoutcraft.api.addon.InternalAddon;
import org.spoutcraft.api.block.BlockPrefab;
import org.spoutcraft.api.logger.SpoutcraftLogger;
import org.spoutcraft.api.material.MapIndex;
import org.spoutcraft.api.material.MaterialPrefab;
import org.spoutcraft.mod.addon.ServerAddonManager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class PrefabTest {
    @Test
    public void test() {
        //TODO Lets see if this fixes travis...
        if (Spoutcraft.getLogger() == null) {
            Spoutcraft.setLogger(new SpoutcraftLogger(Logger.getLogger("Spoutcraft")));
        }
        if (Spoutcraft.getAddonManager() == null) {
            Spoutcraft.setAddonManager(new ServerAddonManager());
        }
        final Addon addon = ((ServerAddonManager) Spoutcraft.getAddonManager()).getInternalAddon();
        final MaterialPrefab mtest = new MaterialPrefab(addon, "test", MapIndex.DIRT);
        final BlockPrefab test = new BlockPrefab(addon, "test", "test", mtest, 1f, 1, 1, true);
        assertNotEquals(mtest, test);
        assertEquals(test.getAddonIdentifier(), addon.getDescription().getIdentifier());
    }
}
