package com.Egietje.egelgame.graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

public class Text {

	public static void drawString(Graphics graphics, String text, int xPos, int yPos, boolean center, Color color,
			Font font) {
		graphics.setColor(color);
		graphics.setFont(font);
		int x = xPos;
		int y = yPos;
		if (center) {
			FontMetrics fm = graphics.getFontMetrics(font);
			x = xPos - fm.stringWidth(text) / 2;
			y = yPos - fm.getHeight() / 2 + fm.getAscent();
		}
		graphics.drawString(text, x, y);
	}

}
