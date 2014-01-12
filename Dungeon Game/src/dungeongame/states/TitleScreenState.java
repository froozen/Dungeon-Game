package dungeongame.states;

import java.awt.Graphics;

import dungeongame.GameInput;
import dungeongame.RessourceManager;

public class TitleScreenState extends BaseState{

	public void drawMe(Graphics g) {
		g.drawImage(RessourceManager.getImage("misc.title"), 0, 0, null);
	}

	public void computeNextFrame() {
		if(GameInput.wasLeftClicked())System.out.println("Left Click!");
		if(GameInput.wasRightClicked())System.out.println("Right Click!");
		
		if(GameInput.wasKeyPressed("X"))System.out.println("X was pressed!");
	}

}
