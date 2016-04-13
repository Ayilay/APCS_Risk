import java.util.ArrayList;

/*
 * This class creates and keeps track of each territory on the map.
 * Author: Ethan Yao
 */
public class Territory
{
	private int numOccupyingArmies;
	private String ID;// the name of this territory
	private Player occupier;
	private ArrayList<Territory> neighbors;

	public Territory(String name)
	{
		occupier = null;// null represents a neutral territory and starts with 2 armies on it
		numOccupyingArmies = 2;
		ID = name;
		neighbors = new ArrayList<Territory>();
	}

	// following two methods are for use in battles
	public void incrementArmies()
	{
		numOccupyingArmies++;
	}

	public void decrementArmies()
	{
		numOccupyingArmies--;
	}

	public int getNumArmies()
	{
		return numOccupyingArmies;
	}

	public String getID()
	{
		return ID;
	}

	public Player getOccupier()
	{
		return occupier;
	}

	public void setOccupier(Player conqueror)
	{
		occupier = conqueror;
	}
	
	public void addNeighbor(Territory other)
	{
		neighbors.add(other);
	}
	
	public boolean isNeightbor(Territory other)
	{
		return neighbors.contains(other);
	}
	/*
	 * Transfers armies from one territory to the other.
	 * Throws and exception if the target does not belong to the current player
	 */
	public void moveArmies(Territory other, int num)
	{
		if (num > this.numOccupyingArmies)
		{
			throw new IllegalArgumentException("You do not have this many armies!");
		}
		if (!other.getOccupier().equals(this.getOccupier()))
		{
			throw new IllegalArgumentException("You do not own this territory. Conquer it first");
		}
		this.numOccupyingArmies -= num;
		other.numOccupyingArmies += num;
	}
}
