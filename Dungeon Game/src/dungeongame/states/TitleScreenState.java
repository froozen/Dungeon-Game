package dungeongame.states;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dungeongame.GameInput;
import dungeongame.GameStateManager;
import dungeongame.RessourceManager;

public class TitleScreenState implements BaseState{

	public TitleScreenState(){
		RessourceManager.loadTileSet("testtile");
	}

	public void drawMe(Graphics g) {
		g.drawImage(RessourceManager.getImage("misc.title"), 0, 0, null);
		
		BufferedImage pressXText = RessourceManager.getFontifiedText("Press 'X' to enter the Game", "standard");
		
		g.drawImage(pressXText, 400 - (pressXText.getWidth() / 2), 400, null);
	}

	public void computeNextFrame() {
		if(GameInput.wasLeftClicked())System.out.println("Left Click!");
		if(GameInput.wasRightClicked())System.out.println("Right Click!");

		if(GameInput.wasKeyPressed("X")){
			System.out.println("Switching to DungeonMapState");
			GameStateManager.changeBackgroundState(new DungeonMapState());
		}
	}

}
