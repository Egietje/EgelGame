package com.Egietje.egelgame.graphics;

import java.awt.image.BufferedImage;

public class SpriteSheet {
	private final int SIZE = 16;
	private BufferedImage SHEET;

	public SpriteSheet(BufferedImage sheet) {
		SHEET = sheet;
	}

	public BufferedImage getTexture(int x, int y, int width, int height) {
		return SHEET.getSubimage(x * SIZE, y * SIZE, width * SIZE, height * SIZE);
	}
}
