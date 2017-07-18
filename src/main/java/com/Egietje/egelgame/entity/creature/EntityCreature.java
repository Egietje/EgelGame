package com.Egietje.egelgame.entity.creature;

import com.Egietje.egelgame.Handler;
import com.Egietje.egelgame.entity.Entity;
import com.Egietje.egelgame.tile.Tile;

public abstract class EntityCreature extends Entity {
	public static final float DEFAULT_SPEED = 1.5F;
	public static final int DEFAULT_CREATURE_WIDTH = 32;
	public static final int DEFAULT_CREATURE_HEIGHT = 32;
	protected float SPEED;
	protected float X_MOVEMENT, Y_MOVEMENT;

	public EntityCreature(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
		SPEED = DEFAULT_SPEED;
		X_MOVEMENT = 0;
		Y_MOVEMENT = 0;
	}

	public void move() {
		if (!checkEntityCollisions(X_MOVEMENT, 0F)) {
			moveX();
		}
		if (!checkEntityCollisions(0F, Y_MOVEMENT)) {
			moveY();
		}
	}

	public void moveX() {
		if (X_MOVEMENT > 0) {
			int tx = (int) (X + X_MOVEMENT + COLLISION.x + COLLISION.width) / Tile.TILE_WIDTH;
			if (!collisionWithTile(tx, (int) (Y + COLLISION.y) / Tile.TILE_HEIGHT)
					&& !collisionWithTile(tx, (int) (Y + COLLISION.y + COLLISION.height) / Tile.TILE_HEIGHT)) {
				X += X_MOVEMENT;
			} else {
				X = tx * Tile.TILE_WIDTH - COLLISION.x - COLLISION.width - 1;
			}
		} else if (X_MOVEMENT < 0) {
			int tx = (int) (X + X_MOVEMENT + COLLISION.x) / Tile.TILE_WIDTH;
			if (!collisionWithTile(tx, (int) (Y + COLLISION.y) / Tile.TILE_HEIGHT)
					&& !collisionWithTile(tx, (int) (Y + COLLISION.y + COLLISION.height) / Tile.TILE_HEIGHT)) {
				X += X_MOVEMENT;
			} else {
				X = tx * Tile.TILE_WIDTH + Tile.TILE_WIDTH - COLLISION.x;
			}
		}
	}

	public void moveY() {
		if (Y_MOVEMENT > 0) {
			int ty = (int) (Y + Y_MOVEMENT + COLLISION.y + COLLISION.height) / Tile.TILE_HEIGHT;
			if (!collisionWithTile((int) (X + COLLISION.x) / Tile.TILE_WIDTH, ty)
					&& !collisionWithTile((int) (X + COLLISION.x + COLLISION.width) / Tile.TILE_WIDTH, ty)) {
				Y += Y_MOVEMENT;
			} else {
				Y = ty * Tile.TILE_HEIGHT - COLLISION.y - COLLISION.height - 1;
			}
		} else if (Y_MOVEMENT < 0) {
			int ty = (int) (Y + Y_MOVEMENT + COLLISION.y) / Tile.TILE_HEIGHT;
			if (!collisionWithTile((int) (X + COLLISION.x) / Tile.TILE_WIDTH, ty)
					&& !collisionWithTile((int) (X + COLLISION.x + COLLISION.width) / Tile.TILE_WIDTH, ty)) {
				Y += Y_MOVEMENT;
			} else {
				Y = ty * Tile.TILE_HEIGHT + Tile.TILE_HEIGHT - COLLISION.y;
			}
		}
	}

	protected boolean collisionWithTile(int x, int y) {
		return HANDLER.getWorld().getTile(x, y).isSolid();
	}

	public float getSpeed() {
		return SPEED;
	}

	public void setSpeed(float speed) {
		SPEED = speed;
	}

	public float getXMovement() {
		return X_MOVEMENT;
	}

	public void setXMovement(float xMovement) {
		X_MOVEMENT = xMovement;
	}

	public float getYMovement() {
		return Y_MOVEMENT;
	}

	public void setYMovement(float yMovement) {
		Y_MOVEMENT = yMovement;
	}
}
