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
import dungeongame.entitys.NonTileObject;
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
		sortNonTileObjects();
		cleanNonTileObjects();
		
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

		for(NonTileObject nto:activeMap.nonTileObjects){
			if(nto instanceof BaseEntity)
				if(((BaseEntity)nto).moving)anythingMoving = true;
		}

		if(!anythingMoving){
			player.update();

			if(player.moving){
				ArrayList<NonTileObject> relevantNonTileObjects = activeMap.getNonTileObjectsIn(activeMap.getRoomThatContains(player));
				if(relevantNonTileObjects != null){
					for(NonTileObject nto:relevantNonTileObjects){
						if(nto != player){
							nto.update();
						}
					}
				}
			}
		}
	}

	private void updateScreenPositions(){
		ArrayList<NonTileObject> relevantNonTileObjects = activeMap.getNonTileObjectsIn(activeMap.getRoomThatContains(player));
		if(relevantNonTileObjects != null){
			for(NonTileObject nto:relevantNonTileObjects){
				if(nto instanceof BaseEntity){
					((BaseEntity)nto).computeNextPosition();
				}
			}
		}
		else player.computeNextPosition();
	}
	
	private void cleanNonTileObjects(){
		ArrayList<NonTileObject> removeList = new ArrayList<NonTileObject>();
		for(NonTileObject nto:activeMap.nonTileObjects){
			if(nto instanceof BattleEntity){
				BattleEntity battleEntity = (BattleEntity)nto;
				if(battleEntity.stats.hp < 1){
					removeList.add(battleEntity);
				}
			}
		}

		for(NonTileObject nto:removeList){
			if(nto instanceof BattleEntity)
				((BattleEntity)nto).uponDeath();
			activeMap.nonTileObjects.remove(nto);
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
	
	private void sortNonTileObjects(){
		Collections.sort(activeMap.nonTileObjects, new Comparator<NonTileObject>() {
			public int compare(NonTileObject nto1, NonTileObject nto2){
				return Double.compare(nto1.getY(), nto2.getY());
			}
		});
	}
}