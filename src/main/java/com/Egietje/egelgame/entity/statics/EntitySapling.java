package com.Egietje.egelgame.entity.statics;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.Egietje.egelgame.Handler;
import com.Egietje.egelgame.graphics.Assets;
import com.Egietje.egelgame.items.Item;
import com.Egietje.egelgame.tile.Tile;

public class EntitySapling extends EntityStatic {
	private int SPEED;
	private long LAST_TIME, TIMER;

	public EntitySapling(Handler handler, float x, float y, int speed) {
		super(handler, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);
		COLLISION.x = 4 * 2;
		COLLISION.y = 13 * 2;
		COLLISION.width = 6 * 2;
		COLLISION.height = 2 * 2;
		HEALTH = 1;
		SPEED = speed;
		TIMER = 0;
		LAST_TIME = System.currentTimeMillis();
	}

	@Override
	public void tick() {
		TIMER += System.currentTimeMillis() - LAST_TIME;
		LAST_TIME = System.currentTimeMillis();
		if (TIMER >= SPEED) {
			TIMER = 0;
			EntityTree entityTree = new EntityTree(HANDLER, X, Y - Tile.TILE_HEIGHT * 2);
			HANDLER.getWorld().getEntityManager().removeEntity(this);
			if (HANDLER.getWorld().canSpawn(entityTree.getCollisionBounds(0, 0), true, true, true)) {
				HANDLER.getWorld().getEntityManager().addEntity(entityTree);
			} else {
				HANDLER.getWorld().getEntityManager().addEntity(this);
			}
		}
	}

	@Override
	public void render(Graphics graphics) {
		graphics.drawImage(Assets.ENTITY_SAPLING, (int) (X - HANDLER.getCamera().getXOffset()),
				(int) (Y - HANDLER.getCamera().getYOffset()), WIDTH, HEIGHT, null);
	}

	@Override
	public void die() {
		Rectangle col = getCollisionBounds(0, 0);
		if (HANDLER.getWorld().canSpawn(col, true, false, false)) {
			Random random = new Random();
			HANDLER.getWorld().getItemManager()
					.addItem(Item.LOG.createNew((int) (col.x + col.width * random.nextDouble() - Item.ITEM_WIDTH / 2),
							(int) (col.y + col.height * random.nextDouble() - Item.ITEM_HEIGHT / 2)));
		}
	}
}
