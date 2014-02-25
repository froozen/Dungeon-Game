package dungeongame;

import dungeongame.basetypes.items.EquipmentItem;
import dungeongame.basetypes.items.EquipmentItem.EquipmentType;

public class BattleStats {
	public int hp, maxHp;
	public int mp, maxMp;
	public int atk, def;
	public int str, dex, mag, luk;
	public int level, expToNextLevel, lastExpToNextLevel;
	
	private EquipmentItem weapon, helmet, armor, pants;

	public BattleStats(){
		hp = 1;
		maxHp = 1;
		mp = 1;
		maxMp = 1;
		atk = 1;
		def = 1;
		level = 1;
		expToNextLevel = 1;
		str = 1;
		dex = 1;
		mag = 1;
		luk = 1;
	}

	public void dealDamage(int atk){
		atk -= def;
		if(atk > 0)hp -= atk;
	}

	public void gainExp(int amount){
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
	
	private void determineNextExpToNextLevel(){
		expToNextLevel = (int)(lastExpToNextLevel * 1.25);
	}
	
	private void levelUp(){
		level++;
		System.out.println("Level Up! Level raised to " + level);
	}
	
	public void equipEquipmentItem(EquipmentItem item){
		if(item.equipmentType == EquipmentType.WEAPON){
			if(weapon != null){
				Inventory.addItem(weapon);
				atk -= weapon.amount;
			}
			weapon = item;
			atk += weapon.amount;
		}
		else if(item.equipmentType == EquipmentType.HELMET){
			if(helmet != null){
				Inventory.addItem(helmet);
				def -= helmet.amount;
			}
			helmet = item;
			def += helmet.amount;
		}
		else if(item.equipmentType == EquipmentType.ARMOR){
			if(armor != null){
				Inventory.addItem(armor);
				def -= armor.amount;
			}
			armor = item;
			def += armor.amount;
		}
		else if(item.equipmentType == EquipmentType.PANTS){
			if(pants != null){
				Inventory.addItem(pants);
				def -= pants.amount;
			}
			pants = item;
			def += pants.amount;
		}
		
		Inventory.removeItem(item);
	}
	
	public EquipmentItem getWeapon(){
		return weapon;
	}
	
	public EquipmentItem getArmorn(){
		return armor;
	}
	
	public EquipmentItem getHelmet(){
		return helmet;
	}
	
	public EquipmentItem getPants(){
		return pants;
	}
}
