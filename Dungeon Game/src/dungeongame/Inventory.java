package dungeongame;

import java.util.ArrayList;

import dungeongame.basetypes.items.BaseItem;

public class Inventory {
	private static ArrayList<BaseItem> items;

	static{
		items = new ArrayList<BaseItem>();
	}

	public static void addItem(BaseItem item){
		items.add(item);
	}
	
	public static ArrayList<BaseItem> getInventoryArrayList(){
		return items;
	}
}
