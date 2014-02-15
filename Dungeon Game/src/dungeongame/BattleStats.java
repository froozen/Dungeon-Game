package dungeongame;

public class BattleStats {
	public int hp, maxHp;
	public int mp, maxMp;
	public int atk, def;
	public int str, dex, mag, luk;
	public int level, expToNextLevel, lastExpToNextLevel;

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
}
