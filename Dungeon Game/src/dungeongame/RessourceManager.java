package dungeongame;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

import dungeongame.entitys.BaseEntity.Direction;

public class RessourceManager {
	public static final int tileSize = 32;
	
	private static String directoryPath;
	private static HashMap <String, BufferedImage> images;
	private static HashMap <String, String> imageLocations;
	private static ArrayList<BufferedImage> tiles;

	static{
		images = new HashMap<String, BufferedImage>();
		imageLocations = new HashMap<String, String>();

		imageLocations.put("misc.title", "misc" + File.separator + "title.png");
		imageLocations.put("sprites.player", "sprites" + File.separator + "player.png");

		directoryPath = determineDirectoryPath();
		System.out.println("Loading files from: " + directoryPath);
	}

	public static BufferedImage getImage(String imageName){
		if(images.containsKey(imageName))return images.get(imageName);
		else if(imageLocations.containsKey(imageName)){
			BufferedImage image = null;

			try {
				File imageFile = new File(directoryPath + "res" + File.separator + imageLocations.get(imageName));
				if(imageFile.isFile())image = ImageIO.read(imageFile);
				else System.out.println("Error: Cannot find file: " + imageFile.getAbsolutePath());

			} catch (IOException e) {e.printStackTrace();}

			return image;
		}
		else{
			System.out.println("Error: Cannot find path to '" + imageName + "' in imageLocations");
			return null; 
		}
	}

	public static BufferedImage getTile(int index){
		if(tiles == null){
			System.out.println("Error: No tileset loaded!");
			return null;
		}
		else if(tiles.size() < index){
			System.out.println("Error: Tile index out of range: " + index + " size: " + tiles.size());
			return null;
		}
		else return tiles.get(index - 1);
	}

	public static void loadTileSet(String tileSetName){
		try {
			File tileFile = new File(directoryPath + File.separator + "res" + File.separator + "tiles" + File.separator + tileSetName + ".png");
			if(tileFile.isFile()){
				tiles = new ArrayList<BufferedImage>();
				BufferedImage tileSetImage = ImageIO.read(tileFile);
				
				for(int y = 0; y < tileSetImage.getHeight() / tileSize; y++){
					for(int x = 0; x < tileSetImage.getWidth() / tileSize; x++){
						tiles.add(tileSetImage.getSubimage(x * tileSize, y * tileSize, tileSize, tileSize));
					}
				}
			}
			else System.out.println("Error: Cannot find file: " + tileFile.getAbsolutePath());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static BufferedImage getCharacterSprite(String spriteName, Direction direction, int cyclePhase){
		BufferedImage sprite = getImage(spriteName);
		
		int spriteWidth = sprite.getWidth() / 3;
		int spriteHeight = sprite.getHeight() / 4;
		
		int directionNumber = 0;
		if(direction == Direction.DOWN)directionNumber = 0;
		else if(direction == Direction.RIGHT)directionNumber = 1;
		else if(direction == Direction.UP)directionNumber = 2;
		else if(direction == Direction.LEFT)directionNumber = 3;
		
		sprite = sprite.getSubimage(cyclePhase * spriteWidth, directionNumber * spriteHeight, spriteWidth, spriteHeight);
		
		return sprite;
	}

	private static String determineDirectoryPath(){
		String path = "";

		try {path = new File(RessourceManager.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getAbsolutePath();
		} catch (URISyntaxException e) {e.printStackTrace();}

		if(path.endsWith(File.separator + "bin")){
			path += File.separator + "..";
		}
		else if(path.endsWith(".jar")){
			path = path.substring(0, path.lastIndexOf(File.separator));
		}

		path += File.separator;
		return path;
	}
}
