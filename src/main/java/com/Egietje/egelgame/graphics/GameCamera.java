package com.Egietje.egelgame.graphics;

import com.Egietje.egelgame.Handler;
import com.Egietje.egelgame.entity.Entity;
import com.Egietje.egelgame.tile.Tile;

public class GameCamera {
	private Handler HANDLER;
	private float X_OFFSET, Y_OFFSET;

	public GameCamera(Handler handler, float xOffset, float yOffset) {
		HANDLER = handler;
		X_OFFSET = xOffset;
		Y_OFFSET = yOffset;
	}

	public void checkOutOfMap() {
		if (X_OFFSET < 0) {
			X_OFFSET = 0;
		} else if (X_OFFSET > HANDLER.getWorld().getWidth() * Tile.TILE_WIDTH - HANDLER.getWidth()) {
			X_OFFSET = HANDLER.getWorld().getWidth() * Tile.TILE_WIDTH - HANDLER.getWidth();
		}
		if (Y_OFFSET < 0) {
			Y_OFFSET = 0;
		} else if (Y_OFFSET > HANDLER.getWorld().getHeight() * Tile.TILE_HEIGHT - HANDLER.getHeight()) {
			Y_OFFSET = HANDLER.getWorld().getHeight() * Tile.TILE_HEIGHT - HANDLER.getHeight();
		}
	}

	public void centerOnEntity(Entity entity) {
		X_OFFSET = entity.getX() - HANDLER.getWidth() / 2 + entity.getWidth() / 2;
		Y_OFFSET = entity.getY() - HANDLER.getHeight() / 2 + entity.getHeight() / 2;
		checkOutOfMap();
	}

	public void move(float xAmount, float yAmount) {
		X_OFFSET += xAmount;
		Y_OFFSET += yAmount;
		checkOutOfMap();
	}

	public float getXOffset() {
		return X_OFFSET;
	}

	public void setXOffset(float xOffset) {
		X_OFFSET = xOffset;
	}

	public float getYOffset() {
		return Y_OFFSET;
	}

	public void setYOffset(float yOffset) {
		Y_OFFSET = yOffset;
	}
}
