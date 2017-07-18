package com.Egietje.egelgame.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {
	public static BufferedImage loadImage(String imageName) {
		try {
			return ImageIO.read(ImageLoader.class.getResource("/textures/" + imageName + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}
}
