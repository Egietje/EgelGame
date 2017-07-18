package com.Egietje.egelgame.state;

import java.awt.Graphics;

import com.Egietje.egelgame.Handler;
import com.Egietje.egelgame.graphics.Assets;
import com.Egietje.egelgame.ui.*;

public class MenuState extends State {
	private UIManager MENU_MANAGER;

	public MenuState(Handler handler) {
		super(handler);
		MENU_MANAGER = new UIManager(handler);
	}

	@Override
	public void tick() {
		MENU_MANAGER.tick();
	}

	@Override
	public void render(Graphics graphics) {
		graphics.drawImage(Assets.MENU_BACKGROUND, 0, 0, HANDLER.getWidth(), HANDLER.getHeight(), null);
		MENU_MANAGER.render(graphics);
	}

	@Override
	public void onSet() {
		HANDLER.getMouseManager().setManager(MENU_MANAGER);
		MENU_MANAGER.addObject(new UIImage(HANDLER.getWidth() / 2 - 256, HANDLER.getHeight() / 4 - 128, 512, 256, Assets.MENU_LOGO));
		MENU_MANAGER.addObject(new UIImageButton(HANDLER.getWidth() / 2 - 48, HANDLER.getHeight() / 2 - 48, 96, 32,
				Assets.BUTTON_START, () -> {
					HANDLER.getMouseManager().setManager(null);
					StateManager.setCurrentState(HANDLER.getGame().GAME_STATE);
				}));
		MENU_MANAGER.addObject(new UIImageButton(HANDLER.getWidth() / 2 - 48, HANDLER.getHeight() / 2 + 16, 96, 32,
				Assets.BUTTON_SETTINGS, () -> {
					HANDLER.getMouseManager().setManager(null);
					StateManager.setCurrentState(HANDLER.getGame().SETTINGS_STATE);
				}));
	}
}
