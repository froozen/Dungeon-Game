package dungeongame.basetypes.items;

import dungeongame.GameVariables;
import dungeongame.RessourceManager;

public class EquipmentItem extends BaseItem{
	public int amount;
	public EquipmentType equipmentType;
	
	public EquipmentItem(EquipmentType equipmentType, int amount){
		this.amount = amount;
		this.equipmentType = equipmentType;
		
		if(equipmentType == EquipmentType.WEAPON){
			this.icon = RessourceManager.getImage("icons.sword");
			this.name = "Weapon";
		}
		else if(equipmentType == EquipmentType.HELMET){
			this.icon = RessourceManager.getImage("icons.helmet");
			this.name = "Helmet";
		}
		else if(equipmentType == EquipmentType.ARMOR){
			this.icon = RessourceManager.getImage("icons.armor");
			this.name = "Armor";
		}
		else if(equipmentType == EquipmentType.PANTS){
			this.icon = RessourceManager.getImage("icons.pants");
			this.name = "Pants";
		}
	}
	
	public enum EquipmentType{
		WEAPON, HELMET, ARMOR, PANTS
	}

	public void use() {
		GameVariables.playerBattleStats.equipEquipmentItem(this);
	}

	public String getDescription() {
		return "";
	}
}
