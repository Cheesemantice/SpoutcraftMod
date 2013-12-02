package org.spoutcraft.mod.protocol.message;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;

import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.network.INetworkManager;
import org.spoutcraft.api.Spoutcraft;
import org.spoutcraft.api.protocol.message.Message;

public class DownloadLinkMessage implements Message {
    private final String addonIdentifier;
    private final URL url;

    public DownloadLinkMessage(String addonIdentifier, String url) throws MalformedURLException {
        this(addonIdentifier, new URL(url));
    }

    public DownloadLinkMessage(String addonIdentifier, URL url) {
        this.addonIdentifier = addonIdentifier;
        this.url = url;
    }

    public String getAddonIdentifier() {
        return addonIdentifier;
    }

    public URL getUrl() {
        return url;
    }

    @Override
    public void handle(Side side, INetworkManager manager, Player player) {
        Spoutcraft.getLogger().log(Level.INFO, addonIdentifier);
        Spoutcraft.getLogger().log(Level.INFO, url.toString());
    }
}
