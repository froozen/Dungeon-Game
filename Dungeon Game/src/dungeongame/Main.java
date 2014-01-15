package dungeongame;

public class Main {

	public static void main(String[] args) {
		
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
