package dungeongame.stats;

public abstract class Stats {
	public int hp, maxHp;
	public int mp, maxMp;
	
	public abstract int getAtk ();
	public abstract int getMAtk ();
	public abstract int getDef ();
	public abstract int getMDef ();
	
	public void subtractHp ( int amount ) {
		hp = hp - amount > 0 ? hp - amount : 0;
		if ( hp > maxHp ) hp = maxHp;
	}
}