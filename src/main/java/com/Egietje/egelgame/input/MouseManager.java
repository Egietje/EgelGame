package com.Egietje.egelgame.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.Egietje.egelgame.ui.UIManager;

public class MouseManager implements MouseListener, MouseMotionListener {

	private boolean LEFT, RIGHT;
	private int MOUSE_X, MOUSE_Y;
	private UIManager UI_MANAGER;

	public MouseManager() {
	}

	public void setManager(UIManager manager) {
		UI_MANAGER = manager;
	}

	public boolean isLeftPressed() {
		return LEFT;
	}

	public boolean isRightPressed() {
		return RIGHT;
	}

	public int getMouseX() {
		return MOUSE_X;
	}

	public int getMouseY() {
		return MOUSE_Y;
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		MOUSE_X = e.getX();
		MOUSE_X = e.getY();
		if (UI_MANAGER != null) {
			UI_MANAGER.onMouseMove(e);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			LEFT = true;
		}
		if (e.getButton() == MouseEvent.BUTTON3) {
			RIGHT = true;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			LEFT = false;
		}
		if (e.getButton() == MouseEvent.BUTTON3) {
			RIGHT = false;
		}
		if (UI_MANAGER != null) {
			UI_MANAGER.onMouseRelease(e);
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
}
