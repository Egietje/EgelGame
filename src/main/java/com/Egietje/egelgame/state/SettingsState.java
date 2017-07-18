package com.Egietje.egelgame.state;

import java.awt.Graphics;

import com.Egietje.egelgame.Handler;
import com.Egietje.egelgame.graphics.Assets;
import com.Egietje.egelgame.ui.*;

public class SettingsState extends State {
	private UIManager SETTINGS_MANAGER;

	public SettingsState(Handler handler) {
		super(handler);
		SETTINGS_MANAGER = new UIManager(handler);
	}

	@Override
	public void tick() {
		SETTINGS_MANAGER.tick();
	}

	@Override
	public void render(Graphics graphics) {
		SETTINGS_MANAGER.render(graphics);
	}

	@Override
	public void onSet() {
		HANDLER.getMouseManager().setManager(SETTINGS_MANAGER);
		SETTINGS_MANAGER.addObject(new UIImage(HANDLER.getWidth() / 2 - 256, HANDLER.getHeight() / 4 - 128, 512, 256, Assets.MENU_LOGO));
		SETTINGS_MANAGER.addObject(new UIImageButton(HANDLER.getWidth() / 2 - 48, HANDLER.getHeight() / 2 - 48, 96, 32,
				Assets.BUTTON_START, () -> {
					HANDLER.getMouseManager().setManager(null);
					StateManager.setCurrentState(HANDLER.getGame().GAME_STATE);
				}));
		SETTINGS_MANAGER.addObject(new UIImageButton(HANDLER.getWidth() / 2 - 48, HANDLER.getHeight() / 2 + 16, 96, 32,
				Assets.BUTTON_SETTINGS, () -> {
					HANDLER.getMouseManager().setManager(null);
					StateManager.setCurrentState(HANDLER.getGame().SETTINGS_STATE);
				}));
	}
}
