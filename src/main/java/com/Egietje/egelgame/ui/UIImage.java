package com.Egietje.egelgame.ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class UIImage extends UIObject {
	private BufferedImage IMAGE;
	
	public UIImage(float x, float y, int width, int height, BufferedImage image) {
		super(x, y, width, height);
		IMAGE = image;
	}

	@Override
	public void tick() {
	}

	@Override
	public void render(Graphics graphics) {
		graphics.drawImage(IMAGE, (int) X, (int) Y, WIDTH, HEIGHT, null);
	}

	@Override
	public void onClick() {
	}
}
