package dungeongame.states;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import dungeongame.GameInput;
import dungeongame.GameStateManager;
import dungeongame.GameStateManager.GameStateFocus;
import dungeongame.basetypes.DungeonMap;
import dungeongame.entitys.BaseEntity;
import dungeongame.entitys.BattleEntity;
import dungeongame.entitys.Player;
import dungeongame.menus.PauseMenu;

public class DungeonMapState implements BaseState{
	public DungeonMap activeMap;
	private Player player;

	public DungeonMapState(){
		GameStateManager.changeForegroundState(new HUDState());
		
		activeMap = new DungeonMap(25,19);

		player = activeMap.getPlayer();
//		activeMap.entitys.add(new Enemy(new Point(12, 5), activeMap));
	}

	public void computeNextFrame() {
		initializeMovements();
		updatePositions();
		sortEntitys();
		removeEntities();
		
		activeMap.updateParticles();
		
		checkForMenus();
	}
	
	public void drawMe(Graphics g) {
		activeMap.drawMe(g);
	}
	
	private void initializeMovements(){
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
	}

	private void updatePositions(){
		for(BaseEntity e:activeMap.entitys){
			e.computeNextPosition();
		}
	}
	
	private void removeEntities(){
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
			entity.die();
			activeMap.entitys.remove(entity);
		}
	}
	
	private void checkForMenus(){
		if(GameInput.wasKeyPressed("X")){
			MenuStackState menuStackState = new MenuStackState();
			menuStackState.addMenu(new PauseMenu());
			
			GameStateManager.changeForegroundState(menuStackState);
			GameStateManager.changeGameStateFocus(GameStateFocus.FOREGROUND);
		}
	}
	
	private void sortEntitys(){
		Collections.sort(activeMap.entitys, new Comparator<BaseEntity>() {
			public int compare(BaseEntity e1, BaseEntity e2){
				return Double.compare(e1.y, e2.y);
			}
		});
	}
}
