package dungeongame;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class RessourceManager {
	private static String directoryPath;
	private static HashMap <String, BufferedImage> images;
	private static HashMap <String, String> imageLocations;

	static{
		images = new HashMap<String, BufferedImage>();
		imageLocations = new HashMap<String, String>();

		imageLocations.put("misc.title", "misc" + File.separator + "title.png");

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
