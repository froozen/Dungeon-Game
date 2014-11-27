package dungeongame.entitys;

import java.awt.Point;

import dungeongame.basetypes.DungeonMap;
import dungeongame.basetypes.GameMap;
import dungeongame.stats.HumanStats;
import dungeongame.stats.MonsterStats;

public class Enemy extends BattleEntity{

	public Enemy(Point position, GameMap location){
		super(position, location);
		spriteLocation = "sprites.enemy";
		
		MonsterStats ownStats = new MonsterStats();
		ownStats.atk = 10;
		ownStats.def = 3;
		ownStats.hp = ownStats.maxHp = 30;
		ownStats.mp = ownStats.maxMp = 15;
		
		stats = ownStats;
	}
	
	public void update() {
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
		((DungeonMap) locationMap).spawnEnemy();
		HumanStats.gainExp(5);
	}
}
