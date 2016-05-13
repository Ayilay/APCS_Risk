package territoryMap;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import main.Player;

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

	public boolean isOccupiedByPlayer()
	{
		return occupier != null;
	}

	/*
	 * Transfers armies from one territory to the other. Throws and exception if
	 * the target does not belong to the current player
	 */
	public void moveArmies(String otherID, int num)
	{
		if(!isNeighborWith(otherID))
		{
			return;
		}

		Territory other = TerritoryMap.get(otherID);
		if(num > this.numOccupyingArmies)
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

	public Set<String> getAdjacentTerritoriesOccupiedBy(Player p)
	{
		// No sense in checking adjacent territories that don't belong to anyone
		if(p == null) return null;

		Set<String> temp = new HashSet<String>();

		for(String s : this.getAdjacentTerritories())
		{
			if(TerritoryMap.getOccupierOnTerritory(s) != null) // Both territories are occupied by some player
				if(TerritoryMap.getOccupierOnTerritory(s).getName().equals(p.getName()))
					temp.add(s);
		}

		return temp;
	}

	public Set<String> getValidAttackerTerritoriesOccupiedBy(Player p)
	{
		Set<String> territories = getAdjacentTerritoriesOccupiedBy(p);
		Iterator<String> iter = territories.iterator();
		while(iter.hasNext())
		{
			String terrID = iter.next();
			if(TerritoryMap.getNumArmiesDeployedOn(terrID) < 2)
				iter.remove();
		}

		return territories;
	}

	public void disownOccupier()
	{
		occupier = null;
	}

	public Set<String> getValidFortifiers()
	{
		Set<String> targets = new HashSet<String>(getAdjacentTerritoriesOccupiedBy(occupier));

		Iterator<String> iter = targets.iterator();
		while(iter.hasNext())
		{
			String terrID = iter.next();

			if(TerritoryMap.getNumArmiesDeployedOn(terrID) < 2)
				iter.remove();
		}

		return targets;
	}

	public boolean isValidFortifier(String terrID)
	{
		return getValidFortifiers().contains(terrID);
	}
}
