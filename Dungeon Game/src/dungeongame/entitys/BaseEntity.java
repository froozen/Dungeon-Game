package dungeongame.entitys;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

import dungeongame.RessourceManager;
import dungeongame.basetypes.GameMap;

public abstract class BaseEntity {
	final double speed = 2.5;

	public Point position;
	public double x, y;
	public String spriteLocation;
	public BufferedImage sprite;
	public GameMap locationMap;
	public Direction direction;
	public int cyclePhase;
	public boolean moving;

	private static long lastTime;
	public static double timeSinceLastFrame;
	
	public abstract void initializeMovement();

	static{
		lastTime = System.currentTimeMillis();
	}

	public void drawMe(Graphics g){
		sprite = RessourceManager.getCharacterSprite(spriteLocation, direction, cyclePhase);
		g.drawImage(sprite, (int) (x * RessourceManager.tileSize - ((sprite.getWidth() - RessourceManager.tileSize) / 2)), (int) ((y + 1) * RessourceManager.tileSize - sprite.getHeight()), null);
	}

	public enum Direction{
		DOWN, RIGHT, UP, LEFT
	}

	public static void updateTimeSinceLastFrame(){
		timeSinceLastFrame = ((double)(System.currentTimeMillis() - lastTime) / 1000);
		lastTime = System.currentTimeMillis();
	}
	
	public void moveDirection(Direction movementDirection){
		direction = movementDirection;
		
		if(movementDirection == Direction.RIGHT){
			position.x++;
			
			if(!locationMap.occupied[position.x][position.y]){
				moving = true;
				locationMap.occupied[position.x - 1][position.y] = false;
				locationMap.occupied[position.x][position.y] = true;
			}
			else position.x--;
			
		}
		else if(movementDirection == Direction.LEFT){
			position.x--;
							
			if(!locationMap.occupied[position.x][position.y]){
				moving = true;
				locationMap.occupied[position.x + 1][position.y] = false;
				locationMap.occupied[position.x][position.y] = true;
			}
			else position.x++;
		}
		else if(movementDirection == Direction.DOWN){
			position.y++;
			
			if(!locationMap.occupied[position.x][position.y]){
				moving = true;
				locationMap.occupied[position.x][position.y - 1] = false;
				locationMap.occupied[position.x][position.y] = true;
			}
			else position.y--;
		}
		else if(movementDirection == Direction.UP){
			position.y--;
			
			if(!locationMap.occupied[position.x][position.y]){
				moving = true;
				locationMap.occupied[position.x][position.y + 1] = false;
				locationMap.occupied[position.x][position.y] = true;
			}
			else position.y++;
		}
	}

	public void computeNextPosition(){
		if (moving){
			if(direction == Direction.UP){
				if(y>position.y){
					y -= timeSinceLastFrame * speed;
				}
				if(!(y>position.y)){
					moving = false;
					y = position.y;
				}
			}
			else if(direction == Direction.LEFT){
				if(x>position.x){
					x -= timeSinceLastFrame * speed;
				}
				if(!(x>position.x)){
					moving = false;
					x = position.x;
				}
			}
			else if(direction == Direction.DOWN){
				if(y<position.y){
					y += timeSinceLastFrame * speed;
				}
				if(!(y<position.y)){
					moving = false;
					y = position.y;
				}
			}
			else if(direction == Direction.RIGHT){
				if(x<position.x){
					x += timeSinceLastFrame * speed;
				}
				if(!(x<position.x)){
					moving = false;
					x = position.x;
				}
			}

			if(direction == Direction.RIGHT || direction == Direction.LEFT){
				if(x - ((int)x) + 0.25 > 0.50)cyclePhase = 1;
				else cyclePhase = 0;
			}
			else if(direction == Direction.UP || direction == Direction.DOWN){
				if(y - ((int)y) + 0.25 > 0.50)cyclePhase = 1;
				else cyclePhase = 0;
			}
			
		}
	}
}