package com.Egietje.egelgame.entity.statics;

import java.awt.Graphics;

import com.Egietje.egelgame.Handler;
import com.Egietje.egelgame.graphics.Assets;
import com.Egietje.egelgame.tile.Tile;

public class EntityYellowFlower extends EntityStatic {

	public EntityYellowFlower(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);
		COLLISION.x = 8 * 2;
		COLLISION.y = 14 * 2;
		COLLISION.width = 2 * 2;
		COLLISION.height = 1 * 2;
		HEALTH = 1;
	}

	@Override
	public void tick() {
	}

	@Override
	public void render(Graphics graphics) {
		graphics.drawImage(Assets.ENTITY_YELLOW_FLOWER, (int) (X - HANDLER.getCamera().getXOffset()),
				(int) (Y - HANDLER.getCamera().getYOffset()), WIDTH, HEIGHT, null);
	}

	@Override
	public void die() {
	}

}
