package dungeongame.entitys;

import java.awt.Point;

import dungeongame.RessourceManager;
import dungeongame.basetypes.GameMap;
import dungeongame.particles.DamageNumberParticle;
import dungeongame.stats.DamageCalculator;
import dungeongame.stats.Stats;

public abstract class BattleEntity extends BaseEntity{
	public Stats stats;
	
	public abstract void uponDeath();
	
	public BattleEntity(Point position, GameMap locationMap) {
		super(position, locationMap);
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
				battleTarget.receiveDamage(DamageCalculator.calculateNormalAttack(stats, battleTarget.stats));
				moving = true;
				remainingFreezeTime = 0.5;
			}
		}
	}
	
	public void receiveDamage(int damage){
		stats.subtractHp(damage);
		
		if ( damage > 0 )
			locationMap.particles.add(new DamageNumberParticle(damage, (int) (x * RessourceManager.tileSize) + (sprite.getWidth() / 2), (int) (y * RessourceManager.tileSize) - (sprite.getHeight() / 4)));
	}
}
