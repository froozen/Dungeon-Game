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
	}

	public void initializeMovement() {
		if(!moving){
			if(GameInput.wasKeyDown("W")){
				direction = Direction.UP;
				moving = true;
				position.y--;
			}
			else if(GameInput.wasKeyDown("A")){
				direction = Direction.LEFT;
				moving = true;
				position.x--;
			}
			else if(GameInput.wasKeyDown("D")){
				direction = Direction.RIGHT;
				moving = true;
				position.x++;
			}
			else if(GameInput.wasKeyDown("S")){
				direction = Direction.DOWN;
				moving = true;
				position.y++;
			}
			
			if(moving)System.out.println("target: x=" + position.x + " y=" + position.y);
		}
	}
}
