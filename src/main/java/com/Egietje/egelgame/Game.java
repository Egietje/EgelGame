package com.Egietje.egelgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.*;

import com.Egietje.egelgame.graphics.Assets;
import com.Egietje.egelgame.graphics.GameCamera;
import com.Egietje.egelgame.graphics.Text;
import com.Egietje.egelgame.input.*;
import com.Egietje.egelgame.state.*;

public class Game implements Runnable {
	private int WIDTH, HEIGHT;
	public String TITLE;
	private boolean RUNNING = false;
	private Thread THREAD;
	private Display DISPLAY;
	private BufferStrategy BUFFER_STRATEGY;
	private Graphics GRAPHICS;
	public State GAME_STATE;
	public State MENU_STATE;
	public State SETTINGS_STATE;
	private KeyManager KEY_MANAGER;
	private MouseManager MOUSE_MANAGER;
	private GameCamera CAMERA;
	private Handler HANDLER;

	public Game(String title, int height) {
		TITLE = title;
		HEIGHT = height;
		WIDTH = height / 9 * 16;
		KEY_MANAGER = new KeyManager();
		MOUSE_MANAGER = new MouseManager();
	}

	private void init() {
		DISPLAY = new Display(TITLE, HEIGHT, WIDTH);
		DISPLAY.getFrame().addKeyListener(KEY_MANAGER);
		DISPLAY.getFrame().addMouseListener(MOUSE_MANAGER);
		DISPLAY.getFrame().addMouseMotionListener(MOUSE_MANAGER);
		DISPLAY.getCanvas().addKeyListener(KEY_MANAGER);
		DISPLAY.getCanvas().addMouseListener(MOUSE_MANAGER);
		DISPLAY.getCanvas().addMouseMotionListener(MOUSE_MANAGER);
		Assets.loadArrays();
		HANDLER = new Handler(this);
		CAMERA = new GameCamera(HANDLER, 0, 0);
		GAME_STATE = new GameState(HANDLER);
		MENU_STATE = new MenuState(HANDLER);
		SETTINGS_STATE = new SettingsState(HANDLER);
		StateManager.setCurrentState(MENU_STATE);
	}

	private void tick() {
		KEY_MANAGER.tick();

		if (StateManager.getCurrentState() != null) {
			StateManager.getCurrentState().tick();
		}
	}

	private void render() {
		if (DISPLAY.getCanvas().getBufferStrategy() != null) {
			BUFFER_STRATEGY = DISPLAY.getCanvas().getBufferStrategy();
			GRAPHICS = BUFFER_STRATEGY.getDrawGraphics();
			GRAPHICS.clearRect(0, 0, WIDTH, HEIGHT);

			if (StateManager.getCurrentState() != null) {
				StateManager.getCurrentState().render(GRAPHICS);
			}

			Text.drawString(GRAPHICS, FPS + "FPS | " + UPS + "UPS", 0, 24, false, Color.WHITE, Assets.FONT32);

			BUFFER_STRATEGY.show();
			GRAPHICS.dispose();
		} else {
			DISPLAY.getCanvas().createBufferStrategy(3);
		}
	}

	private int FPS = 0;
	private int UPS = 0;

	@Override
	public void run() {
		init();

		final int maxUps = 60;
		final double timePerTick = 1000000000 / maxUps;
		final int maxFps = 120;
		final double timePerRender = 1000000000 / maxFps;
		double deltaT = 0;
		double deltaR = 0;
		long now = 0;
		long lastTime = System.nanoTime();
		long timer = 0;
		int fps = 0;
		int ups = 0;

		while (RUNNING) {
			now = System.nanoTime();
			deltaT += (now - lastTime) / timePerTick;
			deltaR += (now - lastTime) / timePerRender;
			timer += now - lastTime;
			lastTime = now;

			if (deltaT >= 1) {
				tick();
				fps++;
				deltaT--;
			}

			if (deltaR >= 1) {
				render();
				ups++;
				deltaR--;
			}

			if (timer >= 1000000000) {
				FPS = fps;
				fps = 0;
				UPS = ups;
				ups = 0;
				timer = 0;
			}
		}

		stop();
	}

	public KeyManager getKeyManager() {
		return KEY_MANAGER;
	}

	public MouseManager getMouseManager() {
		return MOUSE_MANAGER;
	}

	public GameCamera getCamera() {
		return CAMERA;
	}

	public int getWidth() {
		return WIDTH;
	}

	public int getHeight() {
		return HEIGHT;
	}

	public synchronized void start() {
		if (!RUNNING) {
			RUNNING = true;
			THREAD = new Thread(this);
			THREAD.setName(TITLE);
			THREAD.start();
		}
	}

	public synchronized void stop() {
		if (RUNNING) {
			try {
				RUNNING = false;
				THREAD.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
				System.exit(0);
			}
		}
	}
}