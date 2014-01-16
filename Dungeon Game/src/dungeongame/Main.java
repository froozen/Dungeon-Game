package dungeongame;

import dungeongame.entitys.BaseEntity;

public class Main {

	public static void main(String[] args) {
		BaseEntity.updateTimeSinceLastFrame();
		
		GameFrame gameFrame = new GameFrame();
		
		while(true){
			GameInput.pushInputs();
			GameStateManager.computeNextFrame();
			gameFrame.refresh();
			try {Thread.sleep(15);}
			catch (InterruptedException e) {e.printStackTrace();}
		}
	}

}
