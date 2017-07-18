package com.Egietje.egelgame.entity.statics;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.Egietje.egelgame.Handler;
import com.Egietje.egelgame.graphics.Assets;
import com.Egietje.egelgame.items.Item;
import com.Egietje.egelgame.tile.Tile;

public class EntityTree extends EntityStatic {

	public EntityTree(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILE_WIDTH * 2, Tile.TILE_HEIGHT * 3);
		COLLISION.x = 9 * 2;
		COLLISION.y = 40 * 2;
		COLLISION.width = 11 * 2;
		COLLISION.height = 6 * 2;
	}

	@Override
	public void tick() {
	}

	@Override
	public void die() {
		Rectangle col = getCollisionBounds(0, 0);
		if (HANDLER.getWorld().canSpawn(col, true, false, false)) {
			Random random = new Random();
			for (int i = 0; i < 2 + random.nextInt(3); i++) {
				HANDLER.getWorld().getItemManager()
						.addItem(Item.LOG.createNew(
								(int) (col.x + col.width * random.nextDouble() - Item.ITEM_WIDTH / 2),
								(int) (col.y + col.height * random.nextDouble() - Item.ITEM_HEIGHT / 2)));
			}
		}
	}

	@Override
	public void render(Graphics graphics) {
		graphics.drawImage(Assets.ENTITY_TREE, (int) (X - HANDLER.getCamera().getXOffset()),
				(int) (Y - HANDLER.getCamera().getYOffset()), WIDTH, HEIGHT, null);
	}
}
