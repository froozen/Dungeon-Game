package dungeongame.states;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import dungeongame.RessourceManager;
import dungeongame.basetypes.GameMap;
import dungeongame.entitys.BaseEntity;
import dungeongame.entitys.BattleEntity;
import dungeongame.entitys.Enemy;
import dungeongame.entitys.Player;

public class DungeonMapState extends BaseState{
	public GameMap activeMap;
	private Player player;

	public DungeonMapState(){
		activeMap = new GameMap(25,19);

		player = new Player(new Point(1,1), activeMap);
		activeMap.entitys.add(player);
		activeMap.entitys.add(new Enemy(new Point(10, 13), activeMap));
	}

	public void drawMe(Graphics g) {
		activeMap.drawMe(g);
		
		BufferedImage text = RessourceManager.getFontifiedText("Demo text", "outline");
		g.drawImage(text, 800 - text.getWidth() - 5, 2, null);
	}

	public void computeNextFrame() {
		BaseEntity.updateTimeSinceLastFrame();

		boolean anythingMoving = false;

		for(BaseEntity entity:activeMap.entitys){
			if(entity.moving)anythingMoving = true;
		}

		if(!anythingMoving){
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

		ArrayList<BattleEntity> removeList = new ArrayList<BattleEntity>();
		for(BaseEntity entity:activeMap.entitys){
			if(entity instanceof BattleEntity){
				BattleEntity battleEntity = (BattleEntity)entity;
				if(battleEntity.battleStats.hp < 1){
					removeList.add(battleEntity);
				}
			}
		}

		for(BattleEntity entity:removeList){
			if(activeMap.occupied[entity.position.x][entity.position.y])activeMap.occupied[entity.position.x][entity.position.y] = false;
			activeMap.entitys.remove(entity);
		}
	}

}
