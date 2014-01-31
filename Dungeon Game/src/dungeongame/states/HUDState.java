package dungeongame.states;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dungeongame.RessourceManager;

public class HUDState extends BaseState{

	public void drawMe(Graphics g) {
		BufferedImage text = RessourceManager.getFontifiedText("Demo text", "outline");
		g.drawImage(text, 800 - text.getWidth() - 5, 2, null);
	}

	//UNUSED
	public void computeNextFrame() {
	}
}
