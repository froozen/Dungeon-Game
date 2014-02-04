package dungeongame.states;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import dungeongame.GameInput;
import dungeongame.GameStateManager;
import dungeongame.GameStateManager.GameStateFocus;
import dungeongame.basetypes.GameMap;
import dungeongame.entitys.BaseEntity;
import dungeongame.entitys.BattleEntity;
import dungeongame.entitys.Enemy;
import dungeongame.entitys.Player;
import dungeongame.menus.PauseMenu;

public class DungeonMapState implements BaseState{
	public GameMap activeMap;
	private Player player;

	public DungeonMapState(){
		GameStateManager.changeForegroundState(new HUDState());
		
		activeMap = new GameMap(25,19);

		player = new Player(new Point(1,1), activeMap);
		activeMap.entitys.add(player);
		activeMap.entitys.add(new Enemy(new Point(1, 4), activeMap));
	}

	public void computeNextFrame() {
		initializeMovements();
		updatePositions();
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
}
