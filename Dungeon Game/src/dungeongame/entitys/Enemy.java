package dungeongame.entitys;

import java.awt.Point;

import dungeongame.BattleStats;
import dungeongame.basetypes.GameMap;

public class Enemy extends BattleEntity{

	public Enemy(Point position, GameMap location){
		super(position, location);
		spriteLocation = "sprites.enemy";
		
		battleStats = new BattleStats(30, 5, 7, 3);
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
			if(prey.position.x == position.x){
				if(prey.position.y - position.y == -1)attack(Direction.UP);
				else if(prey.position.y - position.y == 1)attack(Direction.DOWN);
			}
			else if(prey.position.y == position.y){
				if(prey.position.x - position.x == 1)attack(Direction.LEFT);
				else if(prey.position.x - position.x == -1)attack(Direction.RIGHT);
			}
			
			if(!moving){
				if(prey.position.x > position.x) moveDirection(Direction.RIGHT);
				else if(prey.position.x < position.x) moveDirection(Direction.LEFT);
				else if(prey.position.y > position.y) moveDirection(Direction.DOWN);
				else if(prey.position.y < position.y) moveDirection(Direction.UP);
			}
		}
		else System.out.println("Error: Cannot find Player in locationMap.entitys");
	}

}
