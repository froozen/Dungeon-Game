package dungeongame.states;

import java.awt.Graphics;
import java.awt.Point;

import dungeongame.basetypes.GameMap;
import dungeongame.entitys.BaseEntity;
import dungeongame.entitys.Player;

public class DungeonMapState extends BaseState{
	public GameMap activeMap;

	public DungeonMapState(){
		activeMap = new GameMap(25,20);
		activeMap.entitys.add(new Player(new Point(1,1),activeMap));
	}
	
	public void drawMe(Graphics g) {
		activeMap.drawMe(g);
	}

	public void computeNextFrame() {
		BaseEntity.updateTimeSinceLastFrame();
		for(BaseEntity e:activeMap.entitys){
			e.computeNextFrame();
		}
	}

}
