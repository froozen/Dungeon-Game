package dungeongame.states;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import dungeongame.GameInput;
import dungeongame.GameStateManager;
import dungeongame.GameVariables;
import dungeongame.GameStateManager.GameStateFocus;
import dungeongame.basetypes.DungeonMap;
import dungeongame.entitys.BaseEntity;
import dungeongame.entitys.BattleEntity;
import dungeongame.entitys.Player;
import dungeongame.menus.InventoryMenu;

public class DungeonMapState implements BaseState{
	public DungeonMap activeMap;
	private Player player;

	public DungeonMapState(){
		GameStateManager.changeForegroundState(new HUDState());
		
		activeMap = new DungeonMap(25,19);

		player = activeMap.getPlayer();
	}

	public void computeNextFrame() {
		initializeMovements();
		updateScreenPositions();
		sortEntitys();
		removeEntities();
		
		activeMap.updateParticles();
		
		checkForMenus();
		
		if(GameVariables.playerBattleStats.hp < 1){
			GameStateManager.changeBackgroundState(new TitleScreenState());
			GameStateManager.changeForegroundState(null);
			GameVariables.playerBattleStats = null;
		}
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
				ArrayList<BaseEntity> valuableEntitys = activeMap.getEntitysIn(activeMap.getRoomThatContains(player));
				if(valuableEntitys != null){
					for(BaseEntity entity:valuableEntitys){
						if(entity != player){
							entity.initializeMovement();
						}
					}
				}
			}
		}
	}

	private void updateScreenPositions(){
		ArrayList<BaseEntity> valuableEntitys = activeMap.getEntitysIn(activeMap.getRoomThatContains(player));
		if(valuableEntitys != null){
			for(BaseEntity entity:valuableEntitys){
					entity.computeNextPosition();
			}
		}
		else player.computeNextPosition();
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
			entity.uponDeath();
			activeMap.entitys.remove(entity);
		}
	}
	
	private void checkForMenus(){
		if(GameInput.wasKeyPressed("Menu")){
			MenuStackState menuStackState = new MenuStackState();
			menuStackState.addMenu(new InventoryMenu());
			
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
