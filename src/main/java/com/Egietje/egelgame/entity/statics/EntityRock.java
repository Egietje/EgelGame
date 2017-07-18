package com.Egietje.egelgame.entity.statics;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.Egietje.egelgame.Handler;
import com.Egietje.egelgame.graphics.Assets;
import com.Egietje.egelgame.items.Item;
import com.Egietje.egelgame.tile.Tile;

public class EntityRock extends EntityStatic {

	public EntityRock(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);
		COLLISION.x = 1 * 2;
		COLLISION.y = 10 * 2;
		COLLISION.width = 13 * 2;
		COLLISION.height = 5 * 2;
	}

	@Override
	public void tick() {
	}

	@Override
	public void die() {
		Rectangle col = getCollisionBounds(0, 0);
		if (HANDLER.getWorld().canSpawn(col, true, false, false)) {
			Random random = new Random();
			HANDLER.getWorld().getItemManager()
					.addItem(Item.STONE.createNew((int) (col.x + col.width * random.nextDouble() - Item.ITEM_WIDTH / 2),
							(int) (col.y + col.height * random.nextDouble() - Item.ITEM_HEIGHT / 2)));
		}
	}

	@Override
	public void render(Graphics graphics) {
		graphics.drawImage(Assets.ENTITY_ROCK, (int) (X - HANDLER.getCamera().getXOffset()),
				(int) (Y - HANDLER.getCamera().getYOffset()), WIDTH, HEIGHT, null);
	}
}
