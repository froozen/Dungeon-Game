package dungeongame;

public class BattleStats {
	public int hp, maxHp;
	public int mp, maxMp;
	public int atk, def;
	
	public BattleStats(){
		hp = 1;
		maxHp = 1;
		mp = 1;
		maxMp = 1;
		atk = 1;
		def = 1;
	}
	
	public BattleStats(int maxHp, int maxMp, int atk, int def){
		this.maxHp = maxHp;
		this.hp = maxHp;
		this.maxMp = maxMp;
		this.mp = maxMp;
		this.atk = atk;
		this.def = def;
	}
	
	public void dealDamage(int atk){
		atk -= def;
		if(atk > 0)hp -= atk;
	}
}
