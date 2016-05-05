import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class GameController
{
	private ArrayList<Player> players;
	private Timeline timeline;
	private int turn = 0;

	public GameController()
	{
		TerritoryMap.init();
		AchievementManager.init();

		players = new ArrayList<Player>();
	}

	public void addPlayer(Player p)
	{
		players.add(p);
	}

	public Set<String> getPlayerNames()
	{
		Set<String> names = new HashSet<String>();
		for(Player p : players)
			names.add(p.getName());

		return names;
	}
}
