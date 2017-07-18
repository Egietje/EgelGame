package com.Egietje.egelgame.ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class UIImageButton extends UIObject {
	private BufferedImage[] IMAGES;
	private IClickListener CLICKER;

	public UIImageButton(float x, float y, int width, int height, BufferedImage[] images, IClickListener clicker) {
		super(x, y, width, height);
		IMAGES = images;
		CLICKER = clicker;
	}

	@Override
	public void tick() {
	}

	@Override
	public void render(Graphics graphics) {
		if (HOVERING) {
			graphics.drawImage(IMAGES[1], (int) X, (int) Y, WIDTH, HEIGHT, null);
		} else {
			graphics.drawImage(IMAGES[0], (int) X, (int) Y, WIDTH, HEIGHT, null);
		}
	}

	@Override
	public void onClick() {
		CLICKER.onClick();
	}
}
