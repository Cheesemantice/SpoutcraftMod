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
package org.spoutcraft.mod.protocol.message;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.network.INetworkManager;
import org.spoutcraft.api.Spoutcraft;
import org.spoutcraft.api.addon.Addon;
import org.spoutcraft.api.protocol.message.AddonMessage;

public class AddFileMessage extends AddonMessage {
    //SERVER
    private Path path;
    //CLIENT
    private String name;
    private byte[] data;

    @SideOnly (Side.SERVER)
    public AddFileMessage(Addon addon, Path path) {
        super(addon);
        this.path = path;
    }

    @SideOnly (Side.CLIENT)
    public AddFileMessage(Addon addon, String name, byte[] data) {
        super(addon);
        this.name = name;
        this.data = data;
    }

    @SideOnly (Side.SERVER)
    public Path getResource() {
        return path;
    }

    @SideOnly (Side.CLIENT)
    public String getName() {
        return name;
    }

    @SideOnly (Side.CLIENT)
    public byte[] getData() {
        return data;
    }

    @Override
    public void handle(Side side, INetworkManager manager, Player player) {
        //TODO Pass off recieved files to resolver
        //TODO For now, we'll use this to receive addons on the client
        Spoutcraft.getLogger().info(name);
        final Path path = Paths.get("assets", name);
        FileOutputStream stream = null;
        try {
            if (Files.exists(path)) {
                Files.delete(path);
            }
            Files.createFile(path);
            stream = new FileOutputStream(path.toFile());
            stream.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
