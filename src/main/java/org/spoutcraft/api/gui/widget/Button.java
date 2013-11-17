package org.spoutcraft.api.gui.widget;

import net.minecraft.util.ResourceLocation;

import org.lwjgl.util.Color;

public class Button extends Label {
	public static final ResourceLocation DEFAULT_BACKGROUND = new ResourceLocation("spoutcraft", "textures/gui/button_background.png");
	public static final ResourceLocation DEFAULT_CLICKED_BACKGROUND = new ResourceLocation("spoutcraft", "textures/gui/button_clicked_background.png");
	private ResourceLocation clickedBackground;

	public Button(int x, int y, int heightBound, int widthBound, String text) {
		this(x, y, heightBound, widthBound, text, DEFAULT_TEXT_COLOR, DEFAULT_TEXT_COLOR, DEFAULT_DISABLED_COLOR, DEFAULT_BACKGROUND, DEFAULT_CLICKED_BACKGROUND);
	}

	public Button(int x, int y, int heightBound, int widthBound, String text, Color textColor, Color hoverColor, Color disableColor, ResourceLocation background, ResourceLocation clickedBackground) {
		super(x, y, heightBound, widthBound, text, textColor, hoverColor, disableColor, background);
		this.clickedBackground = clickedBackground;
	}

	public ResourceLocation getClickedBackground() {
		return clickedBackground;
	}

	public void setClickedBackground(ResourceLocation clickedBackground) {
		this.clickedBackground = clickedBackground;
	}
}
