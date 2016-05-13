package territoryMap;
import java.util.HashSet;
import java.util.Set;

public class Continent
{
	private String ID;
	private int armyBonus;
	private Set<String> territories;

	public Continent(String id, int bonus)
	{
		ID = id;
		armyBonus = bonus;
		territories = new HashSet<String>();
	}

	public void addTerritory(String territory)
	{
		territories.add(territory);
	}

	public String getID()
	{
		return ID;
	}

	public Set<String> getTerritories()
	{
		return territories;
	}

	public int getArmyBonus()
	{
		return armyBonus;
	}
}
