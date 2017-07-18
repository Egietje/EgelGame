package com.Egietje.egelgame.ui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

public abstract class UIObject {

	protected float X, Y;
	protected int WIDTH, HEIGHT;
	protected Rectangle RECTANGLE;
	protected boolean HOVERING = false;

	public UIObject(float x, float y, int width, int height) {
		X = x;
		Y = y;
		WIDTH = width;
		HEIGHT = height;
		RECTANGLE = new Rectangle((int) x, (int) y, width, height);
	}

	public abstract void tick();

	public abstract void render(Graphics graphics);

	public abstract void onClick();

	public void onMouseMove(MouseEvent e) {
		if (RECTANGLE.contains(e.getPoint())) {
			HOVERING = true;
		} else {
			HOVERING = false;
		}
	}

	public void onMouseRelease(MouseEvent e) {
		if (HOVERING) {
			onClick();
		}
	}

	public float getX() {
		return X;
	}

	public void setX(float x) {
		X = x;
	}

	public float getY() {
		return Y;
	}

	public void setY(float y) {
		Y = y;
	}

	public int getWidth() {
		return WIDTH;
	}

	public void setWidth(int width) {
		WIDTH = width;
	}

	public int getHeight() {
		return HEIGHT;
	}

	public void setHeight(int height) {
		HEIGHT = height;
	}

	public boolean isHovering() {
		return HOVERING;
	}

	public void setHovering(boolean hovering) {
		HOVERING = hovering;
	}

}
