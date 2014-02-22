package dungeongame.basetypes.items;

import java.awt.image.BufferedImage;

public abstract class BaseItem {
	public String name;
	public BufferedImage icon;
	
	public abstract void use();
	public abstract String getDescription();
}
