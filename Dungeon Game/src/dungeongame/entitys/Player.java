package dungeongame.entitys;

import java.awt.Point;

import dungeongame.GameInput;
import dungeongame.RessourceManager;
import dungeongame.basetypes.GameMap;

public class Player extends BaseEntity{


	public Player(Point position, GameMap locationMap){
		this.locationMap = locationMap;
		this.position = position;
		this.x = position.x;
		this.y = position.y;

		direction = Direction.DOWN;
		cyclePhase = 0;
		moving = false;

		spriteLocation = "sprites.player";
		sprite = RessourceManager.getCharacterSprite(spriteLocation, direction, cyclePhase);

		locationMap.occupied[position.x][position.y] = true;
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
