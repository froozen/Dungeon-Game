package dungeongame.basetypes.items;

import java.awt.image.BufferedImage;

public abstract class BaseItem {
	public String name, description;
	public boolean consumable;
	public BufferedImage sprite;
	
	public abstract void uponUse();
}
