package dungeongame.entitys;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

import dungeongame.RessourceManager;
import dungeongame.basetypes.GameMap;

public abstract class BaseEntity {
	public Point position;
	public double x, y;
	public String spriteLocation;
	public BufferedImage sprite;
	public GameMap locationMap;
	public Direction direction;
	public int cyclePhase;
	
	public void drawMe(Graphics g){
		g.drawImage(sprite, (int) (x * RessourceManager.tileSize - ((sprite.getWidth() - RessourceManager.tileSize) / 2)), (int) ((y + 1) * RessourceManager.tileSize - sprite.getHeight()), null);
	}
	
	public enum Direction{
		DOWN, RIGHT, UP, LEFT
	}
}