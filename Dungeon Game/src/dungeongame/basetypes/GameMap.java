package dungeongame.basetypes;

public class GameMap {
	public int height, width;
	public int [][] tiles;
	
	public GameMap(int width, int height){
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
