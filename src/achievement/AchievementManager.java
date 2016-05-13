package achievement;
import java.util.ArrayList;

import main.Player;
import territoryMap.TerritoryMap;

/*
 * Creates all the achievements and stores them.
 * WORK IN PROGRESS
 */
public class AchievementManager
{
	public static ArrayList<Achievement> achievements;
	public static void init()
	{
		Achievement trump = new Trump();
		Achievement islands = new IslandHopping();
		Achievement gandhi = new NuclearGandhi();
		achievements = new ArrayList<Achievement>();

		achievements.add(trump);
		achievements.add(islands);
		achievements.add(gandhi);

	}
}

class Trump extends Achievement//self explanatory
{
	public Trump()
	{
		super("I will build a great, great wall", // name
		      "Have more than one hundred armies deployed "// description
		      + "in the United States",
		      50);// bonus
	}

	@Override
	public boolean isFullfilled(Player p)
	{
		if(p.ownsTerritory("Eastern United States")
		        && p.ownsTerritory("Western United States"))
		{
			if(TerritoryMap.getNumArmiesDeployedOn("Eastern United States")
			        + TerritoryMap.getNumArmiesDeployedOn("Western United States") > 100)
			{
				return true;
			}
		}

		return false;
	}
}
class IslandHopping extends Achievement
{
	public IslandHopping()
	{
		super("Island Hopping",
		      "Conquer every island on the map",
		      20);
		//a one time bonus of 20 armies
	}
	public boolean isFullfilled(Player p)
	{
		return p.ownsTerritory("Philippines") && p.ownsTerritory("Indonesia")
		       && p.ownsTerritory("Hawaii") && p.ownsTerritory("New Guinea")
		       && p.ownsTerritory("Eastern Australia") && p.ownsTerritory("Western Australia")
		       && p.ownsTerritory("Iceland");
		//owns every island
	}
}

class NuclearGandhi extends Achievement
{
	public NuclearGandhi()
	{
		super("Nuclear Gandhi"
		      , "Our words are backed by NUCLEAR WEAPONS!",
		      40);
	}

	@Override
	public boolean isFullfilled(Player p)//Own 3 continents
	{
		int continents = 0;
		for(String continent : TerritoryMap.getAllContinents())
		{
			if(p.ownsContinent(continent))
			{
				continents++;
			}
		}
		return continents >= 3;
	}

}
class RussianWinter extends Achievement
{
	public RussianWinter()
	{
		super("If you're going to attack Russia in winter...",
		      "...pack some winter clothes",
		      20);
	}

	@Override
	public boolean isFullfilled(Player p)
	{

		return false;
	}

}
