import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;

public class CLIManager implements UserInterface
{
	BufferedReader br;

	public CLIManager()
	{
		br = new BufferedReader(new InputStreamReader(System.in));
	}

	////////////////////////////////////////////////////////////
	//	Init Methods
	////////////////////////////////////////////////////////////

	@Override
	public int getNumPlayers()
	{
		System.out.println("How many players will be playing?");
		boolean valid = false;
		String input = "";
		int numPlayers = 0;
		while(!valid)
		{
			try
			{
				input = br.readLine();
				numPlayers = Integer.parseInt(input);
			}
			catch(NumberFormatException e)
			{
				generateWarning("Bad number of players");
				continue;
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}

			if(numPlayers < 2 || numPlayers > 5)
			{
				generateWarning("Too many/too little");
			}
			else
			{
				valid = true;
			}
		}
		return numPlayers;
	}

	@Override
	public String getPlayerName()
	{
		System.out.print("Enter player name: ");

		String name = "";
		try
		{
			name = br.readLine();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

		return name;
	}

	@Override
	public String getStartingTerritory(String playerName)
	{
		System.out.print("Enter starting territory for " + playerName + ": ");

		String territoryID = "";
		try
		{
			territoryID = br.readLine();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

		return territoryID;
	}

	////////////////////////////////////////////////////////////
	//	Pre Player Turn Methods
	////////////////////////////////////////////////////////////

	@Override
	public void promptPlayerTurn(Player p)
	{
		String playerName = p.getName();
		Set<String> territories = p.getOccupiedTerritories();

		System.out.println("====================");
		System.out.println(playerName + "'s turn");
		System.out.println("--------------------");
		System.out.println(playerName + "'s territories:");
		System.out.println(territories);
		System.out.println(playerName + "'s cards");
		for(Card c : p.getCards())
		{
			System.out.println(c.toString());
		}
	}

	////////////////////////////////////////////////////////////
	//	Deploy Armies methods
	////////////////////////////////////////////////////////////

	@Override
	public String selectDeployTerritory(Player p)
	{
		System.out.println("Total reinforcements for " + p.getName() + ": " + p.getNumReinforcementsAvailable());
		System.out.print("Select territory to deploy to: ");

		String terr = "";
		try
		{
			terr = br.readLine();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

		return terr;
	}

	@Override
	public int getNumArmiesToDeploy(Player p, String deployTerritory)
	{
		System.out.println(deployTerritory + " has " + TerritoryMap.getNumArmiesDeployedOn(deployTerritory) + " armies on it");
		System.out.print("How many armies to deploy? (default 1): ");

		String numArmiesStr =  "";
		int numArmies = 1;

		// keep trying to parse valid input
		while(true)
		{
			try
			{
				numArmiesStr = br.readLine();
				if(numArmiesStr.equals(""))
					numArmies = 1;
				else if(numArmiesStr.equals("all"))
					numArmies = p.getNumReinforcementsAvailable();
				else
					numArmies = Integer.parseInt(numArmiesStr);
			}
			catch(NumberFormatException e) // In case user enters non-numerical value
			{
				generateWarning("bad input, must be number, try again");
				continue;
			}
			catch(IOException e)
			{
				e.printStackTrace();
				continue;
			}
			break;
		}

		return numArmies;
	}

	////////////////////////////////////////////////////////////
	//	Utility Methods
	////////////////////////////////////////////////////////////

	@Override
	public void generateWarning(String string)
	{
		System.err.println(string);
	}
}