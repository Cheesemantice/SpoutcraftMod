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
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.network.INetworkManager;
import org.spoutcraft.api.Spoutcraft;
import org.spoutcraft.api.addon.Addon;
import org.spoutcraft.api.protocol.message.AddonMessage;

public class AddFileMessage extends AddonMessage {
    private static Map<String, SplitFile> fileDataBuffer = new HashMap<String, SplitFile>();
    //CLIENT
    private String name;
    private int filePart;
    private int filePartCount;
    private byte[] data;

    public AddFileMessage(Addon addon, String name, int part, int partCount, byte[] data) {
        super(addon);
        this.name = name;
        this.filePart = part;
        this.filePartCount = partCount;
        this.data = data;
    }

    public String getFileName() {
        return name;
    }

    public int getPart() {
        return filePart;
    }

    public int getPartCount() {
        return filePartCount;
    }

    public byte[] getData() {
        return data;
    }

    @Override
    public void handle(Side side, INetworkManager manager, Player player) {

        SplitFile split;
        if (fileDataBuffer.containsKey(name)) {
            split = fileDataBuffer.get(name);
        } else {
            split = new SplitFile(filePartCount);
            fileDataBuffer.put(name, split);
        }
        split.addPart(filePart, data);

        //TODO Pass off recieved files to resolver
        //TODO For now, we'll use this to receive addons on the client
        if (split.fileComplete()) {
            Spoutcraft.getLogger().info(name);
            final Path path = Paths.get("assets", name);
            FileOutputStream stream = null;
            try {
                if (Files.exists(path)) {
                    Files.delete(path);
                }
                Files.createFile(path);
                stream = new FileOutputStream(path.toFile());
                //stream.write(data);
                split.write(stream);
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
                fileDataBuffer.remove(name);
            }
        }
    }

    private class SplitFile {
        public byte[][] parts;
        public int maxParts;
        public int partsReceived;

        public SplitFile(int maxParts) {
            this.maxParts = maxParts;
            this.parts = new byte[maxParts][];
        }

        public void addPart(int part, byte[] data) {
            parts[part] = data;
            this.partsReceived++;
        }

        public boolean fileComplete() {
            return partsReceived == maxParts;
        }

        public void write(OutputStream out) throws IOException {
            for (byte[] chunk : parts) {
                out.write(chunk);
            }
        }
    }

    public static List<AddFileMessage> splitFileToMessages(Addon addon, Path path) throws IOException {
        //Leave room for other packet related data
        ByteBuffer readBuff = ByteBuffer.allocate(15000);
        ReadableByteChannel channel = Files.newByteChannel(path, StandardOpenOption.READ);
        List<AddFileMessage> messages = new ArrayList<AddFileMessage>();
        int amnt;
        int part = 0;
        String name = path.getFileName().toString();
        while ((amnt = channel.read(readBuff)) != -1) {
            readBuff.flip();
            byte[] chunk = new byte[amnt];
            readBuff.get(chunk);
            readBuff.clear();
            messages.add(new AddFileMessage(addon, name, part, 0, chunk));
            part++;
        }
        //part is now maxParts
        for (AddFileMessage msg : messages) {
            msg.filePartCount = part;
        }
        return messages;
    }
}
