package dungeongame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
	private static HashMap <Character, Point> fontPositions;
	private static ArrayList<BufferedImage> tiles;

	static{
		images = new HashMap<String, BufferedImage>();
		directoryPath = determineDirectoryPath();
		System.out.println("Loading files from: " + directoryPath);

		loadFontPositions();
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
		int fontWidth = fontMap.getWidth() / 10;
		int fontHeight = fontMap.getHeight() / 9;
		
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
	
	public static BufferedImage getFontifiedBreakingText(String text, String font, int maxWidth){
		

		BufferedImage fontMap = getImage("fonts." + font);
		int fontWidth = fontMap.getWidth() / 10;
		int fontHeight = fontMap.getHeight() / 9;
		
		int charCount = maxWidth / (fontWidth + 1);
				
		String[] textSegments = text.split(" ");
		ArrayList<String> textBlocks = new ArrayList<String>();
		
		String nextTextBlock = "";
		for(int i = 0; i < textSegments.length; i++){
			if((nextTextBlock + textSegments[i]).length() + 1 > charCount){
				textBlocks.add(nextTextBlock);
				
				nextTextBlock = textSegments[i];
				while(nextTextBlock.length() > charCount){
					String addString = nextTextBlock.substring(0, charCount);
					textBlocks.add(addString);
					nextTextBlock = nextTextBlock.substring(charCount);
				}
			}
			else if(!nextTextBlock.equals(""))nextTextBlock += " " + textSegments[i];
			else nextTextBlock = textSegments[i];
		}
		if(!nextTextBlock.equals(""))textBlocks.add(nextTextBlock);
		
		BufferedImage textImage = new BufferedImage(charCount * (fontWidth + 1), textBlocks.size() * (fontHeight + 2), BufferedImage.TYPE_INT_ARGB);
		Graphics textImageGraphics = textImage.getGraphics();
		
		for(int i = 0; i < textBlocks.size(); i++){
			textImageGraphics.drawImage(getFontifiedText(textBlocks.get(i), font), 0, i * (fontHeight + 2), null);
		}
		
		textImageGraphics.dispose();
		return textImage;
	}

	public static BufferedImage getFontifiedScaledText(String text, String font, int scaleFactor){
		int charCount = text.length();

		BufferedImage fontMap = getImage("fonts." + font);
		int fontWidth = fontMap.getWidth() / 10;
		int fontHeight = fontMap.getHeight() / 9;
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

	private static void loadFontPositions(){
		File fontPositionsFile = new File(determineDirectoryPath() + "res" + File.separator + "fonts" + File.separator + "fontPositions");
		fontPositions = new HashMap<Character, Point>();

		if(fontPositionsFile.isFile()){
			try {
				BufferedReader fontPositionsReader = new BufferedReader(new FileReader(fontPositionsFile));

				String fontPositionsString = "";
				while((fontPositionsString = fontPositionsReader.readLine()) != null){
					if(fontPositionsString.length() > 0){
						char font = fontPositionsString.charAt(0);
						fontPositionsString = fontPositionsString.substring(2);

						String[] coordinates = fontPositionsString.split(" ");
						if(coordinates.length > 1){
							int x = -1, y = -1;

							try {x = Integer.parseInt(coordinates[0]);
							} catch (NumberFormatException e) {
								System.out.println("Error: Failed to convert to int: " + coordinates[0]);
								e.printStackTrace();
							}

							try {y = Integer.parseInt(coordinates[1]);
							} catch (NumberFormatException e) {
								System.out.println("Error: Failed to convert to int: " + coordinates[1]);
								e.printStackTrace();
							}

							if(x>-1 && y>-1){
								Point p = new Point(x, y);
								fontPositions.put(font, p);
							}
						}
					}
				}
				fontPositionsReader.close();
			} catch (IOException e) {e.printStackTrace();}
		}
	}
}
