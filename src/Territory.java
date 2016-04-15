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
	public void incrementArmiesBy(int amount)
	{
		numOccupyingArmies += amount;
	}

	public void decrementArmiesBy(int amount)
	{
		numOccupyingArmies -= amount;
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

	public boolean isNeighborWith(String other)
	{
		return neighbors.contains(other);
	}

	/*
	 * Transfers armies from one territory to the other. Throws and exception if
	 * the target does not belong to the current player
	 */
	public void moveArmies(String otherID, int num)
	{
	    // TODO: replace exceptions with other error handling?
	    if(!isNeighborWith(otherID))
	    {
	        throw new IllegalArgumentException("Territories are not adjacent!");
	    }
	    
	    Territory other = TerritoryMap.get(otherID);
		if (num > this.numOccupyingArmies)
		{
			return;
		}

		this.numOccupyingArmies -= num;
		other.numOccupyingArmies += num;
	}
	
	public Set<String> getAdjacentTerritories()
	{
	    return this.neighbors;
	}
	
	public Set<String> getAdjacentOccupiedTerritories()
	{
	    // No sense in checking adjacent territories that don't belong to anyone
	    if(this.occupier == null) return null;

		Set<String> temp = new HashSet<String>();
		
		for(String s : this.getAdjacentTerritories())
		{
		    if(TerritoryMap.get(s).getOccupier() != null) // Both territories are occupied by some player
                if(TerritoryMap.get(s).getOccupier().getName().equals(this.getOccupier().getName()))
                    temp.add(s);
		}
		
		return temp;
	}
}
