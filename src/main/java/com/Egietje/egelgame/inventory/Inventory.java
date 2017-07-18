package com.Egietje.egelgame.inventory;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;

import com.Egietje.egelgame.Handler;
import com.Egietje.egelgame.graphics.Assets;
import com.Egietje.egelgame.graphics.Text;
import com.Egietje.egelgame.items.Item;

public class Inventory {

	private Handler HANDLER;
	private boolean ACTIVE = false;
	private ArrayList<Item> INVENTORY_ITEMS;
	private int SELECTED_ITEM;
	private final int INV_X = 1280 / 2 - 512, INV_Y = 720 / 2 - 288;
	private final int INV_WIDTH = 1024, INV_HEIGHT = 576;
	private final int INV_LIST_CENTER_X = INV_WIDTH / 2 - 46, INV_LIST_CENTER_Y = INV_Y + INV_HEIGHT / 2 - 2;
	private final int INV_NORMAL_SPACING = 44, INV_BEGIN_SPACING = 48;
	private final int INV_ITEM_X = INV_X + INV_WIDTH - 218, INV_ITEM_Y = 138;
	private final int INV_ITEM_COUNT_X = INV_ITEM_X + 32, INV_ITEM_COUNT_Y = INV_ITEM_Y + 118;
	private final int INV_ITEM_WIDTH = 64, INV_ITEM_HEIGHT = 64;

	public Inventory(Handler handler) {
		HANDLER = handler;
		INVENTORY_ITEMS = new ArrayList<>();
	}

	public void tick() {
		Iterator<Item> it = INVENTORY_ITEMS.iterator();
		while (it.hasNext()) {
			Item item = it.next();
			if (item.getCount() <= 0) {
				it.remove();
			}
			if (item.getCount() > 64) {
				int count = item.getCount() - 64;
				item.setCount(64);
				Item i = Item.ITEMS[item.getID()];
				i.setCount(count);
				INVENTORY_ITEMS.add(i);
			}
		}
		if (HANDLER.getKeyManager().keyJustPressed(KeyEvent.VK_E)) {
			ACTIVE = !ACTIVE;
		}
		if (ACTIVE) {
			if (HANDLER.getKeyManager().keyJustPressed(KeyEvent.VK_UP)) {
				SELECTED_ITEM--;
			}
			if (HANDLER.getKeyManager().keyJustPressed(KeyEvent.VK_DOWN)) {
				SELECTED_ITEM++;
			}
			if (SELECTED_ITEM < 0) {
				SELECTED_ITEM = INVENTORY_ITEMS.size() - 1;
			} else if (SELECTED_ITEM >= INVENTORY_ITEMS.size()) {
				SELECTED_ITEM = 0;
			}
		}
	}

	public void render(Graphics graphics) {
		if (!ACTIVE) {
			return;
		}

		graphics.drawImage(Assets.INVENTORY_SCREEN, INV_X, INV_Y, INV_WIDTH, INV_HEIGHT, null);

		int length = INVENTORY_ITEMS.size();
		if (length == 0) {
			return;
		}

		for (int i = -5; i < 6; i++) {
			if (SELECTED_ITEM + i < 0 || SELECTED_ITEM + i >= length) {
				continue;
			}
			if (i == 0) {
				Text.drawString(graphics, "> " + INVENTORY_ITEMS.get(SELECTED_ITEM + i).getName() + " <",
						INV_LIST_CENTER_X, INV_LIST_CENTER_Y, true, Color.YELLOW, Assets.FONT32);
			} else if (i == 1 || i == -1) {
				Text.drawString(graphics, INVENTORY_ITEMS.get(SELECTED_ITEM + i).getName(), INV_LIST_CENTER_X,
						INV_LIST_CENTER_Y + i * INV_BEGIN_SPACING, true, Color.WHITE, Assets.FONT32);
			} else if (i > 0) {
				Text.drawString(graphics, INVENTORY_ITEMS.get(SELECTED_ITEM + i).getName(), INV_LIST_CENTER_X,
						INV_LIST_CENTER_Y + i * INV_NORMAL_SPACING + INV_BEGIN_SPACING - INV_NORMAL_SPACING, true,
						Color.WHITE, Assets.FONT32);
			} else {
				Text.drawString(graphics, INVENTORY_ITEMS.get(SELECTED_ITEM + i).getName(), INV_LIST_CENTER_X,
						INV_LIST_CENTER_Y + i * INV_NORMAL_SPACING - INV_BEGIN_SPACING + INV_NORMAL_SPACING, true,
						Color.WHITE, Assets.FONT32);
			}
		}

		Item item = INVENTORY_ITEMS.get(SELECTED_ITEM);
		graphics.drawImage(item.getTexture(), INV_ITEM_X, INV_ITEM_Y, INV_ITEM_WIDTH, INV_ITEM_HEIGHT, null);
		Text.drawString(graphics, Integer.toString(item.getCount()), INV_ITEM_COUNT_X, INV_ITEM_COUNT_Y, true,
				Color.WHITE, Assets.FONT32);
	}

	public void addItem(Item item) {
		for (Item i : INVENTORY_ITEMS) {
			if (i.getID() == item.getID()) {
				if (!(i.getCount() + item.getCount() > 64)) {
					i.setCount(i.getCount() + item.getCount());
					return;
				} else {
					continue;
				}
			}
		}
		INVENTORY_ITEMS.add(item);
	}

	public Handler getHandler() {
		return HANDLER;
	}

	public void setHandler(Handler handler) {
		HANDLER = handler;
	}

	public boolean isActive() {
		return ACTIVE;
	}
}
