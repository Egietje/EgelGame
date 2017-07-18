package com.Egietje.egelgame.world;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.Egietje.egelgame.Handler;
import com.Egietje.egelgame.entity.*;
import com.Egietje.egelgame.entity.creature.*;
import com.Egietje.egelgame.entity.statics.*;
import com.Egietje.egelgame.graphics.Assets;
import com.Egietje.egelgame.items.ItemManager;
import com.Egietje.egelgame.tile.Tile;
import com.Egietje.egelgame.utils.Utils;

public class World {
	private Handler HANDLER;
	private int WIDTH, HEIGHT;
	private int SPAWN_X, SPAWN_Y;
	private int[][] TILES;
	private EntityManager ENTITY_MANAGER;
	private ItemManager ITEM_MANAGER;
	private int SEED;

	public World(Handler handler, String name) {
		HANDLER = handler;
		ENTITY_MANAGER = new EntityManager(handler, new EntityPlayer(handler, 0, 0));
		ITEM_MANAGER = new ItemManager(handler);
		loadWorld(name);
		addEntities();
		ENTITY_MANAGER.getPlayer().setX(SPAWN_X);
		ENTITY_MANAGER.getPlayer().setY(SPAWN_Y);
	}

	public void tick() {
		for (int y = 0; y < HEIGHT; y++) {
			for (int x = 0; x < WIDTH; x++) {
				getTile(x, y).tick();
			}
		}
		Assets.ANIM_WATER.tick();
		ITEM_MANAGER.tick();
		ENTITY_MANAGER.tick();
	}

	public void render(Graphics graphics) {
		int xStart = (int) Math.max(0, HANDLER.getCamera().getXOffset() / Tile.TILE_WIDTH);
		int xEnd = (int) Math.min(WIDTH, (HANDLER.getCamera().getXOffset() + HANDLER.getWidth()) / Tile.TILE_WIDTH + 1);
		int yStart = (int) Math.max(0, HANDLER.getCamera().getYOffset() / Tile.TILE_HEIGHT);
		int yEnd = (int) Math.min(HEIGHT,
				(HANDLER.getCamera().getYOffset() + HANDLER.getHeight()) / Tile.TILE_HEIGHT + 1);
		for (int y = yStart; y < yEnd; y++) {
			for (int x = xStart; x < xEnd; x++) {
				getTile(x, y).render(graphics, (int) (x * Tile.TILE_WIDTH - HANDLER.getCamera().getXOffset()),
						(int) (y * Tile.TILE_HEIGHT - HANDLER.getCamera().getYOffset()));
			}
		}
		ITEM_MANAGER.render(graphics);
		ENTITY_MANAGER.render(graphics);
	}

	public Tile getTile(int x, int y) {
		if (x >= 0 && y >= 0 && x < WIDTH && y < HEIGHT) {
			Tile tile = Tile.TILES[TILES[x][y]];
			if (tile != null) {
				return tile;
			} else {
				return Tile.GRASS_TILE;
			}
		}
		return Tile.WATER_TILE;
	}

	private void addEntities() {
		for (int x = 0; x < WIDTH; x++) {
			for (int y = 0; y < HEIGHT; y++) {
				Random random = new Random(SEED + x + y * WIDTH);
				EntityStatic entityRock = new EntityRock(HANDLER, x * Tile.TILE_WIDTH, y * Tile.TILE_HEIGHT);
				EntityStatic entityTree = new EntityTree(HANDLER, x * Tile.TILE_WIDTH, y * Tile.TILE_HEIGHT);
				EntityStatic entityRedFlower = new EntityRedFlower(HANDLER, x * Tile.TILE_WIDTH, y * Tile.TILE_HEIGHT);
				EntityStatic entityYellowFlower = new EntityYellowFlower(HANDLER, x * Tile.TILE_WIDTH,
						y * Tile.TILE_HEIGHT);
				EntityStatic entitySapling = new EntitySapling(HANDLER, x * Tile.TILE_WIDTH, y * Tile.TILE_HEIGHT,
						10000 * (random.nextInt(6) + 1) + random.nextInt(60000));
				if (random.nextInt(30) == 0 && canSpawn(entityRock.getCollisionBounds(0, 0), true, true, false)) {
					ENTITY_MANAGER.addEntity(entityRock);
				} else if (random.nextInt(100) == 0
						&& canSpawn(entityTree.getCollisionBounds(0, 0), true, true, true)) {
					ENTITY_MANAGER.addEntity(entityTree);
				} else if (random.nextInt(30) == 0
						&& canSpawn(entityRedFlower.getCollisionBounds(0, 0), true, true, true)) {
					ENTITY_MANAGER.addEntity(entityRedFlower);
				} else if (random.nextInt(30) == 0
						&& canSpawn(entityYellowFlower.getCollisionBounds(0, 0), true, true, true)) {
					ENTITY_MANAGER.addEntity(entityYellowFlower);
				} else if (random.nextInt(50) == 0
						&& canSpawn(entitySapling.getCollisionBounds(0, 0), true, true, true)) {
					ENTITY_MANAGER.addEntity(entitySapling);
				}
			}
		}
	}

