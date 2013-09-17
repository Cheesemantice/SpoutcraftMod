package org.spoutcraft.mod.protocol;

import org.spoutcraft.mod.protocol.message.SpoutcraftAddResourceMessage;
import org.spoutcraft.mod.protocol.message.SpoutcraftHelloMessage;
import org.spoutcraft.mod.protocol.message.SpoutcraftMessage;

public class ProtocolRegistry {
    private static final SpoutcraftMessage[] MESSAGES = new SpoutcraftMessage[] {
        new SpoutcraftHelloMessage(),
        new SpoutcraftAddResourceMessage()
    };

    public static SpoutcraftMessage get(String channel) {
        if (channel != null && !channel.isEmpty()) {
            for (SpoutcraftMessage message : MESSAGES) {
                if (message.getChannel().equals(channel)) {
                    return message;
                }
            }
        }
        return null;
    }
}
