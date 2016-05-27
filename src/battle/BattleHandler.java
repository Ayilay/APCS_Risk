package battle;
import territoryMap.Territory;
import territoryMap.TerritoryMap;

/**
 * This class is responsible for battle mechanics
 * Simulates a battle between 2 territories
 */
public class BattleHandler
{
	/*
	 * Simulates a single battle and returns the results. Does not affect the territory states
	 */
	public static BattleResults doBattleOnce(String attackerID, String defenderID, int numAttackingArmies)
	{

		if(numAttackingArmies >= TerritoryMap.getNumArmiesDeployedOn(attackerID))
			return new BattleResults(false, 0, 0);

		Territory attacker = TerritoryMap.get(attackerID);
		Territory defender = TerritoryMap.get(defenderID);
		int numDefendingArmies = defender.getNumArmies();

		int attackerLosses = 0;
		int defenderLosses = 0;


		int numAttackDice;// number of dice rolled by attacker
		int numDefenseDice = 1;// number of dice rolled by defender
		switch(numAttackingArmies) // sets the number of dice used by the
			// attacker
		{
			case 1://if one army used to attack
				numAttackDice = 1;
				break;
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
		if(numDefendingArmies >= 2)
		{
			numDefenseDice = 2;
		}

		Die_Roll attackRoll = new Die_Roll(numAttackDice);
		Die_Roll defenseRoll = new Die_Roll(numDefenseDice);

		for(int i = 0; i < attackRoll.getRolls().length && i < defenseRoll.getRolls().length; i++)
		{
			if(attackRoll.getRolls()[i] > defenseRoll.getRolls()[i])
			{
				numDefendingArmies --;
				defenderLosses ++;
			}
			else
			{
				numAttackingArmies --;
				attackerLosses ++;
			}

			if(numAttackingArmies == 0 || numDefendingArmies == 0)
			{
				break;
			}
		}

		if(numAttackingArmies == 0)
		{
			return new BattleResults(false, attackerLosses, defenderLosses); // Attacker lost the battle
		}
		else if(numDefendingArmies == 0)
		{
			return new BattleResults(true, attackerLosses, defenderLosses); // Attacker won the battle
		}
		else
		{
			return new BattleResults(false, attackerLosses, defenderLosses); // Tie
		}
	}

	/**
	 * Simulates a risk battle. Goes to completion - either the attacker or the
	 * defender is wiped out.
	 *
	 * @return result of the battle
	 */
	public static BattleResults doFullBattle(String attackerID, String defenderID, int numAttackingArmies)
	{
		Territory attacker = TerritoryMap.get(attackerID);
		Territory defender = TerritoryMap.get(defenderID);
		int numDefendingArmies = defender.getNumArmies();

		int totalAttackerLosses = 0;
		int totalDefenderLosses = 0;
		boolean attackerVictory = false;

		while(numAttackingArmies != 0 && numDefendingArmies != 0)	//one army must stay behind on the territory
			//that the attack originated from
		{
			BattleResults results = doBattleOnce(attackerID, defenderID, numAttackingArmies);

			int currentAttackLosses = results.getNumAttackerLosses();
			int currentDefendLosses = results.getNumDefenderLosses();

			numAttackingArmies -= currentAttackLosses;
			numDefendingArmies -= currentDefendLosses;

			totalAttackerLosses  += currentAttackLosses;
			totalDefenderLosses +=  currentDefendLosses;
			
			attacker.decrementArmiesBy(currentAttackLosses);
			defender.decrementArmiesBy(currentDefendLosses);
		}

		if(numAttackingArmies == 0) // Attacker lost all armies
		{
			attackerVictory = false;
		}
		else
		{
			attackerVictory = true;
			attacker.moveArmies(defenderID, numAttackingArmies);
		}

		return new BattleResults(attackerVictory, totalAttackerLosses, totalDefenderLosses);
	}
}
