package dungeongame.states;

import java.awt.Color;
import java.awt.Graphics;

public class TitleScreenState extends BaseState{

	public void drawMe(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(0, 0, 800, 600);
	}

	public void computeNextFrame() {
		System.out.println("Frame!");
	}

}
