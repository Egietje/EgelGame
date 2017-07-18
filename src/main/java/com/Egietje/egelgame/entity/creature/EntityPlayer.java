package com.Egietje.egelgame.entity.creature;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.Egietje.egelgame.Handler;
import com.Egietje.egelgame.entity.Entity;
import com.Egietje.egelgame.graphics.Animation;
import com.Egietje.egelgame.graphics.Assets;
import com.Egietje.egelgame.graphics.Text;
import com.Egietje.egelgame.inventory.Inventory;
import com.Egietje.egelgame.tile.Tile;

public class EntityPlayer extends EntityCreature {
	private Animation ANIM_LEFT, ANIM_RIGHT;
	private double LAST_ATTACK, ATTACK_COOLDOWN = 800, ATTACK_TIMER = ATTACK_COOLDOWN;
	private Inventory INVENTORY;
	private boolean WATCH_LEFT = false;

	public EntityPlayer(Handler handler, float x, float y) {
		super(handler, x, y, EntityCreature.DEFAULT_CREATURE_WIDTH, EntityCreature.DEFAULT_CREATURE_HEIGHT);
		COLLISION.x = 1 * 2;
		COLLISION.y = 9 * 2;
		COLLISION.width = 14 * 2 - 1;
		COLLISION.height = 6 * 2 - 1;
		ANIM_LEFT = new Animation(200, Assets.ENTITY_EGIE_LEFT_ANIM);
		ANIM_RIGHT = new Animation(200, Assets.ENTITY_EGIE_RIGHT_ANIM);
		INVENTORY = new Inventory(handler);
	}

	@Override
	public void tick() {
		ANIM_LEFT.tick();
		ANIM_RIGHT.tick();
		getInput();
		move();
		HANDLER.getCamera().centerOnEntity(this);
		checkAttack();
		INVENTORY.tick();
	}

	private void checkAttack() {
		ATTACK_TIMER += System.currentTimeMillis() - LAST_ATTACK;
		LAST_ATTACK = System.currentTimeMillis();
		Rectangle cb = getCollisionBounds(0F, 0F);
		Rectangle ar = new Rectangle();
		ar.x = cb.x - 6;
		ar.y = cb.y - 14;
		ar.width = cb.width * 14 / 10;
		ar.height = cb.height * 10 / 3;
		if (!INVENTORY.isActive()) {
			if (ATTACK_TIMER >= ATTACK_COOLDOWN) {
				if (HANDLER.getKeyManager().ATTACK) {
					ATTACK_TIMER = 0;
					for (Entity entity : HANDLER.getWorld().getEntityManager().getEntities()) {
						if (!entity.equals(this)) {
							if (entity.getCollisionBounds(0F, 0F).intersects(ar)) {
								entity.hurt(1);
							}
						}
					}
				}
			}
		}
	}

	@Override
	public void die() {
		System.out.println("You lost!");
	}

	private void getInput() {
		X_MOVEMENT = 0;
		Y_MOVEMENT = 0;

		if (!INVENTORY.isActive() && !HANDLER.getKeyManager().ATTACK) {
			if (HANDLER.getKeyManager().UP) {
				Y_MOVEMENT -= SPEED;
			}
			if (HANDLER.getKeyManager().DOWN) {
				Y_MOVEMENT += SPEED;
			}
			if (HANDLER.getKeyManager().LEFT) {
				X_MOVEMENT -= SPEED;
				WATCH_LEFT = true;
			}
			if (HANDLER.getKeyManager().RIGHT) {
				X_MOVEMENT += SPEED;
				WATCH_LEFT = false;
			}
		}
	}

	@Override
	public void render(Graphics graphics) {
		if (!HANDLER.getKeyManager().ATTACK) {
			graphics.drawImage(getCurrentFrame(), (int) (X - HANDLER.getCamera().getXOffset()),
					(int) (Y - HANDLER.getCamera().getYOffset()), WIDTH, HEIGHT, null);
		} else {
			graphics.drawImage(Assets.ENTITY_EGIE_BALL,
					(int) (X - HANDLER.getCamera().getXOffset() - Tile.TILE_WIDTH / 2),
					(int) (Y - HANDLER.getCamera().getYOffset() - Tile.TILE_HEIGHT / 2), WIDTH * 2, HEIGHT * 2, null);
		}
		double seconds = -ATTACK_TIMER + ATTACK_COOLDOWN;
		if (seconds < 0) {
			seconds = 0;
		}
		double sec = (int) (seconds / 100);
		Text.drawString(graphics, sec / 10 + " seconds left", HANDLER.getWidth() / 2, 20, true, Color.WHITE,
				Assets.FONT32);
	}

	public void postRender(Graphics graphics) {
		INVENTORY.render(graphics);
	}

	private BufferedImage getCurrentFrame() {
		if (WATCH_LEFT && (X_MOVEMENT < 0 || Y_MOVEMENT != 0)) {
			return ANIM_LEFT.getCurrentFrame();
		} else if (!WATCH_LEFT && (X_MOVEMENT > 0 || Y_MOVEMENT != 0)) {
			return ANIM_RIGHT.getCurrentFrame();
		} else if (WATCH_LEFT && X_MOVEMENT == 0 && Y_MOVEMENT == 0) {
			return Assets.ENTITY_EGIE_LEFT;
		} else {
			return Assets.ENTITY_EGIE_RIGHT;
		}
	}

	public Inventory getInventory() {
		return INVENTORY;
	}

	public void setInventory(Inventory inventory) {
		INVENTORY = inventory;
	}
}
