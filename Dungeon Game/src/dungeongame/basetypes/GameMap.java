package dungeongame.basetypes;

import java.util.ArrayList;

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
}
