package dungeongame.entitys;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

import dungeongame.GameStateManager;
import dungeongame.RessourceManager;
import dungeongame.basetypes.GameMap;
import dungeongame.states.DungeonMapState;

public class Stairs implements NonTileObject{
	BufferedImage image;
	double x, y;
	Point position;
	GameMap locationMap;
	
	public Stairs(Point position, GameMap locationMap){
		this.position = position;
		this.x = position.x;
		this.y = position.y;
		this.locationMap = locationMap;
	}

	public void drawMe(Graphics g) {
		image = RessourceManager.getTile(3);
		g.drawImage(image, (int) (x * RessourceManager.tileSize - ((image.getWidth() - RessourceManager.tileSize) / 2)), (int) ((y + 1) * RessourceManager.tileSize - image.getHeight()), null);
	}

	public void update() {
		if(locationMap.getPlayer().position.equals(position))
			GameStateManager.changeBackgroundState(new DungeonMapState());
	}

	public Point getPosition() {
		return position;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public boolean isSolid() {
		return false;
	}

}
