package dungeongame.entitys;

import java.awt.Point;

import dungeongame.RessourceManager;
import dungeongame.basetypes.GameMap;

public class Enemy extends BaseEntity{

	public Enemy(Point position, GameMap location){
		this.locationMap = location;
		this.position = position;
		this.x = position.x;
		this.y = position.y;
		
		direction = Direction.DOWN;
		cyclePhase = 0;
		moving = false;
		
		spriteLocation = "sprites.enemy";
		sprite = RessourceManager.getCharacterSprite(spriteLocation, direction, cyclePhase);
	}
	
	public void initializeMovement() {
		Player prey = null;
		
		for(BaseEntity entity:locationMap.entitys){
			if(entity instanceof Player){
				prey = (Player) entity;
				break;
			}
		}
		
		if(prey != null){
			if(prey.position.x > position.x){
				position.x++;
				direction = Direction.RIGHT;
				moving = true;
			}
			else if(prey.position.x < position.x){
				position.x--;
				direction = Direction.LEFT;
				moving = true;
			}
			else if(prey.position.y > position.y){
				position.y++;
				direction = Direction.DOWN;
				moving = true;
			}
			else if(prey.position.y < position.y){
				position.y--;
				direction = Direction.UP;
				moving = true;
			}
		}
		else System.out.println("Error: Unable to find Player in locationMap.entitys");
	}

}
