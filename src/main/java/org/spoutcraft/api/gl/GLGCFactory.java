package org.spoutcraft.api.gl;

import java.util.EnumSet;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GLGCFactory {
    private final static Queue<GLGCObject> glDeleteQueue = new ConcurrentLinkedQueue<>();

    static {
        TickRegistry.registerTickHandler(new ITickHandler() {
            @Override
            public void tickStart(EnumSet<TickType> type, Object... tickData) {
            }

            @Override
            public void tickEnd(EnumSet<TickType> type, Object... tickData) {
                GLGCObject toDelete;
                while ((toDelete = glDeleteQueue.poll()) != null) {
                    toDelete.delete();
                }
            }

            @Override
            public EnumSet<TickType> ticks() {
                return EnumSet.of(TickType.CLIENT);
            }

            @Override
            public String getLabel() {
                return "Spoutcraft - GL Garbage Collection";
            }
        }, Side.CLIENT);
    }

    public static void offer(GLGCObject toDelete) {
        glDeleteQueue.offer(toDelete);
    }
}
