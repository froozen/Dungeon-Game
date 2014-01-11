package dungeongame.states;

import java.awt.Color;
import java.awt.Graphics;

import dungeongame.GameInput;

public class TitleScreenState extends BaseState{

	public void drawMe(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(0, 0, 800, 600);
	}

	public void computeNextFrame() {
		if(GameInput.wasLeftClicked)System.out.println("Left Click!");
		if(GameInput.wasRightClicked)System.out.println("Right Click!");
		
		if(GameInput.wasKeyPressed("X"))System.out.println("X was pressed!");
	}

}
