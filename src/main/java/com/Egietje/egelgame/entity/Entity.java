package com.Egietje.egelgame.entity;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.Egietje.egelgame.Handler;

public abstract class Entity {
	public static final int DEFAULT_HEALTH = 10;
	protected Handler HANDLER;
	protected float X, Y;
	protected int WIDTH, HEIGHT;
	protected int HEALTH;
	protected boolean ACTIVE = true;
	protected Rectangle COLLISION;

	public Entity(Handler handler, float x, float y, int width, int height) {
		HANDLER = handler;
		X = x;
		Y = y;
		WIDTH = width;
		HEIGHT = height;
		HEALTH = DEFAULT_HEALTH;
		COLLISION = new Rectangle(0, 0, width, height);
	}

	public abstract void tick();

	public abstract void render(Graphics graphics);

	public abstract void die();

	public void hurt(int amount) {
		HEALTH -= amount;
		if (HEALTH <= 0) {
			ACTIVE = false;
			die();
		}
	}

	public boolean checkEntityCollisions(float xOffset, float yOffset) {
		for (Entity entity : HANDLER.getWorld().getEntityManager().getEntities()) {
			if (!entity.equals(this)) {
				if (entity.getCollisionBounds(0F, 0F).intersects(getCollisionBounds(xOffset, yOffset))) {
					return true;
				}
			}
		}
		return false;
	}

	public Rectangle getCollisionBounds(float xOffset, float yOffset) {
		return new Rectangle((int) (X + COLLISION.x + xOffset), (int) (Y + COLLISION.y + yOffset), COLLISION.width,
				COLLISION.height);
	}

	public float getX() {
		return X;
	}

	public void setX(float x) {
		X = x;
	}

	public float getY() {
		return Y;
	}

	public void setY(float y) {
		Y = y;
	}

	public int getWidth() {
		return WIDTH;
	}

	public void setWidth(int width) {
		WIDTH = width;
	}

	public int getHeight() {
		return HEIGHT;
	}

	public void setHeight(int height) {
		HEIGHT = height;
	}

	public int getHealth() {
		return HEALTH;
	}

	public void setHealth(int health) {
		HEALTH = health;
	}

	public boolean isActive() {
		return ACTIVE;
	}

	public void setActive(boolean active) {
		ACTIVE = active;
	}
}
