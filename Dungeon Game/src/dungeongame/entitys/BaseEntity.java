package dungeongame.entitys;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

import dungeongame.GameVariables;
import dungeongame.RessourceManager;
import dungeongame.basetypes.GameMap;

public abstract class BaseEntity implements NonTileObject {
	final double speed = 2.5;

	protected Point position;
	protected double x, y;
	public String spriteLocation;
	public BufferedImage sprite;
	public GameMap locationMap;
	public Direction direction;
	public int cyclePhase;
	public boolean moving;

	protected int lastCyclePhase;
	protected double remainingFreezeTime;

	public BaseEntity(Point position, GameMap locationMap){
		this.locationMap = locationMap;
		this.position = position;
		this.x = position.x;
		this.y = position.y;

		direction = Direction.DOWN;
		cyclePhase = 0;
		lastCyclePhase = 2;
		moving = false;
	}

	public enum Direction{
		DOWN, RIGHT, UP, LEFT
	}

	public void drawMe(Graphics g){
		loadSprite();
		g.drawImage(sprite, (int) (x * RessourceManager.tileSize - ((sprite.getWidth() - RessourceManager.tileSize) / 2)), (int) ((y + 1) * RessourceManager.tileSize - sprite.getHeight()), null);
	}

	public void moveDirection(Direction movementDirection){
		direction = movementDirection;
		Point nextPosition = new Point(position);

		if(movementDirection == Direction.RIGHT){
			nextPosition.x++;

			if(nextPosition.x > -1 && nextPosition.x < locationMap.width){
				if(!locationMap.isOccupied(nextPosition)){
					position = nextPosition;
					moving = true;
				}
			}
		}
		else if(movementDirection == Direction.LEFT){
			nextPosition.x--;

			if(nextPosition.x > -1 && nextPosition.x < locationMap.width){
				if(!locationMap.isOccupied(nextPosition)){
					position = nextPosition;
					moving = true;
				}
			}
		}
		else if(movementDirection == Direction.DOWN){
			nextPosition.y++;

			if(nextPosition.y > -1 && nextPosition.y < locationMap.height){
				if(!locationMap.isOccupied(nextPosition)){
					position = nextPosition;
					moving = true;
				}
			}
		}
		else if(movementDirection == Direction.UP){
			nextPosition.y--;

			if(nextPosition.y > -1 && nextPosition.y < locationMap.height){
				if(!locationMap.isOccupied(nextPosition)){
					position = nextPosition;
					moving = true;
				}
			}
		}
	}

	public void computeNextPosition(){
		if (moving){
			if(remainingFreezeTime > 0){
				remainingFreezeTime -= GameVariables.timeSinceLastFrame;
				if(remainingFreezeTime < 0)moving = false;
			}
			else if(direction == Direction.UP){
				if(y>position.y){
					y -= GameVariables.timeSinceLastFrame * speed;
				}
				else {
					moving = false;
					y = position.y;
				}
			}
			else if(direction == Direction.LEFT){
				if(x>position.x){
					x -=GameVariables.timeSinceLastFrame * speed;
				}
				else {
					moving = false;
					x = position.x;
				}
			}
			else if(direction == Direction.DOWN){
				if(y<position.y){
					y +=GameVariables.timeSinceLastFrame * speed;
				}
				else {
					moving = false;
					y = position.y;
				}
			}
			else if(direction == Direction.RIGHT){
				if(x<position.x){
					x +=GameVariables.timeSinceLastFrame * speed;
				}
				else {
					moving = false;
					x = position.x;
				}
			}
		}
	}

	protected void loadSprite(){
		if(spriteLocation != null){
			if(direction == Direction.RIGHT || direction == Direction.LEFT){
				if(x - ((int)x) + 0.25 > 0.50){
					if(cyclePhase != lastCyclePhase){
						if(lastCyclePhase == 1) lastCyclePhase = 2;
						else lastCyclePhase = 1;

						cyclePhase = lastCyclePhase;
					}
				}
				else cyclePhase = 0;
			}
			else if(direction == Direction.UP || direction == Direction.DOWN){
				if(y - ((int)y) + 0.25 > 0.50){
					if(cyclePhase != lastCyclePhase){
						if(lastCyclePhase == 1) lastCyclePhase = 2;
						else lastCyclePhase = 1;

						cyclePhase = lastCyclePhase;
					}
				}
				else cyclePhase = 0;
			}

			sprite = RessourceManager.getCharacterSprite(spriteLocation, direction, cyclePhase);
		}
	}

	public Point getPosition(){
		return position;
	}
	
	public boolean isSolid(){
		return true;
	}
	
	public double getX(){
		return x;
	}
	
	public double getY(){
		return y;
	}
}