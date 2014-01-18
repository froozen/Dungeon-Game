package dungeongame.states;

import java.awt.Graphics;
import java.awt.Point;

import dungeongame.basetypes.GameMap;
import dungeongame.entitys.BaseEntity;
import dungeongame.entitys.Enemy;
import dungeongame.entitys.Player;

public class DungeonMapState extends BaseState{
	public GameMap activeMap;
	private Player player;

	public DungeonMapState(){
		activeMap = new GameMap(25,20);
		
		player = new Player(new Point(1,1), activeMap);
		activeMap.entitys.add(player);
		activeMap.entitys.add(new Enemy(new Point(3, 3), activeMap));
	}
	
	public void drawMe(Graphics g) {
		activeMap.drawMe(g);
	}

	public void computeNextFrame() {
		BaseEntity.updateTimeSinceLastFrame();
		
		if(!player.moving){
			player.initializeMovement();
			
			if(player.moving){
				for(BaseEntity entity:activeMap.entitys){
					if(entity != player){
						entity.initializeMovement();
					}
				}
			}
		}
		
		
		
		for(BaseEntity e:activeMap.entitys){
			e.computeNextPosition();
		}
	}

}
