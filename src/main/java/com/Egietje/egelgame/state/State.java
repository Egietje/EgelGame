package com.Egietje.egelgame.state;

import java.awt.Graphics;

import com.Egietje.egelgame.Handler;

public abstract class State {

	protected Handler HANDLER;

	public State(Handler handler) {
		HANDLER = handler;
	}

	public abstract void tick();

	public abstract void render(Graphics graphics);
	
	public abstract void onSet();

}
