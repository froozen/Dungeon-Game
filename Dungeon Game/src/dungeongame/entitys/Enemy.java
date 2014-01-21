package dungeongame.entitys;

import java.awt.Point;

import dungeongame.basetypes.GameMap;

public class Enemy extends BaseEntity{

	public Enemy(Point position, GameMap location){
		super(position, location);
		spriteLocation = "sprites.enemy";
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
			if(prey.position.x > position.x) moveDirection(Direction.RIGHT);
			else if(prey.position.x < position.x) moveDirection(Direction.LEFT);
			else if(prey.position.y > position.y) moveDirection(Direction.DOWN);
			else if(prey.position.y < position.y) moveDirection(Direction.UP);
		}
		else System.out.println("Error: Unable to find Player in locationMap.entitys");
	}

}
