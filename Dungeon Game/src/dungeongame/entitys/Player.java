package dungeongame.entitys;

import java.awt.Point;

import dungeongame.GameInput;
import dungeongame.basetypes.GameMap;

public class Player extends BaseEntity{


	public Player(Point position, GameMap locationMap){
		super(position, locationMap);
		
		spriteLocation = "sprites.player";
	}

	public void initializeMovement() {
		if(!moving){
			if(GameInput.wasKeyDown("W"))moveDirection(Direction.UP);
			else if(GameInput.wasKeyDown("A")) moveDirection(Direction.LEFT);
			else if(GameInput.wasKeyDown("D")) moveDirection(Direction.RIGHT);
			else if(GameInput.wasKeyDown("S")) moveDirection(Direction.DOWN);
		}
	}
}
