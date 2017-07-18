package com.Egietje.egelgame;

import com.Egietje.egelgame.graphics.GameCamera;
import com.Egietje.egelgame.input.KeyManager;
import com.Egietje.egelgame.input.MouseManager;
import com.Egietje.egelgame.world.World;

public class Handler {
	private Game GAME;
	private World WORLD;

	public Handler(Game game) {
		GAME = game;
	}

	public GameCamera getCamera() {
		return GAME.getCamera();
	}

	public KeyManager getKeyManager() {
		return GAME.getKeyManager();
	}

	public MouseManager getMouseManager() {
		return GAME.getMouseManager();
	}

	public int getWidth() {
		return GAME.getWidth();
	}

	public int getHeight() {
		return GAME.getHeight();
	}

	public Game getGame() {
		return GAME;
	}

	public void setGame(Game game) {
		GAME = game;
	}

	public World getWorld() {
		return WORLD;
	}

	public void setWorld(World world) {
		WORLD = world;
	}
}
