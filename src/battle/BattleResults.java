package battle;
public class BattleResults
{
	private boolean attackerVictory;
	private int numAttackerLosses;
	private int numDefenderLosses;

	public BattleResults(boolean attackVictory, int attackLosses, int defendLosses)
	{
		attackerVictory = attackVictory;
		numAttackerLosses = attackLosses;
		numDefenderLosses = defendLosses;
	}

	public int getNumAttackerLosses()
	{
		return numAttackerLosses;
	}

	public int getNumDefenderLosses()
	{
		return numDefenderLosses;
	}

	public boolean getAttackSuccess()
	{
		return attackerVictory;
	}
}
