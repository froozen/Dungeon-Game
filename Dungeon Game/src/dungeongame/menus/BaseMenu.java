package dungeongame.menus;

import java.awt.Graphics;

public interface BaseMenu {
	public abstract void drawMe(Graphics g);
	public abstract void computeNextFrame();
}
