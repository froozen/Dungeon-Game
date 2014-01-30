package dungeongame.particles;

import java.awt.Graphics;

import dungeongame.entitys.BaseEntity;

public abstract class BaseParticle {
	public double x, y;
	public boolean remove;
	
	protected double passedTime;
	
	public static double timeSinceLastFrame;
	
	public abstract void updatePosition();
	public abstract void drawMe(Graphics g);
	
	public static void updateTimeSinceLastFrame(){
		timeSinceLastFrame = BaseEntity.timeSinceLastFrame;
	}
}
