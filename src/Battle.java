/*
 * This program simulates the battles of risk. 
 */
public class Battle
{
	private int numAttackingArmies;// the number of attacking armies
	private Territory attacker;//territory the attack originated from
	private int numDefendingArmies;// the number of defending armies
	private Territory defender;//defender's territory
	

	// the outcome of this battle: true means attacker won, false means
	// defender won
	private boolean outcome;
	/*
	 * Constructor used for an all out attack. No going back, kiddo
	 * @param t1 The territory from which the attacker is attacking from.
	 * @param t2 The territory from which the defender is defending from. (Should reword that)
	 */
	public Battle(Territory t1, Territory t2)//t1 is the attacking territory, t2 is the defending one.
	{
		numAttackingArmies = t1.getNumArmies()-1;
		numDefendingArmies = t2.getNumArmies();
		attacker = t1;
		defender = t2;
		
	}
	/*
	 * Constructor used in case the player wants to attack with only part of his armies. 
	 * @param t1: The territory that the attacker is attacking from
	 * @param t2: The territory that the defender is defending from
	 * @param attacking: The number of armies the attacker is using to attack
	 */
	public Battle(Territory t1, Territory t2, int attacking)
	{
		attacker = t1;
		defender = t2;
		numAttackingArmies = attacking;
		numDefendingArmies = t2.getNumArmies();
	}

	/*
	 * Engages armies once according to rules.
	 */
	public void doBattleOnce()
	{
		
		int numAttackDice;// number of dice rolled by attacker
		int numDefenseDice = 1;// number of dice rolled by defender
		switch (numAttackingArmies)// sets the number of dice used by the
									// attacker
		{
            case 2://if two armies on attacking territory
                numAttackDice = 1;
                break;
            case 3://if three armies on attacking territory
                numAttackDice = 2;
                break;
            default:
                numAttackDice = 3;
                break;
		}
		if (numDefendingArmies >= 2)
		{
			numDefenseDice = 2;
		}

		Die_Roll attackRoll = new Die_Roll(numAttackDice);
		Die_Roll defenseRoll = new Die_Roll(numDefenseDice);

		System.out.println("Num of attacking armies: " + numAttackingArmies);
		System.out.println("Num of defending armies: " + numDefendingArmies);
		for (int i = 0; i < attackRoll.getRolls().length && i < defenseRoll.getRolls().length; i++)
		{
			System.out.println("highest roll by attacker " + attackRoll.getRolls()[i]);
			System.out.println("highest roll by defender " + defenseRoll.getRolls()[i]);
			if (attackRoll.getRolls()[i] > defenseRoll.getRolls()[i])
			{
				numDefendingArmies--;
				defender.decrementArmiesBy(1);
				System.out.println("Defending army lost");
				
			} 
			else
			{
				attacker.decrementArmiesBy(1);
				numAttackingArmies--;
				System.out.println("Attacking army lost");
			}
			if(numAttackingArmies == 0 || numDefendingArmies == 0)
			{
				break;
			}
		}
		
		
	}

	/*
	 * Simulates a risk battle. Goes to completion - either the attacker or the
	 * defender is wiped out.
  	 * 
	 * @return result of the battle - true if attacker won, false if defender
	 * won.
	 */
	public void doBattle()
	{
		System.out.println("Attack mounted from territory " + attacker.getID() + " to " + defender.getID());
		while (numAttackingArmies != 0 && numDefendingArmies != 0)	//one army must stay behind on the territory
																	//that the attack originated from
		{
			doBattleOnce();
		}
		if (numAttackingArmies == 0)
		{
			System.out.println("Defender won");
			outcome = false;
		} else
		{
			System.out.println("Attacker won");
			outcome = true;
		}

		resolve();
	}
	public boolean getResult()
	{
		return outcome;
	}
	/*
	 * Resolves the battle. If the attacker won, all attacking armies minus one are moved to the conquered territory.
	 * Otherwise nothing happens.
	 */
	public void resolve()
	{
		if(getResult())
		{
			attacker.moveArmies(defender.getID(), numAttackingArmies);
		}
	}
}
