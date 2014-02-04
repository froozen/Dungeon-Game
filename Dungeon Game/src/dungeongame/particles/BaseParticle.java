package dungeongame.particles;

import java.awt.Graphics;

public abstract class BaseParticle {
	public double x, y;
	public boolean remove;
	
	protected double passedTime;
		
	public abstract void updatePosition();
	public abstract void drawMe(Graphics g);
}
