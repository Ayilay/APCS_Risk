import java.util.HashSet;
import java.util.Set;

/*
 * This class creates and keeps track of each territory on the map.
 * Author: Ethan Yao
 */

public class Territory
{
	private int numOccupyingArmies;
	private String ID; // the name of this territory
	private Player occupier;
	private Set<String> neighbors; // Holds keys to Territories in TerritoryMap

	public Territory(String name)
	{
		occupier = null; // null represents a neutral territory and starts with
							// 2 armies on it
		numOccupyingArmies = 2;
		ID = name;
		neighbors = new HashSet<String>();
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

	public void addNeighbor(String other)
	{
		neighbors.add(other);
	}

	public boolean isNeightbor(Territory other)
	{
		return neighbors.contains(other);
	}

	/*
	 * Transfers armies from one territory to the other. Throws and exception if
	 * the target does not belong to the current player
	 */
	public void moveArmies(String otherID, int num)
	{
<<<<<<< HEAD
	    // TODO: replace exceptions with other error handling?
	    if(!isAdjacentTo(otherID))
	    {
	        throw new IllegalArgumentException("Territories are not adjacent!");
	    }
	    
	    Territory other = TerritoryMap.get(otherID);
=======
		// TODO: replace exceptions with other error handling?
>>>>>>> ethan/master
		if (num > this.numOccupyingArmies)
		{
			return;
		}
		if (!other.getOccupier().equals(this.getOccupier()))
		{
			return;
		}

		this.numOccupyingArmies -= num;
		other.numOccupyingArmies += num;
	}
	
	private boolean isAdjacentTo(String otherID)
	{
	    return (this.getAdjacentTerritories().contains(otherID));
	}
	
	public Set<String> getAdjacentTerritories()
	{
	    return this.neighbors;
	}
}
