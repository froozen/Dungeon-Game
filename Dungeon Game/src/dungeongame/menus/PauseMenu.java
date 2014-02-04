package dungeongame.menus;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dungeongame.GameInput;
import dungeongame.GameStateManager;
import dungeongame.RessourceManager;
import dungeongame.GameStateManager.GameStateFocus;
import dungeongame.states.HUDState;

public class PauseMenu implements BaseMenu {
	private BufferedImage backGround, text;
	
	public PauseMenu(){
		backGround = RessourceManager.getImage("ui.pause");
		text = RessourceManager.getFontifiedText("Pause", "standard");
	}

	public void drawMe(Graphics g) {
		g.drawImage(backGround, 400 - (backGround.getWidth() / 2), 300 - (backGround.getHeight() / 2), null);
		g.drawImage(text, 405 - (backGround.getWidth() / 2), 305 - (backGround.getHeight() / 2), null);
	}

	public void computeNextFrame() {
		if(GameInput.wasKeyPressed("X")){
			GameStateManager.changeForegroundState(new HUDState());
			GameStateManager.changeGameStateFocus(GameStateFocus.BACKGROUND);
		}
	}

}
