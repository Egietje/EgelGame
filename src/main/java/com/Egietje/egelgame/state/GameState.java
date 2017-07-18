package com.Egietje.egelgame.state;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import com.Egietje.egelgame.Handler;
import com.Egietje.egelgame.ui.UIManager;
import com.Egietje.egelgame.world.World;

public class GameState extends State {
	private World WORLD;
	private UIManager GAME_MANAGER;
	private int ID = 1;

	public GameState(Handler handler) {
		super(handler);
		GAME_MANAGER = new UIManager(handler);
		WORLD = new World(HANDLER, "world" + ID);
		HANDLER.setWorld(WORLD);
	}

	@Override
	public void tick() {
		if (HANDLER.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE)) {
			StateManager.setCurrentState(HANDLER.getGame().MENU_STATE);
		}
		if (HANDLER.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER)) {
			ID++;
			if (ID > 7) {
				ID = 1;
			}
			WORLD = new World(HANDLER, "world" + ID);
			HANDLER.setWorld(WORLD);
		}
		GAME_MANAGER.tick();
		WORLD.tick();
	}

	@Override
	public void render(Graphics graphics) {
		GAME_MANAGER.render(graphics);
		WORLD.render(graphics);
	}

	@Override
	public void onSet() {
		HANDLER.getMouseManager().setManager(GAME_MANAGER);
		WORLD = new World(HANDLER, "world" + ID);
		HANDLER.setWorld(WORLD);
	}
}
