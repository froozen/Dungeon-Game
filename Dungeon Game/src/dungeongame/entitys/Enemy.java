package dungeongame.entitys;

import java.awt.Point;

import dungeongame.BattleStats;
import dungeongame.GameVariables;
import dungeongame.basetypes.GameMap;

public class Enemy extends BattleEntity{

	public Enemy(Point position, GameMap location){
		super(position, location);
		spriteLocation = "sprites.enemy";
		
		battleStats = new BattleStats();
		battleStats.atk = 7;
		battleStats.def = 3;
		battleStats.maxHp = 30;
		battleStats.hp = 30;
		battleStats.maxMp = 15;
		battleStats.mp = 15;
	}
	
	public void initializeMovement() {
		Player prey = locationMap.getPlayer();
		
		if(prey != null){
			if(prey.position.x == position.x){
				if(prey.position.y - position.y == -1){
					direction = Direction.UP;
					attack(direction);
				}
				else if(prey.position.y - position.y == 1){
					direction = Direction.DOWN;
					attack(direction);
				}
			}
			else if(prey.position.y == position.y){
				if(prey.position.x - position.x == -1){
					direction = Direction.LEFT;
					attack(direction);
				}
				else if(prey.position.x - position.x == 1){
					direction = Direction.RIGHT;
					attack(direction);
				}
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

	public void uponDeath() {
		GameVariables.playerBattleStats.gainExp(20);
	}

}
