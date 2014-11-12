package dungeongame.stats;

public class DamageCalculator {
	public static int calculateNormalAttack ( Stats dealer, Stats receiver ) {
		int damage = (int) ( dealer.getAtk() - ( receiver.getDef() * 0.4 )  );
		if ( damage > 0 )
		{
			receiver.subtractHp ( damage );
			return damage;
		}
		return 0;
	}
}
