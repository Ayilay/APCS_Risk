import java.util.HashSet;
import java.util.Set;

/*
 * This program defines a player of the game. Not yet finished.
 * Author: Ethan Yao
 */
public class Player
{
	private int numReinforcements;//the total number of reinforcements the player can get
	private int numReinforcementsAvailable;//how many more armies the player can deploy on this phase.
	
	private Set<String> occupiedTerritories;//which locations the player has
	//Removed setOwned because we should check every turn for which sets and it really doesn't serve any purpose.
	
	private String name;
	
	/*
	 * Default constructor. Takes the player's name and starting territory. Starts off with 3 reinforcements per turn
	 * @param name: The name of the player
	 * @param starter: The territory the player initially control.
	 */
	public Player(String name, Territory starter)
	{
		numReinforcements = 3;
		occupiedTerritories = new HashSet<String>();
		occupiedTerritories.add(starter.getID());
		this.name = name;
	}
	/*
	 * Mounts an attack from a territory to another.
	 * @param attacker: The territory that the attack is being mounted from.
	 * @param other: The territory being invaded.
	 * @param armies: The number of armies being used for the attack. If this number is zero, an all out attack is mounted
	 */
	public void attackOther(Territory attacker, Territory other, int armies)
	{
		Battle battle;
		if(armies == 0)
		{
			battle = new Battle(attacker,other);
		}
		else
		{
			battle = new Battle(attacker,other,armies);
		}
		battle.doBattle();
		if(battle.getResult())
		{
			other.getOccupier().occupiedTerritories.remove(other.getID());//Removes this territory from the other player's map
			other.setOccupier(this);
			occupiedTerritories.add(other.getID());
		}
	}
	/*
	 * Gets the number of reinforcements available on a new turn according to the following rules:
	 * Divide the number of territories by 3 and round down. Must be less than seven.
	 * Also calculates the bonuses based on territories owned.
	 * 
	 */
	public void getReinforcements()
	{
		if(occupiedTerritories.size()<9)//Less than 9 armies means only 3 armies a turn
		{
			numReinforcements = 3;
		}
		else
		{
			numReinforcements = occupiedTerritories.size()/3;
		}
		if(TerritoryMap.hasSet(TerritoryMap.africa, occupiedTerritories))
		{
			numReinforcements +=4;//4 reinforcements for owning all of Africa
		}
		if(TerritoryMap.hasSet(TerritoryMap.asia, occupiedTerritories))
		{
			numReinforcements +=8;
		}
		if(TerritoryMap.hasSet(TerritoryMap.australia, occupiedTerritories))
		{
			numReinforcements +=3;
		}
		if(TerritoryMap.hasSet(TerritoryMap.europe, occupiedTerritories))
		{
			numReinforcements +=6;
		}
		if(TerritoryMap.hasSet(TerritoryMap.northAmerica, occupiedTerritories))
		{
			numReinforcements +=5;
		}
		if(TerritoryMap.hasSet(TerritoryMap.southAmerica, occupiedTerritories))
		{
			numReinforcements +=3;
		}
		//Calculates based on the number of sets owned.
		
		numReinforcementsAvailable = numReinforcements;
	}
	
	/*
	 * Deploys reinforcements onto the selected territory.
	 */
	public void deployReinforcements(Territory t)
	{
		if(!occupiedTerritories.contains(t.getID()))
		{
			return;
		}
		else
		{
			t.incrementArmies();
			numReinforcements--;
		}
	}
}
