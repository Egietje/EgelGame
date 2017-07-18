package com.Egietje.egelgame.items;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.Egietje.egelgame.Handler;
import com.Egietje.egelgame.graphics.Assets;

public class Item {

	public static final Item[] ITEMS = new Item[256];
	public static final Item STONE = new Item(Assets.ITEM_STONE, "Stone", 0);
	public static final Item LOG = new Item(Assets.ITEM_LOG, "Log", 1);

	public static final int ITEM_WIDTH = 16, ITEM_HEIGHT = 16;

	protected Handler HANDLER;
	protected BufferedImage TEXTURE;
	protected String NAME;
	protected final int ID;
	protected Rectangle BOUNDS;
	protected int X, Y, COUNT;
	protected boolean PICK_UP;

	public Item(BufferedImage texture, String name, int id) {
		TEXTURE = texture;
		NAME = name;
		ID = id;
		BOUNDS = new Rectangle(X, Y, ITEM_WIDTH, ITEM_HEIGHT);
		COUNT = 1;
		if (ITEMS[id] == null) {
			ITEMS[id] = this;
		}
	}

	public void tick() {
		if (HANDLER.getWorld().getEntityManager().getPlayer().getCollisionBounds(0, 0).intersects(BOUNDS)) {
			PICK_UP = true;
			HANDLER.getWorld().getEntityManager().getPlayer().getInventory().addItem(this);
		}
	}

	public void render(Graphics graphics) {
		if (HANDLER != null) {
			render(graphics, (int) (X - HANDLER.getCamera().getXOffset()),
					(int) (Y - HANDLER.getCamera().getYOffset()));
		}
	}

	public void render(Graphics graphics, int x, int y) {
		graphics.drawImage(TEXTURE, x, y, ITEM_WIDTH, ITEM_HEIGHT, null);
	}

	public Item createNew(int count) {
		Item item = new Item(TEXTURE, NAME, ID);
		item.setPickedUp(true);
		item.setCount(count);
		return item;
	}

	public Item createNew(int x, int y) {
		Item item = new Item(TEXTURE, NAME, ID);
		item.setPosition(x, y);
		return item;
	}

	public void setPosition(int x, int y) {
		X = x;
		Y = y;
		BOUNDS.x = x;
		BOUNDS.y = y;
	}

	public Handler getHandler() {
		return HANDLER;
	}

	public void setHandler(Handler handler) {
		HANDLER = handler;
	}

	public BufferedImage getTexture() {
		return TEXTURE;
	}

	public void setTexture(BufferedImage texture) {
		TEXTURE = texture;
	}

	public String getName() {
		return NAME;
	}

	public Rectangle getBounds() {
		return BOUNDS;
	}

	public void setName(String name) {
		NAME = name;
	}

	public int getX() {
		return X;
	}

	public void setX(int x) {
		X = x;
	}

	public int getY() {
		return Y;
	}

	public void setY(int y) {
		Y = y;
	}

	public int getCount() {
		return COUNT;
	}

	public void setCount(int count) {
		COUNT = count;
	}

	public int getID() {
		return ID;
	}

	public boolean isPickedUp() {
		return PICK_UP;
	}

	public void setPickedUp(boolean pickedUp) {
		PICK_UP = pickedUp;
	}
}
