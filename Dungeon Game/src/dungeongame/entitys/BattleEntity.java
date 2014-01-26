package dungeongame.entitys;

import java.awt.Point;

import dungeongame.BattleStats;
import dungeongame.basetypes.GameMap;

public abstract class BattleEntity extends BaseEntity{
	public BattleStats battleStats;
	public String battleStatsName;
	
	public BattleEntity(Point position, GameMap locationMap) {
		super(position, locationMap);
		battleStats = new BattleStats();
	}

	public void attack(Direction direction){
		BaseEntity target = null;
		
		if(direction == Direction.LEFT)target = locationMap.getEntityAt(new Point(position.x - 1, position.y));
		else if(direction == Direction.RIGHT)target = locationMap.getEntityAt(new Point(position.x + 1, position.y));
		else if(direction == Direction.UP)target = locationMap.getEntityAt(new Point(position.x, position.y - 1));
		else if(direction == Direction.DOWN)target = locationMap.getEntityAt(new Point(position.x, position.y + 1));
		
		if(target != null){
			if(target instanceof BattleEntity){
				BattleEntity battleTarget = (BattleEntity)target;
				battleTarget.battleStats.dealDamage(battleStats.atk);
				moving = true;
			}
		}
	}
}
