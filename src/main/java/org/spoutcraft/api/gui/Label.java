package org.spoutcraft.api.gui;

import net.minecraft.util.ResourceLocation;

import org.lwjgl.util.Color;

public class Label extends Widget {
	private static final Color DEFAULT_TEXT_COLOR = new Color(Color.WHITE);
	private static final Color DEFAULT_DISABLED_COLOR = new Color(Color.LTGREY);
	private static final ResourceLocation DEFAULT_BACKGROUND = new ResourceLocation("spoutcraft", "textures/gui/label_background.png");
	private String text;
	private Color textColor, hoverColor, disableColor;
	private ResourceLocation background;

	public Label(int x, int y, int heightBound, int widthBound, String text) {
		this(x, y, heightBound, widthBound, text, DEFAULT_TEXT_COLOR, DEFAULT_TEXT_COLOR, DEFAULT_DISABLED_COLOR, DEFAULT_BACKGROUND);
	}

	public Label(int x, int y, int heightBound, int widthBound, String text, Color textColor, Color hoverColor, Color disableColor, ResourceLocation background) {
		super(x, y, heightBound, widthBound);
		this.text = text;
		this.textColor = textColor;
		this.hoverColor = hoverColor;
		this.disableColor = disableColor;
		this.background = background;
	}

	public String getText() {
		return text;
	}

	public Label setText(String text) {
		this.text = text;
		return this;
	}

	public Color getTextColor() {
		return textColor;
	}

	public Label setTextColor(Color textColor) {
		this.textColor = textColor;
		return this;
	}

	public Color getHoverColor() {
		return hoverColor;
	}

	public Label setHoverColor(Color hoverColor) {
		this.hoverColor = hoverColor;
		return this;
	}

	public Color getDisableColor() {
		return disableColor;
	}

	public Label setDisableColor(Color disableColor) {
		this.disableColor = disableColor;
		return this;
	}

	public ResourceLocation getBackground() {
		return background;
	}

	public Label setBackground(ResourceLocation background) {
		this.background = background;
		return this;
	}

	public void onTextChanged(String previous) {

	}
}
