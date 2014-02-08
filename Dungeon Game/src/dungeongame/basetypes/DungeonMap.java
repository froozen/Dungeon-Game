package dungeongame.basetypes;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

public class DungeonMap extends GameMap{
	public ArrayList<DungeonRoom> rooms;

	public DungeonMap(int width, int height) {
		super(width, height);
		
		boolean validLayout = true;
		while(validLayout){
			createStartRooms();
			expandRooms();

			validLayout = !checkRoomValidity();
		}
		updateTiles();
		
	}

	private void createStartRooms(){
		ArrayList<Point> points = new ArrayList<Point>();
		Random random = new Random();

		while(points.size() < 6){
			Point center = new Point();

			center.x = random.nextInt(width - 2) + 1;
			center.y = random.nextInt(height - 2) + 1;

			boolean invalidPoint = false;
			for(int x = center.x - 2; x < center.x + 3; x++){
				for(int y = center.y - 2; y < center.y + 3; y++){
					for(Point point:points){
						if(point.x == x && point.y == y)invalidPoint = true;
					}
				}
			}
			
			if(!invalidPoint)points.add(center);
		}

		rooms = new ArrayList<DungeonRoom>();
		for(Point point:points){
			rooms.add(new DungeonRoom(point));
		}
	}

	private void expandRooms(){
		boolean expandable = true;
		while(expandable){
			expandable = false;

			for(DungeonRoom room:rooms){
				if(tryExpandingRoom(room))expandable = true;
			}
		}
	}

	private void updateTiles(){
		for(int x = 0; x < tiles.length; x++){
			for(int y = 0; y < tiles[x].length; y++){
				if(!roomsContain(new Point(x, y)))tiles[x][y] = 2;
			}
		}
	}

	private boolean tryExpandingRoom(DungeonRoom room){
		boolean expanded = false;
		if(tryExpandingRoomUp(room))expanded = true;
		if(tryExpandingRoomDown(room))expanded = true;
		if(tryExpandingRoomToTheLeft(room))expanded = true;
		if(tryExpandingRoomToTheRight(room))expanded = true;

		return expanded;
	}

	private boolean tryExpandingRoomUp(DungeonRoom room){
		if(room.space.y > 1){
			Rectangle testRectangle = new Rectangle(room.space.x - 1, room.space.y -2, room.space.width + 2, 2);
			if(!roomsContain(testRectangle)){
				room.expandUp();
				return true;
			}
		}
		return false;
	}

	private boolean tryExpandingRoomDown(DungeonRoom room){
		if(room.space.y + room.space.height < height - 1){
			Rectangle testRectangle = new Rectangle(room.space.x - 1, room.space.y + room.space.height, room.space.width + 2, 2);
			if(!roomsContain(testRectangle)){
				room.expandDown();
				return true;
			}
		}
		return false;
	}
		
	private boolean tryExpandingRoomToTheLeft(DungeonRoom room){
		if(room.space.x > 1){
			Rectangle testRectangle = new Rectangle(room.space.x - 2, room.space.y - 1, 2, room.space.height + 2);
			if(!roomsContain(testRectangle)){
				room.expandToTheLeft();
				return true;
			}
		}
		return false;
	}

	private boolean tryExpandingRoomToTheRight(DungeonRoom room){
		if(room.space.x + room.space.width < width -1){
			Rectangle testRectangle = new Rectangle(room.space.x + room.space.width, room.space.y - 1, 2, room.space.height + 2);
			if(!roomsContain(testRectangle)){
				room.expandToTheRight();
				return true;
			}
		}
		return false;
	}

	private boolean roomsContain(Point point){
		for(DungeonRoom room:rooms){
			if(room.space.contains(point))return true;
		}

		return false;
	}
	
	private boolean roomsContain(Rectangle rectangle){
		for(DungeonRoom room:rooms){
			if(room.space.intersects(rectangle))return true;
		}

		return false;
	}
	
	private boolean checkRoomValidity(){
		for(DungeonRoom room:rooms){
			if(room.space.width < 4)return false;
			if(room.space.height < 4)return false;
		}
		return true;
	}
}
