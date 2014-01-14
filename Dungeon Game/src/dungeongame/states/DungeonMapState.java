package dungeongame.states;

import java.awt.Graphics;

import dungeongame.RessourceManager;
import dungeongame.basetypes.GameMap;

public class DungeonMapState extends BaseState{
	public GameMap activeMap;

	public DungeonMapState(){
		activeMap = new GameMap(25,20);
	}
	
	public void drawMe(Graphics g) {
		for(int x = 0; x < activeMap.tiles.length; x++){
			for(int y = 0; y < activeMap.tiles[x].length; y++){
				g.drawImage(RessourceManager.getTile(activeMap.tiles[x][y]), x * RessourceManager.tileSize, y * RessourceManager.tileSize, null);
			}
		}
	}

	public void computeNextFrame() {
		
	}

}
