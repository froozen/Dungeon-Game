package dungeongame.entitys;

import java.awt.Point;

import dungeongame.GameInput;
import dungeongame.GameVariables;
import dungeongame.basetypes.GameMap;
import dungeongame.stats.HumanStats;

public class Player extends BattleEntity{
	public Player(Point position, GameMap locationMap){
		super(position, locationMap);

		if(GameVariables.playerBattleStats == null){
			// TODO Generate somewhere else
			stats = new HumanStats ();
			stats.hp = stats.maxHp = 200;
			stats.mp = stats.maxMp = 150;
			
			GameVariables.playerBattleStats = (HumanStats)stats;
		}
		else stats = GameVariables.playerBattleStats;
		
		spriteLocation = "sprites.player";
	}

	public void update() {
		if(!moving){
			if(GameInput.wasKeyDown("Up"))moveDirection(Direction.UP);
			else if(GameInput.wasKeyDown("Left")) moveDirection(Direction.LEFT);
			else if(GameInput.wasKeyDown("Right")) moveDirection(Direction.RIGHT);
			else if(GameInput.wasKeyDown("Down")) moveDirection(Direction.DOWN);
			else if(GameInput.wasKeyDown("Action"))attack(direction);
		}
	}

	//UNUSED
	public void uponDeath() {		
	}
}
