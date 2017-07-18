package com.Egietje.egelgame.tile;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.Egietje.egelgame.graphics.Animation;
import com.Egietje.egelgame.graphics.Assets;

public class Tile {
	public static final Tile[] TILES = new Tile[256];
	public static final Tile GRASS_TILE = new Tile(Assets.TILE_GRASS, "grass", 0, false, true);
	public static final Tile SAND_TILE = new Tile(Assets.TILE_SAND, "sand", 1, false, false);
	public static final Tile PATH_TILE = new Tile(Assets.TILE_PATH, "path", 2, false, false);
	public static final Tile WATER_TILE = new Tile(Assets.ANIM_WATER, "water", 3, true, false);
	public static final Tile WOOD_TILE = new Tile(Assets.TILE_WOOD, "wood", 4, true, false);
	public static final Tile STONE_TILE = new Tile(Assets.TILE_STONE, "stone", 5, true, false);
	public static final int TILE_WIDTH = 32, TILE_HEIGHT = 32;
	protected BufferedImage TEXTURE;
	protected Animation ANIMATION;
	protected final int ID;
	protected boolean SOLID;
	protected boolean CAN_SUSTAIN_PLANTS;
	protected String NAME;

	public Tile(BufferedImage texture, String name, int id, boolean solid, boolean sustainPlants) {
		TEXTURE = texture;
		NAME = name;
		ID = id;
		SOLID = solid;
		CAN_SUSTAIN_PLANTS = sustainPlants;
		if (TILES[id] == null) {
			TILES[id] = this;
		} else {
			System.err.println(name + " can't use the ID: " + id + ", because it is already taken by " + TILES[id].getName());
			id++;
			System.err.println("Using ID: " + id);
			TILES[id] = this;
		}
	}

	public Tile(Animation animation, String name, int id, boolean solid, boolean sustainPlants) {
		ANIMATION = animation;
		NAME = name;
		ID = id;
		SOLID = solid;
		CAN_SUSTAIN_PLANTS = sustainPlants;
		if (TILES[id] == null) {
			TILES[id] = this;
		} else {
			System.err.println(
					name + " can't use the ID: " + id + ", because it is already taken by " + TILES[id].getName());
		}
	}

	public void tick() {
	}

	public void render(Graphics graphics, int x, int y) {
		if (TEXTURE != null) {
			graphics.drawImage(TEXTURE, x, y, TILE_WIDTH, TILE_HEIGHT, null);
		} else if (ANIMATION != null) {
			graphics.drawImage(ANIMATION.getCurrentFrame(), x, y, TILE_WIDTH, TILE_HEIGHT, null);
		}
	}

	public boolean isSolid() {
		return SOLID;
	}

	public int getID() {
		return ID;
	}

	public String getName() {
		return NAME;
	}

	public boolean canSustainPlants() {
		return CAN_SUSTAIN_PLANTS;
	}
}