	public boolean checkSolid(Rectangle bounds) {
		for (int width = 0; width < bounds.width; width++) {
			for (int height = 0; height < bounds.height; height++) {
				int x = width;
				int y = height;
				while (x > 1.0) {
					x--;
				}
				while (y > 1.0) {
					y--;
				}
				x += bounds.x / Tile.TILE_WIDTH + width / Tile.TILE_WIDTH;
				y += bounds.y / Tile.TILE_HEIGHT + height / Tile.TILE_HEIGHT;
				if (getTile(x, y).isSolid()) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean checkIntersect(Rectangle bounds) {
		for (int width = 0; width < bounds.width; width++) {
			for (int height = 0; height < bounds.height; height++) {
				int x = width;
				int y = height;
				while (x > 1.0) {
					x--;
				}
				while (y > 1.0) {
					y--;
				}
				x += bounds.x / Tile.TILE_WIDTH + width / Tile.TILE_WIDTH;
				y += bounds.y / Tile.TILE_HEIGHT + height / Tile.TILE_HEIGHT;
				for (Entity e : ENTITY_MANAGER.getEntities()) {
					if (e.getCollisionBounds(0, 0).intersects(bounds) && e != ENTITY_MANAGER.getPlayer()) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean checkSustain(Rectangle bounds) {
		for (int width = 0; width < bounds.width; width++) {
			for (int height = 0; height < bounds.height; height++) {
				int x = width;
				int y = height;
				while (x > 1.0) {
					x--;
				}
				while (y > 1.0) {
					y--;
				}
				x += bounds.x / Tile.TILE_WIDTH + width / Tile.TILE_WIDTH;
				y += bounds.y / Tile.TILE_HEIGHT + height / Tile.TILE_HEIGHT;
				if (!getTile(x, y).canSustainPlants()) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean checkPath(Rectangle bounds) {
		for (int width = 0; width < bounds.width; width++) {
			for (int height = 0; height < bounds.height; height++) {
				int x = width;
				int y = height;
				while (x > 1.0) {
					x--;
				}
				while (y > 1.0) {
					y--;
				}
				x += bounds.x / Tile.TILE_WIDTH + width / Tile.TILE_WIDTH;
				y += bounds.y / Tile.TILE_HEIGHT + height / Tile.TILE_HEIGHT;
				if (getTile(x, y) == Tile.PATH_TILE) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean canSpawn(Rectangle bounds, boolean checkSolid, boolean checkIntersect, boolean checkSustain) {
		if (checkSolid && checkSolid(bounds)) {
			return false;
		}
		if (checkIntersect && checkIntersect(bounds)) {
			return false;
		}
		if (checkSustain && checkSustain(bounds)) {
			return false;
		}
		if (checkPath(bounds)) {
			return false;
		}
		return true;
	}

	private void loadWorld(String name) {
		String file = Utils.loadFileAsString("src/main/resources/worlds/" + name + ".world");
		String[] tokens = file.split("\\s+");
		WIDTH = Utils.parseInt(tokens[0]);
		HEIGHT = Utils.parseInt(tokens[1]);
		SPAWN_X = Utils.parseInt(tokens[2]) * Tile.TILE_WIDTH;
		SPAWN_Y = Utils.parseInt(tokens[3]) * Tile.TILE_HEIGHT;
		SEED = Utils.parseInt(tokens[4]);
		TILES = new int[WIDTH][HEIGHT];
		if (WIDTH * Tile.TILE_WIDTH < HANDLER.getWidth() || HEIGHT * Tile.TILE_HEIGHT < HANDLER.getHeight()) {
			System.err.println("ERR: " + name + " is too small.");
			throw new IllegalArgumentException(name + " is too small.");
		}
		for (int y = 0; y < HEIGHT; y++) {
			for (int x = 0; x < WIDTH; x++) {
				TILES[x][y] = Utils.parseInt(tokens[x + y * WIDTH + 5]);
			}
		}
	}

	public int getWidth() {
		return WIDTH;
	}

	public int getHeight() {
		return HEIGHT;
	}

	public EntityManager getEntityManager() {
		return ENTITY_MANAGER;
	}

	public Handler getHandler() {
		return HANDLER;
	}

	public void setHandler(Handler handler) {
		HANDLER = handler;
	}

	public ItemManager getItemManager() {
		return ITEM_MANAGER;
	}

	public void setItemManager(ItemManager itemManager) {
		ITEM_MANAGER = itemManager;
	}
}
