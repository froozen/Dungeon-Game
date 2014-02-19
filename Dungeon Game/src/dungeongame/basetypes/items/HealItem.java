package dungeongame.basetypes.items;

import dungeongame.GameVariables;

public class HealItem extends BaseItem{
	public int hpAmount, mpAmount;

	
	
	public void uponUse() {
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
