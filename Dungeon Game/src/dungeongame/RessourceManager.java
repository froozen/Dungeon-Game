package dungeongame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
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
	
	private static final int fontHeight = 12, fontWidth = 5;
	
	private static String directoryPath;
	private static HashMap <String, BufferedImage> images;
	private static HashMap <Character, Point> fontPositions;
	private static ArrayList<BufferedImage> tiles;

	static{
		images = new HashMap<String, BufferedImage>();
		directoryPath = determineDirectoryPath();
		System.out.println("Loading files from: " + directoryPath);
		
		fontPositions = new HashMap<Character, Point>();
		
		fontPositions.put('A', new Point(0, 0));
		fontPositions.put('B', new Point(1, 0));
		fontPositions.put('C', new Point(2, 0));
		fontPositions.put('D', new Point(3, 0));
		fontPositions.put('E', new Point(4, 0));
		fontPositions.put('F', new Point(5, 0));
		fontPositions.put('G', new Point(6, 0));
		fontPositions.put('H', new Point(7, 0));
		fontPositions.put('I', new Point(8, 0));
		fontPositions.put('J', new Point(9, 0));
		fontPositions.put('K', new Point(0, 1));
		fontPositions.put('L', new Point(1, 1));
		fontPositions.put('M', new Point(2, 1));
		fontPositions.put('N', new Point(3, 1));
		fontPositions.put('O', new Point(4, 1));
		fontPositions.put('P', new Point(5, 1));
		fontPositions.put('Q', new Point(6, 1));
		fontPositions.put('R', new Point(7, 1));
		fontPositions.put('S', new Point(8, 1));
		fontPositions.put('T', new Point(9, 1));
		fontPositions.put('U', new Point(0, 2));
		fontPositions.put('V', new Point(1, 2));
		fontPositions.put('W', new Point(2, 2));
		fontPositions.put('X', new Point(3, 2));
		fontPositions.put('Y', new Point(4, 2));
		fontPositions.put('Z', new Point(5, 2));
		
		fontPositions.put('a', new Point(0, 3));
		fontPositions.put('b', new Point(1, 3));
		fontPositions.put('c', new Point(2, 3));
		fontPositions.put('d', new Point(3, 3));
		fontPositions.put('e', new Point(4, 3));
		fontPositions.put('f', new Point(5, 3));
		fontPositions.put('g', new Point(6, 3));
		fontPositions.put('h', new Point(7, 3));
		fontPositions.put('i', new Point(8, 3));
		fontPositions.put('j', new Point(9, 3));
		fontPositions.put('k', new Point(0, 4));
		fontPositions.put('l', new Point(1, 4));
		fontPositions.put('m', new Point(2, 4));
		fontPositions.put('n', new Point(3, 4));
		fontPositions.put('o', new Point(4, 4));
		fontPositions.put('p', new Point(5, 4));
		fontPositions.put('q', new Point(6, 4));
		fontPositions.put('r', new Point(7, 4));
		fontPositions.put('s', new Point(8, 4));
		fontPositions.put('t', new Point(9, 4));
		fontPositions.put('u', new Point(0, 5));
		fontPositions.put('v', new Point(1, 5));
		fontPositions.put('w', new Point(2, 5));
		fontPositions.put('x', new Point(3, 5));
		fontPositions.put('y', new Point(4, 5));
		fontPositions.put('z', new Point(5, 5));

		fontPositions.put(' ', new Point(0, 6));
		fontPositions.put('.', new Point(1, 6));
		fontPositions.put('!', new Point(2, 6));
		fontPositions.put('?', new Point(3, 6));
		fontPositions.put(',', new Point(4, 6));
	}

	public static BufferedImage getImage(String imageName){
		if(images.containsKey(imageName))return images.get(imageName);
		else{
			BufferedImage image = null;

			try {
				File imageFile = new File(getImagePath(imageName));
				if(imageFile.isFile())image = ImageIO.read(imageFile);
				else System.out.println("Error: Cannot find file: " + imageFile.getAbsolutePath());

			} catch (IOException e) {e.printStackTrace();}

			return image;
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
	
	public static BufferedImage getFontifiedText(String text, String font){
		int charCount = text.length();
		
		BufferedImage fontMap = getImage("fonts." + font);
		BufferedImage textImage = new BufferedImage(charCount * (fontWidth + 1), fontHeight, BufferedImage.TYPE_INT_ARGB);
		
		
		
		Graphics textImageGraphics = textImage.getGraphics();
		
		BufferedImage fontImage;
		Point fontPosition = new Point();
		if(fontMap != null){
			for(int i = 0; i < charCount; i++){
				if(fontPositions.containsKey(text.charAt(i)))fontPosition = fontPositions.get(text.charAt(i));
				else fontPosition = fontPositions.get('?');
				
				fontImage = fontMap.getSubimage(fontPosition.x * fontWidth, fontPosition.y * fontHeight, fontWidth, fontHeight);
				textImageGraphics.drawImage(fontImage, i * (fontWidth + 1), 0, null);
			}
		}
		textImageGraphics.dispose();
		
		return textImage;
	}
	
	public static BufferedImage getfontifiedScaledText(String text, String font, int scaleFactor){
		int charCount = text.length();
		
		BufferedImage fontMap = getImage("fonts." + font);
		BufferedImage textImage = new BufferedImage(charCount * (fontWidth + 1), fontHeight, BufferedImage.TYPE_INT_ARGB);
		
		
		
		Graphics textImageGraphics = textImage.getGraphics();
		
		BufferedImage fontImage;
		Point fontPosition = new Point();
		if(fontMap != null){
			for(int i = 0; i < charCount; i++){
				if(fontPositions.containsKey(text.charAt(i)))fontPosition = fontPositions.get(text.charAt(i));
				else fontPosition = fontPositions.get('?');
				
				fontImage = fontMap.getSubimage(fontPosition.x * fontWidth, fontPosition.y * fontHeight, fontWidth, fontHeight);
				textImageGraphics.drawImage(fontImage, i * (fontWidth + 1), 0, null);
			}
		}
		textImageGraphics.dispose();
		
		Image scaledTextImage = textImage.getScaledInstance(textImage.getWidth() * scaleFactor, textImage.getHeight() * scaleFactor, Image.SCALE_DEFAULT);
		BufferedImage finalTextImage = new BufferedImage(scaledTextImage.getWidth(null), scaledTextImage.getWidth(null), BufferedImage.TYPE_INT_ARGB);
		finalTextImage.getGraphics().drawImage(scaledTextImage, 0, 0, null);
		
		return finalTextImage;
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
	
	private static String getImagePath(String location){
		location = location.replace('.', File.separatorChar);
		location = directoryPath + "res" + File.separator + location + ".png";
		return location;
	}
}
