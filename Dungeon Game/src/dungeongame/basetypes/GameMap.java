package dungeongame.basetypes;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import dungeongame.RessourceManager;
import dungeongame.entitys.NonTileObject;
import dungeongame.entitys.Player;
import dungeongame.particles.BaseParticle;

public class GameMap {
	public int height, width;
	public int [][] tiles;
	public ArrayList<NonTileObject> nonTileObjects;
	public ArrayList<BaseParticle> particles;

	public GameMap(int width, int height){
		nonTileObjects = new ArrayList<NonTileObject>();
		particles = new ArrayList<BaseParticle>();

		this.height = height;
		this.width = width;
		tiles = new int[width][height];

		for(int x = 0; x<width; x++){
			for(int y = 0; y<height; y++){
				tiles[x][y] = 1;
			}
		}
	}

	public void drawMe(Graphics g){
		for(int x = 0; x < tiles.length; x++){
			for(int y = 0; y < tiles[x].length; y++){
				g.drawImage(RessourceManager.getTile(tiles[x][y]), x * RessourceManager.tileSize, y * RessourceManager.tileSize, null);
			}
		}

		// //Draw debug collision
		//		g.setColor(Color.red);
		//		for(int x = 0; x < occupied.length; x++){
		//			for(int y = 0; y < occupied[x].length; y++){
		//				if(occupied[x][y]){
		//					g.fillRect(x * RessourceManager.tileSize, y * RessourceManager.tileSize, RessourceManager.tileSize, RessourceManager.tileSize);
		//				}
		//			}
		//		}

		// //Draw debug lines
		//		g.setColor(Color.black);
		//		for(int x = 0; x < tiles.length;x++){
		//			g.drawLine(x * RessourceManager.tileSize, 0, x * RessourceManager.tileSize, tiles[x].length * RessourceManager.tileSize);
		//		}
		//		for(int y = 0; y < tiles[0].length;y++){
		//			g.drawLine(0, y * RessourceManager.tileSize, tiles.length * RessourceManager.tileSize, y * RessourceManager.tileSize);
		//		}
		//		
		for(NonTileObject nto:nonTileObjects)nto.drawMe(g);

		for(BaseParticle particle:particles)particle.drawMe(g);

	}

	public Player getPlayer(){
		for(NonTileObject nto:nonTileObjects){
			if(nto instanceof Player)return (Player)nto;
		}
		return null;
	}

	public void updateParticles(){		
		ArrayList<BaseParticle> removeParticles = new ArrayList<BaseParticle>();
		for(BaseParticle particle:particles){
			particle.updatePosition();
			if(particle.remove)removeParticles.add(particle);
		}

		//Remove "dead" particles
		for(BaseParticle particle:removeParticles)particles.remove(particle);
	}

	public ArrayList<NonTileObject> getNonTileObjectsAt(Point position){
		ArrayList<NonTileObject> ntoAt = new ArrayList<NonTileObject>();
		for(NonTileObject nto:nonTileObjects)
			if(nto.getPosition().equals(position))ntoAt.add(nto);
		return ntoAt;
	}

	public boolean isOccupied(Point position){
		for(NonTileObject nto:nonTileObjects){
			if(nto.getPosition().equals(position) && nto.isSolid()){
				return true;
			}
		}
		return false;
	}
}
