package com.Egietje.egelgame.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

	private boolean[] KEYS, PRESSED, CANT_PRESS;
	public boolean UP, DOWN, LEFT, RIGHT;
	public boolean ATTACK;

	public KeyManager() {
		KEYS = new boolean[256];
		PRESSED = new boolean[KEYS.length];
		CANT_PRESS = new boolean[KEYS.length];
	}

	public void tick() {
		for (int i = 0; i < KEYS.length; i++) {
			if (CANT_PRESS[i] && !KEYS[i]) {
				CANT_PRESS[i] = false;
			} else if (PRESSED[i]) {
				CANT_PRESS[i] = true;
				PRESSED[i] = false;
			} else if (!CANT_PRESS[i] && KEYS[i]) {
				PRESSED[i] = true;
			}
		}
		UP = KEYS[KeyEvent.VK_W];
		DOWN = KEYS[KeyEvent.VK_S];
		LEFT = KEYS[KeyEvent.VK_A];
		RIGHT = KEYS[KeyEvent.VK_D];
		ATTACK = KEYS[KeyEvent.VK_SPACE];
	}

	public boolean keyJustPressed(int keyCode) {
		if (keyCode > -1 && keyCode < KEYS.length) {
			return PRESSED[keyCode];
		}
		return false;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() > -1 && e.getKeyCode() < KEYS.length) {
			KEYS[e.getKeyCode()] = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() > -1 && e.getKeyCode() < KEYS.length) {
			KEYS[e.getKeyCode()] = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}
