package dungeongame.stats;

import java.util.Random;

import dungeongame.Inventory;
import dungeongame.basetypes.items.EquipmentItem;
import dungeongame.basetypes.items.EquipmentItem.EquipmentType;

public class HumanStats extends Stats {
	private EquipmentItem weapon, helmet, armor, pants;

	private static int baseAtk = 5;
	private static int baseDef = 5;
	private static int baseMAtk = 5;
	private static int baseMDef = 5;
	private static int level = 1;
	private static int expToNextLevel = 10, lastExpToNextLevel = 10;

	public int getAtk () {
		int atk = baseAtk;
		if ( weapon != null ) atk += weapon.amount;
		return atk;
	}

	public int getDef() {
		int def = baseDef;
		
		if ( helmet != null ) def += helmet.amount;
		if ( armor != null ) def += armor.amount;
		if ( pants != null ) def += pants.amount;
		
		return def;
	}
		
	public int getLevel()
	{
		return level;
	}

	public int getMAtk() {
		int mAtk = baseMAtk;
		return mAtk;
	}

	public int getMDef() {
		int mDef = baseMDef;
		return mDef;
	}
	
	public static void levelUp()
	{
		level++;
		Random r = new Random();
		baseDef += r.nextInt(4) + 1;
		baseAtk += r.nextInt(4) + 1;
		baseMDef += r.nextInt(4) + 1;
		baseMAtk += r.nextInt(4) + 1;
		System.out.println("Lvl up, new level: " + level);
	}
	
	public static void gainExp(int amount){
		while(amount > 0){
			if(amount >= expToNextLevel){
				amount -= expToNextLevel;
				determineNextExpToNextLevel();
				levelUp();
			}
			else{
				expToNextLevel -= amount;
				amount = 0;
			}
		}
	}
	
	private static void determineNextExpToNextLevel(){
		expToNextLevel = (int) (lastExpToNextLevel * 1.25);
		lastExpToNextLevel = expToNextLevel;
		System.out.println("Exp to Next Level: " + expToNextLevel);
	}
	
	public void equipEquipmentItem(EquipmentItem item){
		if(item.equipmentType == EquipmentType.WEAPON){
			if(weapon != null)
				Inventory.addItem(weapon);
			weapon = item;
		}
		else if(item.equipmentType == EquipmentType.HELMET){
			if(helmet != null)
				Inventory.addItem(helmet);
			helmet = item;
		}
		else if(item.equipmentType == EquipmentType.ARMOR){
			if(armor != null)
				Inventory.addItem(armor);
			armor = item;
		}
		else if(item.equipmentType == EquipmentType.PANTS){
			if(pants != null)
				Inventory.addItem(pants);
			pants = item;
		}
		
		Inventory.removeItem(item);
	}
	
	public EquipmentItem getWeapon () {
		return weapon;
	}

	public EquipmentItem getHelmet () {
		return helmet;
	}

	public EquipmentItem getArmor () {
		return armor;
	}

	public EquipmentItem getPants () {
		return pants;
	}

}
