package dungeongame.particles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dungeongame.GameVariables;
import dungeongame.RessourceManager;

public class DamageNumberParticle extends BaseParticle{
	private final int speed = 75;
	
	private BufferedImage particleImage;
	
	public DamageNumberParticle(int damage, int x, int y){
		this.x = x;
		this.y = y;
		
		particleImage = RessourceManager.getFontifiedText(damage + "", "outline");
		
		x -= particleImage.getWidth() / 2;
	}
	
	public void updatePosition() {
		passedTime +=GameVariables.timeSinceLastFrame;
		if(passedTime < 0.5)y -=GameVariables.timeSinceLastFrame * speed;
		else remove = true;
	}

	public void drawMe(Graphics g) {
		g.drawImage(particleImage, (int)x, (int)y, null);
	}

}
