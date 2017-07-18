package com.Egietje.egelgame.entity.statics;

import java.awt.Graphics;

import com.Egietje.egelgame.Handler;
import com.Egietje.egelgame.graphics.Assets;
import com.Egietje.egelgame.tile.Tile;

public class EntityRedFlower extends EntityStatic {

	public EntityRedFlower(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);
		COLLISION.x = 6 * 2;
		COLLISION.y = 13 * 2;
		COLLISION.width = 2 * 2;
		COLLISION.height = 1 * 2;
		HEALTH = 1;
	}

	@Override
	public void tick() {
	}

	@Override
	public void render(Graphics graphics) {
		graphics.drawImage(Assets.ENTITY_RED_FLOWER, (int) (X - HANDLER.getCamera().getXOffset()),
				(int) (Y - HANDLER.getCamera().getYOffset()), WIDTH, HEIGHT, null);
	}

	@Override
	public void die() {
	}

}
