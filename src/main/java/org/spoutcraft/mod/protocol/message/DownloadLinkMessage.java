package org.spoutcraft.mod.protocol.message;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;

import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.network.INetworkManager;
import org.spoutcraft.api.Spoutcraft;
import org.spoutcraft.api.addon.Addon;
import org.spoutcraft.api.protocol.message.AddonMessage;

public class DownloadLinkMessage extends AddonMessage {
    private final URL url;

    public DownloadLinkMessage(Addon addon, String url) throws MalformedURLException {
        this(addon, new URL(url));
    }

    public DownloadLinkMessage(Addon addon, URL url) {
        super(addon);
        this.url = url;
    }

    public URL getUrl() {
        return url;
    }

    @Override
    public void handle(Side side, INetworkManager manager, Player player) {
        Spoutcraft.getLogger().log(Level.INFO, getAddon().getPrefab().getIdentifier());
        Spoutcraft.getLogger().log(Level.INFO, url.toString());
    }
}
