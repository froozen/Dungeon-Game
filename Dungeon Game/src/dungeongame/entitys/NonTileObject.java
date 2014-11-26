package dungeongame.entitys;

import java.awt.Graphics;
import java.awt.Point;

public interface NonTileObject {
	public void drawMe(Graphics g);
	public void update();

	public Point getPosition();
	public double getX();
	public double getY();
	public boolean isSolid();
}