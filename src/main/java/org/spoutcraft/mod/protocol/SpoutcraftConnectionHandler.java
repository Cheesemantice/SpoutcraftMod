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
package org.spoutcraft.mod.protocol;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.EnumSet;
import java.util.List;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.network.IConnectionHandler;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.NetLoginHandler;
import net.minecraft.network.packet.NetHandler;
import net.minecraft.network.packet.Packet1Login;
import net.minecraft.server.MinecraftServer;
import org.spoutcraft.api.Spoutcraft;
import org.spoutcraft.mod.addon.ServerAddonManager;
import org.spoutcraft.mod.block.BlockPrefabRegistry;
import org.spoutcraft.mod.item.ItemPrefabRegistry;
import org.spoutcraft.mod.material.MaterialPrefabRegistry;
import org.spoutcraft.mod.protocol.message.AddFileMessage;

public class SpoutcraftConnectionHandler implements IConnectionHandler {
    @Override
    public void playerLoggedIn(Player player, NetHandler netHandler, final INetworkManager manager) {

        if (!FMLCommonHandler.instance().getMinecraftServerInstance().isSinglePlayer()) {
            ((MaterialPrefabRegistry) Spoutcraft.getMaterialPrefabRegistry()).sync(manager);
            ((ItemPrefabRegistry) Spoutcraft.getItemPrefabRegistry()).sync(manager);
            ((BlockPrefabRegistry) Spoutcraft.getBlockPrefabRegistry()).sync(manager);

            //TODO Test code for file sending...tried sending it a tick later to see if I get better results
            try {
                TickRegistry.registerTickHandler(new ITickHandler() {
                    final List<AddFileMessage> messages = AddFileMessage.splitFileToMessages(((ServerAddonManager) Spoutcraft.getAddonManager()).getInternalAddon(), Paths.get("test.iso"));

                    @Override
                    public void tickStart(EnumSet<TickType> tickTypes, Object... objects) {
                    }

                    @Override
                    public void tickEnd(EnumSet<TickType> tickTypes, Object... objects) {
                        if (messages.isEmpty()) {
                            return;
                        }
                        manager.addToSendQueue(new SpoutcraftPacket(messages.remove(0)));
                    }

                    @Override
                    public EnumSet<TickType> ticks() {
                        return EnumSet.of(TickType.SERVER);
                    }

                    @Override
                    public String getLabel() {
                        return "Spoutcraft - Sync addons";
                    }
                }, Side.SERVER);
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }

    @Override
    public String connectionReceived(NetLoginHandler netHandler, INetworkManager manager) {
        return "";
    }

    @Override
    public void connectionOpened(NetHandler netClientHandler, String server, int port, INetworkManager manager) {

    }

    @Override
    public void connectionOpened(NetHandler netClientHandler, MinecraftServer server, INetworkManager manager) {

    }

    @Override
    public void connectionClosed(INetworkManager manager) {

    }

    @Override
    public void clientLoggedIn(NetHandler clientHandler, INetworkManager manager, Packet1Login login) {

    }
}
