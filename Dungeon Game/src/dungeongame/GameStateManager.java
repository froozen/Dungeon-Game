package dungeongame;

import java.awt.Graphics;

import dungeongame.states.BaseState;
import dungeongame.states.TitleScreenState;

public class GameStateManager {
	public static BaseState foregroundState, backgroundState;
	public static GameStateFocus focus;
	
	static{
		backgroundState = new TitleScreenState();
		focus = GameStateFocus.BACKGROUND;
	}
	
	public static void drawCurrentGameState(Graphics g){
		if(backgroundState != null)backgroundState.drawMe(g);
		if(foregroundState != null)foregroundState.drawMe(g);
	}
	
	public static void changeBackgroundState(BaseState state){
		backgroundState = state;
	}
	
	public static void changeForegroundState(BaseState state){
		foregroundState = state;
	}
	
	public static void computeNextFrame(){
		if(focus == GameStateFocus.BACKGROUND)backgroundState.computeNextFrame();
		else if(focus == GameStateFocus.FOREGROUND)foregroundState.computeNextFrame();
	}
	
	public static void changeGameStateFocus(GameStateFocus newFocus){
		focus = newFocus;
	}
	
	public enum GameStateFocus{
		FOREGROUND, BACKGROUND
	}
}
