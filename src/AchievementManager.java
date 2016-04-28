/*
 * Creates all the achievements and stores them.
 * WORK IN PROGRESS
 */
public class AchievementManager
{

}

class Trump extends Achievement
{
	public Trump()
	{
		super("I will build a great, great wall", // name
				"Have more than one hundred armies deployed "// description
						+ "along the southern border of the United States",
				50, true);// bonus, one time
	}

	@Override
	public boolean isFullfilled(Player p)
	{
		if(p.getOccupiedTerritories().contains("Eastern United States")
				&& p.getOccupiedTerritories().contains("Western United States"))
		{
			if(TerritoryMap.get("Eastern United States").getNumArmies()
					+ TerritoryMap.get("Western United States").getNumArmies() > 100)
			{
				return true;
			}
			else;
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}
}
class IslandHopping extends Achievement
{
	public IslandHopping()
	{
		super("Island Hopping", 
				"Conquer every island on the map",
				20,true);
		//a one time bonus of 20 armies
	}
	public boolean isFullfilled(Player p)
	{
		return p.ownsTerritory("Philippines")&&p.ownsTerritory("Indonesia")
				&&p.ownsTerritory("Hawaii")&&p.ownsTerritory("New Guinea")
				&&p.ownsTerritory("Eastern Australia")&&p.ownsTerritory("Western Australia")
				&& p.ownsTerritory("Iceland");
		//owns every island
	}
}

class NuclearGandhi extends Achievement
{
	public NuclearGandhi()
	{
		super("Nuclear Gandhi"
				,"Our words are backed by NUCLEAR WEAPONS!",
				40,true);
	}

	@Override
	public boolean isFullfilled(Player p)//Own 3 continents
	{
		int continents = 0;
		for(String continent : TerritoryMap.getAllContinents())
		{
			if(TerritoryMap.ownsContinent(continent, p.getOccupiedTerritories()))
			{
				continents++;
			}
		}
		return continents>=3;
	}
	
}

class RussianWinter extends Achievement
{
	public RussianWinter()
	{
		super("If you're going to attack Russia in winter...",
				"...pack some winter clothes",
				20,true);
	}

	@Override
	public boolean isFullfilled(Player p)
	{
		
		return false;
	}
	
}