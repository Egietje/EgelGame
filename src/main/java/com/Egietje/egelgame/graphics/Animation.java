package com.Egietje.egelgame.graphics;

import java.awt.image.BufferedImage;

public class Animation {

	private int SPEED, INDEX;
	private long LAST_TIME, TIMER;
	private BufferedImage[] FRAMES;

	public Animation(int speed, BufferedImage[] frames) {
		SPEED = speed;
		FRAMES = frames;
		INDEX = 0;
		TIMER = 0;
		LAST_TIME = System.currentTimeMillis();
	}

	public void tick() {
		TIMER += System.currentTimeMillis() - LAST_TIME;
		LAST_TIME = System.currentTimeMillis();

		if (TIMER > SPEED) {
			INDEX++;
			TIMER = 0;
			if (INDEX >= FRAMES.length) {
				INDEX = 0;
			}
		}
	}

	public BufferedImage getCurrentFrame() {
		return FRAMES[INDEX];
	}

}
