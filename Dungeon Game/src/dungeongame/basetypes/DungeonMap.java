package dungeongame.basetypes;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class DungeonMap extends GameMap{
	public ArrayList<DungeonRoom> rooms;

	public DungeonMap(int width, int height) {
		super(width, height);

		createStartRooms();
		expandRooms();

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
		boolean expanded = false;

		int y = room.space.y - 1;
		if(y > 0){
			expanded = true;
			for(int x = room.space.x; x < room.space.x + room.space.width; x++){
				if(roomsContain(new Point(x , y)))expanded = false;
			}

			y--;
			for(int x = room.space.x; x < room.space.x + room.space.width; x++){
				if(roomsContain(new Point(x , y)))expanded = false;
			}
		}
		if(expanded){
			room.expandUp();
		}

		return expanded;
	}

	private boolean tryExpandingRoomDown(DungeonRoom room){
		boolean expanded = false;

		int y = room.space.y + room.space.height;
		if(y < height - 1){
			expanded = true;
			for(int x = room.space.x; x < room.space.x + room.space.width; x++){
				if(roomsContain(new Point(x , y)))expanded = false;
			}

			y++;
			for(int x = room.space.x; x < room.space.x + room.space.width; x++){
				if(roomsContain(new Point(x , y)))expanded = false;
			}
		}
		if(expanded){
			room.expandDown();
		}

		return expanded;
	}

	private boolean tryExpandingRoomToTheLeft(DungeonRoom room){
		boolean expanded = false;

		int x = room.space.x - 1;
		if(x > 0){
			expanded = true;
			for(int y = room.space.y; y < room.space.y + room.space.height; y++){
				if(roomsContain(new Point(x , y)))expanded = false;
			}

			x--;
			for(int y = room.space.y; y < room.space.y + room.space.height; y++){
				if(roomsContain(new Point(x , y)))expanded = false;
			}
		}
		if(expanded){
			room.expandToTheLeft();
		}

		return expanded;
	}

	private boolean tryExpandingRoomToTheRight(DungeonRoom room){
		boolean expanded = false;

		int x = room.space.x + room.space.width + 1;
		if(x < width){
			expanded = true;
			for(int y = room.space.y; y < room.space.y + room.space.height; y++){
				if(roomsContain(new Point(x , y)))expanded = false;
			}

			x++;
			for(int y = room.space.y; y < room.space.y + room.space.height; y++){
				if(roomsContain(new Point(x , y)))expanded = false;
			}
		}
		if(expanded){
			room.expandToTheRight();
		}

		return expanded;
	}

	private boolean roomsContain(Point point){
		for(DungeonRoom room:rooms){
			if(room.space.contains(point))return true;
		}

		return false;
	}
}
