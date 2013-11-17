package org.spoutcraft.api.util;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;

public class RenderUtil {
	public static final Tessellator TESSELLATOR = Tessellator.instance;
	public static final Minecraft MINECRAFT = FMLClientHandler.instance().getClient();

	public static void create2DRectangleModal(double x, double y, double width, double height, double zLevel) {
		TESSELLATOR.startDrawingQuads();
		TESSELLATOR.addVertexWithUV(x + 0, y + height, zLevel, 0, 1);
		TESSELLATOR.addVertexWithUV(x + width, y + height, zLevel, 1, 1);
		TESSELLATOR.addVertexWithUV(x + width, y + 0, zLevel, 1,0);
		TESSELLATOR.addVertexWithUV(x + 0, y + 0, zLevel, 0, 0);
	}
}
