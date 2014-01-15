package dungeongame.entitys;

import java.awt.Point;

import dungeongame.RessourceManager;
import dungeongame.basetypes.GameMap;

public class Player extends BaseEntity{
	
	
	public Player(Point position, GameMap locationMap){
		this.locationMap = locationMap;
		this.position = position;
		this.x = position.x;
		this.y = position.y;
		
		direction = Direction.DOWN;
		cyclePhase = 0;
		
		spriteLocation = "sprites.player";
		sprite = RessourceManager.getCharacterSprite(spriteLocation, direction, cyclePhase);
	}
}
