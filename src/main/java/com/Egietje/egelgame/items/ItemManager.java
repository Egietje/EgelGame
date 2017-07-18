package com.Egietje.egelgame.items;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import com.Egietje.egelgame.Handler;

public class ItemManager {

	private Handler HANDLER;
	private ArrayList<Item> ITEMS;

	public ItemManager(Handler handler) {
		HANDLER = handler;
		ITEMS = new ArrayList<>();
	}

	public void tick() {
		Iterator<Item> it = ITEMS.iterator();
		while (it.hasNext()) {
			Item item = it.next();
			item.tick();
			if (item.isPickedUp()) {
				it.remove();
			}
		}
	}

	public void render(Graphics graphics) {
		for (Item item : ITEMS) {
			item.render(graphics);
		}
	}

	public void addItem(Item item) {
		item.setHandler(HANDLER);
		ITEMS.add(item);
	}

	public Handler getHandler() {
		return HANDLER;
	}

	public void setHandler(Handler handler) {
		HANDLER = handler;
	}
}
