package dungeongame.states;

import java.awt.Graphics;

import dungeongame.GameInput;
import dungeongame.GameStateManager;
import dungeongame.RessourceManager;

public class TitleScreenState extends BaseState{

	public TitleScreenState(){
		RessourceManager.loadTileSet("testtile");
	}

	public void drawMe(Graphics g) {
		g.drawImage(RessourceManager.getImage("misc.title"), 0, 0, null);
		
		g.drawImage(RessourceManager.getFontifiedText("Fontrender demo text", "standart"), 2, 2, null);
	}

	public void computeNextFrame() {
		if(GameInput.wasLeftClicked())System.out.println("Left Click!");
		if(GameInput.wasRightClicked())System.out.println("Right Click!");

		if(GameInput.wasKeyPressed("X")){
			System.out.println("Switching to DungeonMapState");
			GameStateManager.changeGameState(new DungeonMapState());
		}
	}

}
