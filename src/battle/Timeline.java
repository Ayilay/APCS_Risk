package battle;
import java.util.LinkedList;
import java.util.Queue;

import main.Player;

/*
 * This class will keep a record of everything that goes on in the game.
 */
public class Timeline
{
	Queue<String> timeline;

	public Timeline()
	{
		timeline = new LinkedList<String>();
	}

	public void addVictoryToTimeline(int turn, String territoryConquered, Player p)
	{
		String entry = "Turn " + turn + ": " + p.getName() + " conquered " + territoryConquered + "!";
		timeline.add(entry);
	}

	public void addDefenseVictory(int turn, String territoryDefended, Player p)
	{
		String entry = "Turn " + turn + ": " + p.getName() + "'s attack on " + territoryDefended + " was repulsed!";
		timeline.add(entry);
	}

	public void addPlayerConquered(int turn, Player p)
	{
		String entry = "Turn " + turn + ": " + p.getName() + " was eliminated from the game!";
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
