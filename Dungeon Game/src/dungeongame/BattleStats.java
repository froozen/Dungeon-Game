package dungeongame;

public class BattleStats {
	public int hp, maxHp;
	public int atk, def;
	
	public BattleStats(){
		hp = 1;
		maxHp = 1;
		atk = 1;
		def = 1;
	}
	
	public BattleStats(int maxHp, int atk, int def){
		this.maxHp = maxHp;
		this.hp = maxHp;
		this.atk = atk;
		this.def = def;
	}
	
	public void dealDamage(int atk){
		atk -= def;
		if(atk > 0)hp -= atk;
		System.out.println("Damage!");
	}
}
