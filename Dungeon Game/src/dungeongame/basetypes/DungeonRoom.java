package dungeongame.basetypes;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

public class DungeonRoom {
	public Rectangle space;
	public ArrayList<DungeonRoom>linkedRooms;
	public boolean visited;
	
	public DungeonRoom(Point center){
		this.space = new Rectangle(center.x, center.y, 1, 1);
		linkedRooms = new ArrayList<DungeonRoom>();
		visited = false;
	}
	
	public void expandToTheRight(){
		space.width++;
	}
	
	public void expandToTheLeft(){
		space.width++;
		space.x--;
	}
	
	public void expandDown(){
		space.height++;
	}
	
	public void expandUp(){
		space.height++;
		space.y--;
	}
}
