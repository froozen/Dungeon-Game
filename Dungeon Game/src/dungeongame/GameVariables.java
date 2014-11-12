package dungeongame;

import dungeongame.stats.HumanStats;

public class GameVariables {
	public static HumanStats playerBattleStats;
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
