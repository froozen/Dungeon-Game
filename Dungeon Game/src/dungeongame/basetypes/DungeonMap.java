package dungeongame.basetypes;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import dungeongame.RessourceManager;
import dungeongame.entitys.BaseEntity;
import dungeongame.entitys.Player;

public class DungeonMap extends GameMap{
	public ArrayList<DungeonRoom> rooms;
	public ArrayList<Point> links;

	public DungeonMap(int width, int height) {
		super(width, height);

		boolean validLayout = true;
		while(validLayout){
			createStartRooms();
			expandRooms();

			validLayout = !checkRoomValidity();
		}
		createLinks();
		updateTiles();
		fillRooms();
	}
	
	public void drawMe(Graphics g){
		DungeonRoom targetRoom = getRoomThatContains(getPlayer());
		if(targetRoom != null){
			ArrayList<BaseEntity> targetEntitys = getEntitysIn(targetRoom);
			Rectangle targetRectangle = new Rectangle(targetRoom.space.x - 1, targetRoom.space.y -1, targetRoom.space.width + 2, targetRoom.space.height +2);
			
			for(int x = targetRectangle.x; x < targetRectangle.x + targetRectangle.width; x++){
				for(int y = targetRectangle.y ; y < targetRectangle.y + targetRectangle.height; y++){
					g.drawImage(RessourceManager.getTile(tiles[x][y]), x * RessourceManager.tileSize, y * RessourceManager.tileSize, null);
				}
			}
			
			for(BaseEntity e:targetEntitys){
				e.drawMe(g);
			}
		}
		else{
			Point playerPosition = getPlayer().position;
			
			g.drawImage(RessourceManager.getTile(tiles[playerPosition.x][playerPosition.y]), playerPosition.x * RessourceManager.tileSize, playerPosition.y * RessourceManager.tileSize, null);
			g.drawImage(RessourceManager.getTile(tiles[playerPosition.x - 1][playerPosition.y]), (playerPosition.x - 1) * RessourceManager.tileSize, playerPosition.y * RessourceManager.tileSize, null);
			g.drawImage(RessourceManager.getTile(tiles[playerPosition.x + 1][playerPosition.y]), (playerPosition.x + 1) * RessourceManager.tileSize, playerPosition.y * RessourceManager.tileSize, null);
			g.drawImage(RessourceManager.getTile(tiles[playerPosition.x][playerPosition.y - 1]), playerPosition.x * RessourceManager.tileSize, (playerPosition.y - 1) * RessourceManager.tileSize, null);
			g.drawImage(RessourceManager.getTile(tiles[playerPosition.x][playerPosition.y + 1]), playerPosition.x * RessourceManager.tileSize, (playerPosition.y + 1) * RessourceManager.tileSize, null);
			
			getPlayer().drawMe(g);
		}
	}
	
	public boolean roomsContain(Point point){
		for(DungeonRoom room:rooms){
			if(room.space.contains(point))return true;
		}

		return false;
	}

	public boolean roomsContain(Rectangle rectangle){
		for(DungeonRoom room:rooms){
			if(room.space.intersects(rectangle))return true;
		}

		return false;
	}
	
	public DungeonRoom getRoomThatContains(BaseEntity  e){
		for(DungeonRoom room:rooms){
			if(room.space.contains(e.position))return room;
		}
		return null;
	}
	
	public ArrayList<BaseEntity> getEntitysIn(DungeonRoom room){
		ArrayList<BaseEntity> returnList = new ArrayList<BaseEntity>();
		
		for(BaseEntity e:entitys){
			if(room.space.contains(e.position))returnList.add(e);
		}
		
		if(returnList.size() > 0)return returnList;
		else return null;
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

	private void createLinks(){
		links = new ArrayList<Point>();

		for(DungeonRoom room:rooms){
			Rectangle testRectangle = new Rectangle(room.space.x - 2, room.space.y - 2, room.space.width + 4, room.space.height + 4);
			ArrayList<DungeonRoom> linkCandidates = getRoomsThatIntersect(testRectangle);

			if(linkCandidates.size() > 0){
				for(DungeonRoom linkRoom:linkCandidates){
					if(linkRoom != room && !room.linkedRooms.contains(linkRoom)){
						Rectangle linkRoomExpandedSpace = new Rectangle(linkRoom.space.x - 2, linkRoom.space.y - 2, linkRoom.space.width + 4, linkRoom.space.height + 4);
						Rectangle intersectionRectangle = linkRoomExpandedSpace.intersection(testRectangle);

						if(intersectionRectangle.height == 3){
							intersectionRectangle.x += 2;
							intersectionRectangle.y += 1;
							intersectionRectangle.width -= 4;
							intersectionRectangle.height -= 2;

							if(intersectionRectangle.width > 2){
								Point linkPoint = new Point(intersectionRectangle.x + (intersectionRectangle.width / 2)/* - (intersectionRectangle.width % 2)*/, intersectionRectangle.y);
								links.add(linkPoint);

								room.linkedRooms.add(linkRoom);
								linkRoom.linkedRooms.add(room);
							}

						}
						else if(intersectionRectangle.width == 3){
							intersectionRectangle.x += 1;
							intersectionRectangle.y += 2;
							intersectionRectangle.width -= 2;
							intersectionRectangle.height -= 4;

							if(intersectionRectangle.height > 2){
								Point linkPoint = new Point(intersectionRectangle.x, intersectionRectangle.y + (intersectionRectangle.height / 2)/* - (intersectionRectangle.height % 2)*/);
								links.add(linkPoint);

								room.linkedRooms.add(linkRoom);
								linkRoom.linkedRooms.add(room);
							}
						}
					}
				}
			}
		}
	}


	private void updateTiles(){
		for(int x = 0; x < tiles.length; x++){
			for(int y = 0; y < tiles[x].length; y++){
				if(!roomsContain(new Point(x, y))){
					if(!links.contains(new Point(x, y))){
						tiles[x][y] = 2;
						occupied[x][y] = true;
					}
				}
			}
		}
	}

	private boolean checkRoomValidity(){
		for(DungeonRoom room:rooms){
			if(room.space.width < 4)return false;
			if(room.space.height < 4)return false;
		}
		return true;
	}

	private ArrayList<DungeonRoom> getRoomsThatIntersect(Rectangle space){
		ArrayList<DungeonRoom> intersectingRooms = new ArrayList<DungeonRoom>();

		for(DungeonRoom room:rooms){
			if(room.space.intersects(space))intersectingRooms.add(room);
		}
		return intersectingRooms;
	}
	
	private void fillRooms(){
		Random random = new Random();
		
		DungeonRoom playerSpawnRoom = rooms.get(random.nextInt(rooms.size()));
		Point playerSpawnPoint = new Point(playerSpawnRoom.space.x + random.nextInt(playerSpawnRoom.space.width), playerSpawnRoom.space.y + random.nextInt(playerSpawnRoom.space.height));
		Player player = new Player(playerSpawnPoint, this);
		entitys.add(player);
	}
}
