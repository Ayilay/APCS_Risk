package battle;
import java.util.LinkedList;
import java.util.Queue;

import main.Player;

/*
 * This class will keep a record of everything that goes on in the game.
 */
public class Timeline
{
	private Queue<String> timeline;
	private int numTurns;

	public Timeline()
	{
		timeline = new LinkedList<String>();
		numTurns = 1;
	}

	public void incrementTurns()
	{
		numTurns++;
	}
	
	public void addVictoryToTimeline(String territoryConquered, Player p)
	{
		String entry = "Turn " + numTurns + ": " + p.getName() + " conquered " + territoryConquered + "!";
		timeline.add(entry);
	}

	public void addDefenseVictory(String territoryDefended, Player p)
	{
		String entry = "Turn " + numTurns + ": " + p.getName() + "'s attack on " + territoryDefended + " was repulsed!";
		timeline.add(entry);
	}

	public void addPlayerConquered(Player p)
	{
		String entry = "Turn " + numTurns + ": " + p.getName() + " was eliminated from the game!";
		timeline.add(entry);
	}
	@Override
	public String toString()
	{
		String theTimeline = "";
		for(String s : timeline)
		{
			theTimeline += s;
			theTimeline += "\n";
		}
		return theTimeline;
	}
}
