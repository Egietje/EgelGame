package com.Egietje.egelgame.state;

public class StateManager {
	private static State CURRENT_STATE = null;

	public static void setCurrentState(State state) {
		CURRENT_STATE = state;
		state.onSet();
	}

	public static State getCurrentState() {
		return CURRENT_STATE;
	}
}
