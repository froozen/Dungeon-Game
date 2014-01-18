package dungeongame.basetypes;

import java.awt.Graphics;
import java.util.ArrayList;

import dungeongame.RessourceManager;
import dungeongame.entitys.BaseEntity;

public class GameMap {
	public int height, width;
	public int [][] tiles;
	public ArrayList<BaseEntity> entitys;
	
	public GameMap(int width, int height){
		entitys = new ArrayList<BaseEntity>();
		
		this.height = height;
		this.width = width;
		tiles = new int[width][height];
		
		for(int x = 0; x<width; x++){
			for(int y = 0; y<height; y++){
				tiles[x][y] = 1;
			}
		}
	}
	
	public void drawMe(Graphics g){
		for(int x = 0; x < tiles.length; x++){
			for(int y = 0; y < tiles[x].length; y++){
				g.drawImage(RessourceManager.getTile(tiles[x][y]), x * RessourceManager.tileSize, y * RessourceManager.tileSize, null);
			}
		}
		
		//Draw debug lines
		for(int x = 0; x < tiles.length;x++){
			g.drawLine(x * RessourceManager.tileSize, 0, x * RessourceManager.tileSize, tiles[x].length * RessourceManager.tileSize);
		}
		for(int y = 0; y < tiles[0].length;y++){
			g.drawLine(0, y * RessourceManager.tileSize, tiles.length * RessourceManager.tileSize, y * RessourceManager.tileSize);
		}
		
		
		for(BaseEntity entity:entitys){
			entity.drawMe(g);
		}
	}
}
