package dungeongame.menus;

import java.awt.Graphics;

public abstract class BaseMenu {
	
	
	public abstract void drawMe(Graphics g);
	public abstract void computeNextFrame();
}
