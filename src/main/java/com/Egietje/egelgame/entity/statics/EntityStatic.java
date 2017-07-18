package com.Egietje.egelgame.entity.statics;

import com.Egietje.egelgame.Handler;
import com.Egietje.egelgame.entity.Entity;

public abstract class EntityStatic extends Entity {
	public EntityStatic(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
	}
}
