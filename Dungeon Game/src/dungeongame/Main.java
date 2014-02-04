package dungeongame;

public class Main {

	public static void main(String[] args) {
		GameFrame gameFrame = new GameFrame();
				
		while(true){
			GameVariables.updateTimeSinceLastFrame();
			GameInput.pushInputs();
			GameStateManager.computeNextFrame();
			gameFrame.refresh();
			try {Thread.sleep(10);}
			catch (InterruptedException e) {e.printStackTrace();}
		}
	}

}
