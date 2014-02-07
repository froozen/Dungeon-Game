package dungeongame.basetypes;

import java.awt.Point;
import java.awt.Rectangle;

public class DungeonRoom {
	public Rectangle space;
	
	public DungeonRoom(Point center){
		this.space = new Rectangle(center.x, center.y, 1, 1);
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
