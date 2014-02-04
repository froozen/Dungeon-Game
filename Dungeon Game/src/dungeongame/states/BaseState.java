package dungeongame.states;

import java.awt.Graphics;

public abstract interface BaseState {
	public abstract void drawMe(Graphics g);
	public abstract void computeNextFrame();
}
