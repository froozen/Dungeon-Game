package dungeongame.basetypes.items;

import dungeongame.GameVariables;
import dungeongame.Inventory;
import dungeongame.RessourceManager;

public class HealItem extends BaseItem{
	public int hpAmount, mpAmount;

	public HealItem(int hpAmount, int mpAmount){
		this.hpAmount = hpAmount;
		this.mpAmount = mpAmount;
		
		this.icon = RessourceManager.getImage("icons.potion");
		this.name = "Potion";
	}
	
	public void use() {
		boolean used = false;
		if(GameVariables.playerBattleStats.hp < GameVariables.playerBattleStats.maxHp && hpAmount != 0){
			GameVariables.playerBattleStats.hp += hpAmount;
			if(GameVariables.playerBattleStats.hp > GameVariables.playerBattleStats.maxHp)GameVariables.playerBattleStats.hp = GameVariables.playerBattleStats.maxHp;
			used = true;
		}
		
		if(GameVariables.playerBattleStats.mp < GameVariables.playerBattleStats.maxMp && mpAmount != 0){
			GameVariables.playerBattleStats.mp += mpAmount;
			if(GameVariables.playerBattleStats.mp > GameVariables.playerBattleStats.maxMp)GameVariables.playerBattleStats.mp = GameVariables.playerBattleStats.maxMp;
			used = true;
		}
		
		if(used)Inventory.removeItem(this);
	}

	public String getDescription() {
		String description = "";
		
		if(hpAmount > 0)description += "HP +" + hpAmount + " ";
		if(mpAmount > 0)description += "MP +" + mpAmount + " ";
		
		return description;
	}
}
