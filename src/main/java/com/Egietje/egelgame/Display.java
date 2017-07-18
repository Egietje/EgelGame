package com.Egietje.egelgame;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Display {
	private JFrame FRAME;
	private Canvas CANVAS;
	private int HEIGHT, WIDTH;
	private String TITLE;

	public Display(String title, int height, int width) {
		TITLE = title;
		HEIGHT = height;
		WIDTH = width;

		createDisplay();
	}

	private void createDisplay() {
		FRAME = new JFrame();
		FRAME.setSize(WIDTH, HEIGHT);
		FRAME.setTitle(TITLE);
		FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		FRAME.setResizable(false);
		FRAME.setLocationRelativeTo(null);
		FRAME.setVisible(true);
		FRAME.setAutoRequestFocus(true);

		CANVAS = new Canvas();
		CANVAS.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		CANVAS.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		CANVAS.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		CANVAS.setFocusable(false);

		FRAME.add(CANVAS);
		FRAME.pack();
	}

	public Canvas getCanvas() {
		return CANVAS;
	}

	public JFrame getFrame() {
		return FRAME;
	}
}