package dungeongame;

import java.awt.Graphics;

import dungeongame.states.BaseState;
import dungeongame.states.TitleScreenState;

public class GameStateManager {
	public static BaseState currentGameState;
	
	static{
		currentGameState = new TitleScreenState();
	}
	
	public static void drawCurrentGameState(Graphics g){
		currentGameState.drawMe(g);
	}
	
	public static void changeGameState(BaseState state){
		currentGameState = state;
	}
}
