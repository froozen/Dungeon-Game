package dungeongame.basetypes.items;

import dungeongame.GameVariables;
import dungeongame.RessourceManager;

public class HealItem extends BaseItem{
	public int hpAmount, mpAmount;

	public HealItem(int hpAmount, int mpAmount){
		this.hpAmount = hpAmount;
		this.mpAmount = mpAmount;
		
		this.icon = RessourceManager.getImage("icons.potion");
		this.name = "Potion";
		this.consumable = true;
	}
	
	protected void uponUse() {
		GameVariables.playerBattleStats.hp += hpAmount;
		if(GameVariables.playerBattleStats.hp > GameVariables.playerBattleStats.maxHp)GameVariables.playerBattleStats.hp = GameVariables.playerBattleStats.maxHp;
		
		GameVariables.playerBattleStats.mp += mpAmount;
		if(GameVariables.playerBattleStats.mp > GameVariables.playerBattleStats.maxMp)GameVariables.playerBattleStats.mp = GameVariables.playerBattleStats.maxMp;
	}

	public String getDescription() {
		String description = "";
		
		if(hpAmount > 0)description += "HP +" + hpAmount + " ";
		if(mpAmount > 0)description += "MP +" + mpAmount + " ";
		
		return description;
	}
}
