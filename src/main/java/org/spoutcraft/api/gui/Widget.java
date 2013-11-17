package org.spoutcraft.api.gui;

public abstract class Widget {
	private int x, y, heightBound, widthBound;
	private boolean focused, shown, enabled;

	public Widget(int x, int y, int heightBound, int widthBound) {
		this.x = x;
		this.y = y;
		this.heightBound = heightBound;
		this.widthBound = widthBound;
	}

	public int getX() {
		return x;
	}

	public Widget setX(int x) {
		this.x = x;
		return this;
	}

	public int getY() {
		return y;
	}

	public Widget setY(int y) {
		this.y = y;
		return this;
	}

	public int getHeightBound() {
		return heightBound;
	}

	public Widget setHeightBound(int heightBound) {
		this.heightBound = heightBound;
		return this;
	}

	public int getWidthBound() {
		return widthBound;
	}

	public Widget setWidthBound(int widthBound) {
		this.widthBound = widthBound;
		return this;
	}

	public void enable() {
		this.enabled = true;
	}

	public void disable() {
		this.enabled = false;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void focus(boolean focused) {
		this.focused = focused;
	}

	public boolean isFocused() {
		return focused;
	}

	public void show() {
		this.shown = true;
	}

	public void hide() {
		this.shown = false;
	}

	public boolean isVisible() {
		return shown;
	}

	public void onEnable() {

	}

	public void onDisable() {

	}

	public void onShow() {

	}

	public void onHide() {

	}

	public void onHover(int x, int y) {

	}

	public void onClick(ClickAction action, int x, int y) {

	}

	public void onFocus(FocusReason reason) {

	}
}
