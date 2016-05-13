package userInterface;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;

import battle.BattleResults;
import card.Card;
import main.Player;
import territoryMap.Territory;
import territoryMap.TerritoryMap;

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

		System.out.println(playerName + "'s cards");
		if(!p.hasCards())
		{
			System.out.println("(No Cards)");
		}
		else
		{
			for(Card c : p.getCards())
			{
				System.out.print("[" + c.toString() + "] ");
			}
			System.out.println();
		}
	}

	////////////////////////////////////////////////////////////
	//	Use Card Methods
	////////////////////////////////////////////////////////////
	@Override
	public boolean promptUseCard()
	{
		String response = "";
		boolean isDone = false;
		while(!isDone)
		{
			System.out.println("Use a Card? Enter Y for yes or N for no ");
			response = getStringInput();
			
			if(!response.equalsIgnoreCase("y") && !response.equalsIgnoreCase("n"))
			{
				System.out.println("Enter Yes or No");
				continue;
			}	
			isDone = true;
		}
		
		if(response.equalsIgnoreCase("n"))
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	public boolean promptTradeCard()
	{
		String response = "";
		boolean isDone = false;
		while(!isDone)
		{
			System.out.println("Trade Cards? Enter Y for yes or N for no ");
			response = getStringInput();
			
			if(!response.equalsIgnoreCase("y") && !response.equalsIgnoreCase("n"))
			{
				System.out.println("Enter Yes or No");
				continue;
			}	
			isDone = true;
		}
		
		if(response.equalsIgnoreCase("n"))
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	public Card selectCard(Player p)
	{
		if(!p.hasCards())
		{
			System.out.println("You do not have any cards");
			return null;
		}
		
		String territory = "";
		int value = 0;
		Card c = null;

		boolean isDone = false;
		while(!isDone)
		{
			System.out.println("Enter the Territory of the card you want to use. Press Q to quit ");
			territory = getStringInput();

			if(territory.equalsIgnoreCase("q"))
				return null;

			System.out.println("Enter the number of armies of the card. Press Q to quit ");
			value = getNumberInput(3);

			if(territory.equalsIgnoreCase("q"))
				return null;

			c = new Card(territory, value);

			if(!p.getCards().contains(c))
			{
				System.out.println("You do not own this card");
				continue;
			}
			else if(!p.getOccupiedTerritories().contains(c.getTerritory()))
			{
				System.out.println("You do not own this territory");
				continue;
			}

			isDone = true;

		}
		return c;
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
		System.out.println(TerritoryMap.get(territoryToAttack).getValidAttackerTerritoriesOccupiedBy(p));

		String terr = getStringInput();
		return terr;
	}

	@Override
	public int getNumArmiesToAttackWith(Player p, String territoryToAttackID, String territoryToAttackFromID)
	{
		int maxArmies = TerritoryMap.getNumArmiesDeployedOn(territoryToAttackFromID) - 1;
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
	public boolean getWantsToFortify(Player p)
	{
		System.out.print("Do you want to fortify? (y/n): ");
		String input = getStringInput();

		while(true)
		{
			if(input.length() == 0)
				return false;

			switch(input.toLowerCase().charAt(0))
			{
				case 'y':
					return true;
				case 'n':
					return false;
				default:
					generateWarning("Bad input, try again");
					continue;
			}
		}

	}

	@Override
	public String getTerritoryToFortify(Player p)
	{
		System.out.println("Select a territory to fortify");
		System.out.println(p.getFortifyTargets());

		String terr = getStringInput();
		return terr;
	}

	@Override
	public String getTerritoryToFortifyFrom(Player p, String terrID)
	{
		Territory terr = TerritoryMap.get(terrID);

		System.out.println("Select a territory to fortify from");
		System.out.println(terr.getValidFortifiers());

		String terrToFortifyFrom = getStringInput();
		return terrToFortifyFrom;
	}

	public int getNumArmiesToFortify(String terrToFortFrom)
	{
		int maxArmies = TerritoryMap.getNumArmiesDeployedOn(terrToFortFrom) - 1;
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