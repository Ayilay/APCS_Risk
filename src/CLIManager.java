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

		int numPlayers = 0;
		while(true)
		{
			numPlayers = getNumberInput(0);

			if(numPlayers < 2 || numPlayers > 5)
			{
				generateWarning("Too many/too little");
			}
			else
			{
				break;
			}
		}

		return numPlayers;
	}

	@Override
	public String getPlayerName()
	{
		System.out.print("Enter player name: ");

		String name = getStringInput();
		return name;
	}

	@Override
	public String getStartingTerritory(String playerName)
	{
		System.out.print("Enter starting territory for " + playerName + ": ");

		String territoryID = getStringInput();
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
		//System.out.println(playerName + "'s cards");
		//for(Card c : p.getCards())
		//{
		//	System.out.println(c.toString());
		//}
	}

	////////////////////////////////////////////////////////////
	//	Deploy Armies methods
	////////////////////////////////////////////////////////////

	@Override
	public String getDeployTerritory(Player p)
	{
		System.out.println("Total reinforcements for " + p.getName() + ": " + p.getNumReinforcementsAvailable());
		System.out.print("Select territory to deploy to: ");

		String terr = getStringInput();
		return terr;
	}

	@Override
	public int getNumArmiesToDeploy(Player p, String deployTerritory)
	{
		System.out.println(deployTerritory + " has " + TerritoryMap.getNumArmiesDeployedOn(deployTerritory) + " armies on it");
		System.out.print("How many armies to deploy? (default 1): ");

		int numArmies = 1;

		numArmies = getNumberInput(p.getNumReinforcementsAvailable());

		return numArmies;
	}

	////////////////////////////////////////////////////////////
	//	Attack Territories methods
	////////////////////////////////////////////////////////////

	@Override
	public boolean getFinishedAttacking()
	{
		System.out.print("Do you wish to continue attacking? (y/n): ");

		String input = getStringInput();

		if(!(input.equals("")) && input.toLowerCase().charAt(0) == 'n')
		{
			System.out.println("Done attacking");
			return true;
		}
		return false;
	}

	@Override
	public String getTerritoryToAttack(Player p)
	{
		System.out.println("Choose a territory to attack");
		System.out.println(p.getAttackTargets());

		String terr = getStringInput();
		return terr;
	}

	@Override
	public String getTerritoryToAttackFrom(Player p, String territoryToAttack)
	{
		System.out.println("Choose a territory to attack from:");
		System.out.println(TerritoryMap.get(territoryToAttack).getAdjacentTerritoriesOccupiedBy(p));

		String terr = getStringInput();
		return terr;
	}

	@Override
	public int getNumArmiesToAttackWith(Player p, String territoryToAttackID, String territoryToAttackFromID)
	{
		int maxArmies = getNumberInput(TerritoryMap.getNumArmiesDeployedOn(territoryToAttackFromID) - 1);
		System.out.println("Choose number of armies to attack with. Opponent has " + TerritoryMap.getNumArmiesDeployedOn(territoryToAttackID));
		System.out.println("You have " + maxArmies + " armies (1 must stay behind)");

		int numArmies = getNumberInput(maxArmies);
		return numArmies;
	}

	@Override
	public void displayBattleResults(BattleResults results)
	{
		System.out.println(results.getAttackSuccess() ? "Attacker Won" : "Defender Won");
		System.out.println("Attacker Losses: " + results.getNumAttackerLosses());
		System.out.println("Defender Losses: " + results.getNumDefenderLosses());
	}

	////////////////////////////////////////////////////////////
	//	Fortify Troops methods
	////////////////////////////////////////////////////////////

	@Override
	public String getTerritoryToFortify(Player p)
	{
		System.out.println("Select a territory to fortify");
		System.out.println(p.getOccupiedTerritories());

		String terr = getStringInput();
		return terr;
	}

	@Override
	public String getTerritoryToFortifyFrom(Player p, String terrID)
	{
		Territory terr = TerritoryMap.get(terrID);

		System.out.println("Select a territory to fortify from");
		System.out.println(terr.getAdjacentTerritoriesOccupiedBy(p));

		String terrToFortifyFrom = getStringInput();
		return terrToFortifyFrom;
	}

	public int getNumArmiesToFortify(String terrToFortFrom)
	{
		int maxArmies = TerritoryMap.getNumArmiesDeployedOn(terrToFortFrom);
		System.out.println("Number of armies to send (max of " + maxArmies + "):");

		int numArmies = getNumberInput(maxArmies);
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

	public void nullInput()
	{
		try
		{
			br.readLine();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	private String getStringInput()
	{
		String str = "";
		try
		{
			str = br.readLine();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

		return str;
	}

	private int getNumberInput(int defaultMax)
	{
		int num = 0;
		String numStr = "";
		while(true)
		{
			try
			{
				numStr = br.readLine();
				if(numStr.equals(""))
					num = 1;
				else if(numStr.equals("all"))
					num = defaultMax;
				else
				{
					num = Integer.parseInt(numStr);
				}
			}
			catch(IOException e)
			{
				e.printStackTrace();
				continue;
			}
			catch(NumberFormatException e)
			{
				System.err.println("Bad input, need integer input");
				return -1;
			}

			break;
		}

		return num;
	}
}