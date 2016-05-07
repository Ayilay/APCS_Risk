import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;

/*
 * This program defines a player of the game.
 * Author: Ethan Yao
 */
public class Player
{
	private int numReinforcements;//the total number of reinforcements the player can get
	private int numReinforcementsAvailable;//how many more armies the player can deploy on this phase.
	private ArrayList<Card> deck;
	private Set<String> occupiedTerritories;//which locations the player has
	private ArrayList<Achievement> notFulfilled;//achievements that the player has not yet obtained

	private String name;

	/**
	 * Default constructor. Takes the player's name and starting territory. Starts off with 3 reinforcements per turn
	 * @param name: The name of the player
	 * @param starter: The territory the player initially control.
	 */
	public Player(String name, String starter)
	{
		numReinforcements = 3;
		occupiedTerritories = new HashSet<String>();
		occupiedTerritories.add(starter);
		TerritoryMap.get(starter).setOccupier(this);
		this.name = name;

		deck = new ArrayList<Card>();
		notFulfilled = new ArrayList<Achievement>(AchievementManager.achievements);
	}
	public int checkAchievements()
	{
		int bonus = 0;
		for(int i = 0; i < notFulfilled.size(); i++)
		{
			if(notFulfilled.get(i).isFullfilled(this))
			{
				bonus += notFulfilled.get(i).getBonus();
				System.out.println(notFulfilled.get(i).achieved());
				notFulfilled.remove(i);
				i--;
			}

		}
		return bonus;
	}

	/**
	 * Mounts an attack from a territory to another.
	 * @param attacker: The territory that the attack is being mounted from.
	 * @param other: The territory being invaded.
	 * @param armies: The number of armies being used for the attack. If this number is zero, an all out attack is mounted
	 * @return Returns result of battle.
	 */
	public boolean attackOther(Territory attacker, Territory other, int armies)
	{
		BattleHandler battle = new BattleHandler(attacker, other, armies);
		battle.doBattle();
		if(battle.getResult())
		{
			if(other.getOccupier() != null)
			{
				other.getOccupier().occupiedTerritories.remove(other.getID());//Removes this territory from the other player's map
			}
			other.setOccupier(this);
			occupiedTerritories.add(other.getID());
		}
		return battle.getResult();
	}

	/**
	 * Gets the number of reinforcements available on a new turn according to the following rules:
	 * Divide the number of territories by 3 and round down. Must be less than seven.
	 * Also calculates the bonuses based on territories owned.
	 *
	 */
	public void calculateReinforcements()
	{
		if(occupiedTerritories.size() < 9) //Less than 9 armies means only 3 armies a turn
		{
			numReinforcements = 3;
		}
		else
		{
			numReinforcements = occupiedTerritories.size() / 3;
		}

		//Calculates based on the number of sets owned.
		numReinforcements += TerritoryMap.calculateArmyBonusFromContinents(occupiedTerritories);
		numReinforcements += checkAchievements();
		//Super Hax Mode: For use with Achievement testing only. Comment out if playing actual game :)
		//numReinforcements += 100;

		numReinforcementsAvailable = numReinforcements;
	}

	/*
	 * Deploys reinforcements onto the selected territory.
	 */
	public boolean deployReinforcements(String t, int amount)
	{
		if(!occupiedTerritories.contains(t))
		{
			return false;
		}
		if(amount > numReinforcementsAvailable)
		{
			return false;
		}

		TerritoryMap.get(t).incrementArmiesBy(amount);
		numReinforcementsAvailable -= amount;
		return true;
	}

	// Returns all territories that can be attacked by Player p
	public Set<String> getAttackTargets()
	{
		Set<String> returnSet = new HashSet<String>();
		for(String s : occupiedTerritories)
		{
			Territory t = TerritoryMap.get(s);
			Set<String> neighbors = t.getAdjacentTerritories();
			for(String k : neighbors)
			{
				if(!occupiedTerritories.contains(k))
				{
					returnSet.add(k);
				}
			}
		}
		return returnSet;
	}

	public String getName()
	{
		return name;
	}

	public int getTotalNumReinforcements()
	{
		return numReinforcements;
	}

	public int getNumReinforcementsAvailable()
	{
		return numReinforcementsAvailable;
	}

	public boolean ownsTerritory(String t)
	{
		return occupiedTerritories.contains(t);
	}

	public boolean ownsContinent(String c)
	{
		return TerritoryMap.continentIsSubsetOfSet(c, occupiedTerritories);
	}

	public boolean canAttack(String t)
	{
		return getAttackTargets().contains(t);
	}

	public Set<String> getOccupiedTerritories()
	{
		return occupiedTerritories;
	}

	public ArrayList<Card> getCards()
	{
		return deck;
	}

	public void addCards(Card c)
	{
		deck.add(c);
	}
	public boolean hasNoTerritories()
	{
		return occupiedTerritories.isEmpty();
	}
}
