package dungeongame.entitys;

import java.awt.Point;

import dungeongame.BattleStats;
import dungeongame.GameInput;
import dungeongame.GameVariables;
import dungeongame.basetypes.GameMap;

public class Player extends BattleEntity{


	public Player(Point position, GameMap locationMap){
		super(position, locationMap);

		if(GameVariables.playerBattleStats == null){
			battleStats = new BattleStats();
			battleStats.atk = 15;
			battleStats.def = 5;
			
			battleStats.maxHp = 200;
			battleStats.hp = battleStats.maxHp;
			battleStats.maxMp = 150;
			battleStats.mp = battleStats.maxMp;
			
			battleStats.expToNextLevel = 15;
			battleStats.lastExpToNextLevel = battleStats.expToNextLevel;
			
			battleStats.str = 4;
			battleStats.mag = 4;
			battleStats.dex = 4;
			battleStats.luk = 4;
			
			GameVariables.playerBattleStats = battleStats;
			battleStats.dealDamage(50);
		}
		else battleStats = GameVariables.playerBattleStats;
		
		spriteLocation = "sprites.player";
	}

	public void initializeMovement() {
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
