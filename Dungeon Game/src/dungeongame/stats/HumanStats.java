package dungeongame.stats;

import dungeongame.Inventory;
import dungeongame.basetypes.items.EquipmentItem;
import dungeongame.basetypes.items.EquipmentItem.EquipmentType;

public class HumanStats extends Stats {
	private EquipmentItem weapon, helmet, armor, pants;

	private int baseAtk = 5;
	private int baseDef = 5;

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

	public int getMAtk() {
		return 0;
	}

	public int getMDef() {
		return 0;
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
