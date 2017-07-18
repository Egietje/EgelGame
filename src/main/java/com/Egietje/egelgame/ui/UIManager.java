package com.Egietje.egelgame.ui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import com.Egietje.egelgame.Handler;

public class UIManager {

	private Handler HANDLER;
	private ArrayList<UIObject> OBJECTS;

	public UIManager(Handler handler) {
		HANDLER = handler;
		OBJECTS = new ArrayList<>();
	}

	public void tick() {
		for (UIObject object : OBJECTS) {
			object.tick();
		}
	}

	public void render(Graphics graphics) {
		for (UIObject object : OBJECTS) {
			object.render(graphics);
		}
	}

	public void onMouseMove(MouseEvent e) {
		for (UIObject object : OBJECTS) {
			object.onMouseMove(e);
		}
	}

	public void onMouseRelease(MouseEvent e) {
		for (UIObject object : OBJECTS) {
			object.onMouseRelease(e);
		}
	}

	public void addObject(UIObject object) {
		OBJECTS.add(object);
	}

	public void removeObject(UIObject object) {
		OBJECTS.remove(object);
	}

	public Handler getHandler() {
		return HANDLER;
	}

	public void setHandler(Handler handler) {
		HANDLER = handler;
	}

	public ArrayList<UIObject> getObjects() {
		return OBJECTS;
	}

	public void setObjects(ArrayList<UIObject> objects) {
		OBJECTS = objects;
	}

}
