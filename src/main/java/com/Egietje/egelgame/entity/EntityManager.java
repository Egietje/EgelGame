package com.Egietje.egelgame.entity;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

import com.Egietje.egelgame.Handler;
import com.Egietje.egelgame.entity.creature.*;

public class EntityManager {
	private Handler HANDLER;
	private EntityPlayer PLAYER;
	private ArrayList<Entity> ENTITIES;
	private Comparator<Entity> SORTER = (a, b) -> {
		if (a.getY() + a.getHeight() < b.getY() + b.getHeight()) {
			return -1;
		} else {
			return 1;
		}
	};

	public EntityManager(Handler handler, EntityPlayer player) {
		HANDLER = handler;
		PLAYER = player;
		ENTITIES = new ArrayList<>();
		addEntity(player);
	}

	public void tick() {
		try {
			Iterator<Entity> it = ENTITIES.iterator();
			while (it.hasNext()) {
				Entity entity = it.next();
				entity.tick();
				if (!entity.isActive()) {
					it.remove();
				}
			}
		} catch (ConcurrentModificationException e) {
		}
		ENTITIES.sort(SORTER);
	}

	public void render(Graphics graphics) {
		for (Entity entity : ENTITIES) {
			entity.render(graphics);
		}
		PLAYER.postRender(graphics);
	}

	public void addEntity(Entity entity) {
		ENTITIES.add(entity);
	}

	public void removeEntity(Entity entity) {
		ENTITIES.remove(entity);
	}

	public ArrayList<Entity> getEntities() {
		return ENTITIES;
	}

	public void setEntities(ArrayList<Entity> entities) {
		ENTITIES = entities;
	}

	public Handler getHandler() {
		return HANDLER;
	}

	public void setHandler(Handler handler) {
		HANDLER = handler;
	}

	public EntityPlayer getPlayer() {
		return PLAYER;
	}

	public void setPlayer(EntityPlayer player) {
		PLAYER = player;
	}
}
