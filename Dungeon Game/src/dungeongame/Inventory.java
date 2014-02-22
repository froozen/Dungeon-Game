package dungeongame;

import java.util.ArrayList;

import dungeongame.basetypes.items.BaseItem;
import dungeongame.basetypes.items.HealItem;

public class Inventory {
	private static ArrayList<BaseItem> items;

	static{
		items = new ArrayList<BaseItem>();
		
		//TODO remove later on
		for(int i = 0; i < 30; i++){
			BaseItem addItem = new  HealItem(15, 3);
			items.add(addItem);
		}
	}

	public static void addItem(BaseItem item){
		items.add(item);
	}
	
	public static ArrayList<BaseItem> getInventoryArrayList(){
		return items;
	}
	
	public static BaseItem getItem(int index){
		if(index < items.size())return items.get(index);
		else return null;
	}
}
