package com.Egietje.egelgame.graphics;

import java.awt.Font;
import java.awt.image.BufferedImage;

public class Assets {
	private static final SpriteSheet TILE_SHEET = new SpriteSheet(ImageLoader.loadImage("tile_sheet"));
	private static final SpriteSheet ENTITY_SHEET = new SpriteSheet(ImageLoader.loadImage("entity_sheet"));
	private static final SpriteSheet ITEM_SHEET = new SpriteSheet(ImageLoader.loadImage("item_sheet"));
	private static final SpriteSheet BUTTON_SHEET = new SpriteSheet(ImageLoader.loadImage("button_sheet"));
	public static final BufferedImage TILE_GRASS = TILE_SHEET.getTexture(0, 0, 1, 1);
	public static final BufferedImage TILE_SAND = TILE_SHEET.getTexture(1, 0, 1, 1);
	public static final BufferedImage TILE_PATH = TILE_SHEET.getTexture(2, 0, 1, 1);
	public static final BufferedImage TILE_WOOD = TILE_SHEET.getTexture(3, 0, 1, 1);
	public static final BufferedImage TILE_STONE = TILE_SHEET.getTexture(4, 0, 1, 1);
	public static final BufferedImage[] TILE_WATER = new BufferedImage[16];
	public static final Animation ANIM_WATER = new Animation(125, TILE_WATER);
	public static final BufferedImage ENTITY_TREE = ENTITY_SHEET.getTexture(0, 0, 2, 3);
	public static final BufferedImage ENTITY_RED_FLOWER = ENTITY_SHEET.getTexture(2, 0, 1, 1);
	public static final BufferedImage ENTITY_YELLOW_FLOWER = ENTITY_SHEET.getTexture(3, 0, 1, 1);
	public static final BufferedImage ENTITY_ROCK = ENTITY_SHEET.getTexture(4, 0, 1, 1);
	public static final BufferedImage ENTITY_SAPLING = ENTITY_SHEET.getTexture(5, 0, 1, 1);
	public static final BufferedImage ENTITY_EGIE_LEFT = ENTITY_SHEET.getTexture(2, 1, 1, 1);
	public static final BufferedImage ENTITY_EGIE_RIGHT = ENTITY_SHEET.getTexture(2, 2, 1, 1);
	public static final BufferedImage ENTITY_EGIE_BALL = ENTITY_SHEET.getTexture(5, 1, 1, 1);
	public static final BufferedImage[] ENTITY_EGIE_LEFT_ANIM = new BufferedImage[2];
	public static final BufferedImage[] ENTITY_EGIE_RIGHT_ANIM = new BufferedImage[2];
	public static final BufferedImage ITEM_LOG = ITEM_SHEET.getTexture(0, 0, 1, 1);
	public static final BufferedImage ITEM_STONE = ITEM_SHEET.getTexture(1, 0, 1, 1);
	public static final BufferedImage[] BUTTON_START = new BufferedImage[2];
	public static final BufferedImage[] BUTTON_SETTINGS = new BufferedImage[2];
	public static final BufferedImage[] BUTTON_WORLD = new BufferedImage[2];
	public static final BufferedImage INVENTORY_SCREEN = ImageLoader.loadImage("inventory");
	public static final BufferedImage MENU_BACKGROUND = ImageLoader.loadImage("menu_background");
	public static final BufferedImage MENU_LOGO = ImageLoader.loadImage("menu_logo");
	public static final Font FONT32 = FontLoader.loadFont("slkscr", 32);

	public static void loadArrays() {
		TILE_WATER[0] = TILE_SHEET.getTexture(0, 1, 1, 1);
		TILE_WATER[1] = TILE_SHEET.getTexture(1, 1, 1, 1);
		TILE_WATER[2] = TILE_SHEET.getTexture(2, 1, 1, 1);
		TILE_WATER[3] = TILE_SHEET.getTexture(3, 1, 1, 1);
		TILE_WATER[4] = TILE_SHEET.getTexture(4, 1, 1, 1);
		TILE_WATER[5] = TILE_SHEET.getTexture(5, 1, 1, 1);
		TILE_WATER[6] = TILE_SHEET.getTexture(6, 1, 1, 1);
		TILE_WATER[7] = TILE_SHEET.getTexture(7, 1, 1, 1);
		TILE_WATER[8] = TILE_SHEET.getTexture(0, 2, 1, 1);
		TILE_WATER[9] = TILE_SHEET.getTexture(1, 2, 1, 1);
		TILE_WATER[10] = TILE_SHEET.getTexture(2, 2, 1, 1);
		TILE_WATER[11] = TILE_SHEET.getTexture(3, 2, 1, 1);
		TILE_WATER[12] = TILE_SHEET.getTexture(4, 2, 1, 1);
		TILE_WATER[13] = TILE_SHEET.getTexture(5, 2, 1, 1);
		TILE_WATER[14] = TILE_SHEET.getTexture(6, 2, 1, 1);
		TILE_WATER[15] = TILE_SHEET.getTexture(7, 2, 1, 1);
		ENTITY_EGIE_LEFT_ANIM[0] = ENTITY_SHEET.getTexture(3, 1, 1, 1);
		ENTITY_EGIE_LEFT_ANIM[1] = ENTITY_SHEET.getTexture(4, 1, 1, 1);
		ENTITY_EGIE_RIGHT_ANIM[0] = ENTITY_SHEET.getTexture(3, 2, 1, 1);
		ENTITY_EGIE_RIGHT_ANIM[1] = ENTITY_SHEET.getTexture(4, 2, 1, 1);
		BUTTON_START[0] = BUTTON_SHEET.getTexture(0, 0, 3, 1);
		BUTTON_START[1] = BUTTON_SHEET.getTexture(0, 1, 3, 1);
		BUTTON_SETTINGS[0] = BUTTON_SHEET.getTexture(3, 0, 3, 1);
		BUTTON_SETTINGS[1] = BUTTON_SHEET.getTexture(3, 1, 3, 1);
	}

}
