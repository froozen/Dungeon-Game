package dungeongame.entitys;

import java.awt.Point;

import dungeongame.BattleStats;
import dungeongame.GameInput;
import dungeongame.basetypes.GameMap;

public class Player extends BattleEntity{


	public Player(Point position, GameMap locationMap){
		super(position, locationMap);
		
		battleStats = new BattleStats(200, 5, 5);
		spriteLocation = "sprites.player";
	}

	public void initializeMovement() {
		if(!moving){
			if(GameInput.wasKeyDown("W"))moveDirection(Direction.UP);
			else if(GameInput.wasKeyDown("A")) moveDirection(Direction.LEFT);
			else if(GameInput.wasKeyDown("D")) moveDirection(Direction.RIGHT);
			else if(GameInput.wasKeyDown("S")) moveDirection(Direction.DOWN);
			else if(GameInput.wasKeyDown("Space"))attack(direction);
		}
	}
}
