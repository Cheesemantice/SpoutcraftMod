package org.spoutcraft.mod.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class SpoutcraftEntityRendererAdapter extends RenderLiving {
	private final ResourceLocation location;

	public SpoutcraftEntityRendererAdapter(ModelBase par1ModelBase, float par2, String name) {
		super(par1ModelBase, par2);
		location = new ResourceLocation("spoutcraft", "models/" + name);
	}

	@Override
	protected ResourceLocation func_110775_a(Entity entity) {
		return location;
	}
}
