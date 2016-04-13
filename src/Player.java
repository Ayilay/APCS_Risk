import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/*
 * This program defines a player of the game. Not yet finished.
 * Author: Ethan Yao
 */
public class Player
{
	private int numReinforcements;//the total number of reinforcements the player can get
	private int numReinforcementsAvailable;//how many more armies the player can deploy on this phase.
	
	private Map<String,Territory> occupied;//which locations the player has
	private ArrayList<String> setOwned;//contains the sets that the player has for bonuses
	
	private String name;
	
	/*
	 * Default constructor. Takes the player's name and starting territory. Starts off with 3 reinforcements per turn
	 * @param name: The name of the player
	 * @param starter: The territory the player initially control.
	 */
	public Player(String name, Territory starter)
	{
		numReinforcements = 3;
		occupied = new HashMap<String, Territory>();
		occupied.put(starter.getID(), starter);
		setOwned = new ArrayList<String>();
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
			other.getOccupier().occupied.remove(other.getID());//Removes this territory from the other player's map
			other.setOccupier(this);
			occupied.put(other.getID(), other);
		}
	}
	
	public void getReinforcements()
	{
		numReinforcementsAvailable = numReinforcements;
	}
	
	/*
	 * Deploys reinforcements onto the selected territory.
	 */
	public void deployReinforcements(Territory t)
	{
		if(!occupied.containsValue(t))
		{
			return;
		}
		else
		{
			t.incrementArmies();
		}
	}
}
