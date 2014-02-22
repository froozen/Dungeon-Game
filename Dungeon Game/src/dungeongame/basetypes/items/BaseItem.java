package dungeongame.basetypes.items;

import java.awt.image.BufferedImage;

import dungeongame.Inventory;

public abstract class BaseItem {
	public String name;
	public boolean consumable;
	public BufferedImage icon;
	
	protected abstract void uponUse();
	public abstract String getDescription();
	
	public void use(){
		uponUse();
		if(consumable){
			Inventory.removeItem(this);
		}
	}
}
