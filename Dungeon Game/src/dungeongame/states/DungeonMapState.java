package dungeongame.states;

import java.awt.Graphics;
import java.awt.Point;

import dungeongame.RessourceManager;
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
		for(int x = 0; x < activeMap.tiles.length; x++){
			for(int y = 0; y < activeMap.tiles[x].length; y++){
				g.drawImage(RessourceManager.getTile(activeMap.tiles[x][y]), x * RessourceManager.tileSize, y * RessourceManager.tileSize, null);
			}
		}
		
		//Draw debug lines
		for(int x = 0; x < activeMap.tiles.length;x++){
			g.drawLine(x * RessourceManager.tileSize, 0, x * RessourceManager.tileSize, activeMap.tiles[x].length * RessourceManager.tileSize);
		}
		for(int y = 0; y < activeMap.tiles[0].length;y++){
			g.drawLine(0, y * RessourceManager.tileSize, activeMap.tiles.length * RessourceManager.tileSize, y * RessourceManager.tileSize);
		}
		
		
		for(BaseEntity entity:activeMap.entitys){
			entity.drawMe(g);
		}
	}

	public void computeNextFrame() {
		
	}

}
