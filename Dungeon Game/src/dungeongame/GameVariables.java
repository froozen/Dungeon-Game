package dungeongame;

public class GameVariables {
	public static BattleStats playerBattleStats;
	public static double timeSinceLastFrame;
	
	private static long lastTime;
	
	static{
		lastTime = System.currentTimeMillis();
	}
	
	public static void updateTimeSinceLastFrame(){
		timeSinceLastFrame = ((double)(System.currentTimeMillis() - lastTime) / 1000);
		lastTime = System.currentTimeMillis();
	}
}
