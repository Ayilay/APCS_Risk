import java.io.IOException;

public class Main
{
	private static GameController riskGameController; 
	private static UserInterface userInterface;

	public static void main(String[] args) throws IOException
	{
		riskGameController = new GameController();
		userInterface = new CLIManager();
		
		// Get player names and starting territories
		initPlayers();
	}
	
	private static void initPlayers()
	{
		int numPlayers = userInterface.getNumPlayers();
		for(int i = 0; i < numPlayers; i++)
        {
			String name = "";
			boolean valid = false;
			while(!valid)
			{
				valid = true;
				name = userInterface.getPlayerName();
				for(String takenName : riskGameController.getPlayerNames())
				{
					if(takenName.equals(name))
					{
						userInterface.generateWarning("Already player with that name");
						valid = false;
					}
				}
			}
			String territory = "";
			valid = false;
			while(!valid)
			{
				territory = userInterface.getStartingTerritory(name);
				if(TerritoryMap.get(territory) == null ||
                    TerritoryMap.getOccupierOnTerritory(territory) != null)
				{
					userInterface.generateWarning("Not a valid territory");
				}
				else
				{
					valid = true;
				}
			}

			riskGameController.addPlayer(new Player(name, territory));
        }
	}

	//TODO: copied and pasted code below, integrate with new model
	/*
	public void play() throws IOException
	{
		while(players.size() != 1)
		{
			for(int i = 0; i < players.size(); i++)
			{
				Player p = players.get(i);
				if(p.equals(players.get(0)))
				{
					turn++;//increment turn count when back to first player
				}
				if(p.getOccupiedTerritories().isEmpty())
				{
					players.remove(i);
					i--;
					timeline.addPlayerConquered(turn, p);
				}
				else
				{
					// CMDLine stuff, will be replaced by GUI Output
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

					// Phase 1: Deploy Reinforcements
					deployReinforcements(p);

					// Phase 2: Attack a territory
					attackOther(p);

					// Phase 3: Fortify an owned territory
					fortifyTroops(p);
				}
			}
		}
		System.out.println("Congratulations, " + players.get(0).getName() + ". You won!");
		System.out.println("See timeline? (y/n)");
		String answer = br.readLine();
		if(answer.equals("y"))
		{
			System.out.println(timeline.toString());
		}
	}

	////////////////////////////////////////////////////////////
	// Player Turn Methods
	////////////////////////////////////////////////////////////

	private void deployReinforcements(Player p) throws IOException
	{

		System.out.println("Phase 1: Deploy Reinforcements");
		p.calculateReinforcements();
		while(p.getNumReinforcementsAvailable() > 0)
		{
			System.out.println("Total reinforcements for " + p.getName() + ": " + p.getNumReinforcementsAvailable());
			System.out.print("Select territory to deploy to: ");
			String territory = br.readLine();

			if(!p.ownsTerritory(territory))
			{
				System.err.println("You do not own this territory. Try again");
				continue;
			}

			// Get the number of armies to deploy. Can also enter "all"
			// If nothing entered, then assume 1 army
			System.out.println(territory + " has " + TerritoryMap.getNumArmiesDeployedOn(territory) + " armies on it");
			System.out.print("How many armies to deploy? (default 1): ");
			String numArmiesStr = br.readLine();
			int numArmies = 1;
			try
			{
				if(numArmiesStr.equals(""))
					numArmies = 1;
				else if(numArmiesStr.equals("all"))
					numArmies = p.getNumReinforcementsAvailable();
				else
					numArmies = Integer.parseInt(numArmiesStr);
			}
			catch(NumberFormatException e) // In case user enters
				// non-numerical value
			{
				System.err.println("Bad number of armies to deploy, try again");
				continue;
			}

			// Check if numArmies is valid
			if(numArmies < 0 || numArmies > p.getNumReinforcementsAvailable())
			{
				System.err.println("You do not have such number of armies, try again");
			}
			else if(numArmies == 0)
			{
				System.out.println("Cancelling deploy to " + territory);
			}
			else
			{
				System.out.println("Deploying " + numArmies + " to " + territory);
				p.deployReinforcements(territory, numArmies);
			}
			System.out.println();
		}
	}

	private void attackOther(Player p) throws IOException
	{
		boolean doneAttacking = false;
		while(!doneAttacking)
		{
			System.out.print("Do you wish to continue attacking? (y/n): ");
			String input = br.readLine();
			if(!(input.equals("")) && input.toLowerCase().charAt(0) == 'n')
			{
				doneAttacking = true;
				System.out.println("Done attacking");
				return;
			}

			String territoryFromID = "", territoryToID = "";
			int numArmies = 0;

			// Choose a territory to attack
			System.out.println("Choose a territory to attack");
			boolean valid = false;
			System.out.println(p.getAttackTargets());
			while(!valid)
			{
				territoryToID = br.readLine();
				if(p.ownsTerritory(territoryToID))
				{
					System.out.println("Nice try you own this one");
				}
				else if(!p.canAttack(territoryToID))
				{
					System.out.println("Can only attack neighboring territories");
				}
				else
				{
					valid = true;
				}
			}
			Territory territoryTo = TerritoryMap.get(territoryToID);

			// Get territory to attack from
			Set<String> available = territoryTo.getAdjacentTerritoriesOccupiedBy(p);
			System.out.println("Choose a territory to attack from:");
			System.out.println(available);
			valid = false;
			while(!valid)
			{
				territoryFromID = br.readLine();
				if(!territoryTo.isNeighborWith(territoryFromID))
				{
					System.out.println("no");
				}
				else
				{
					valid = true;
				}
			}
			Territory territoryFrom = TerritoryMap.get(territoryFromID);

			// Get number of armies to attack with
			System.out.println("Choose number of armies to attack with. Opponent has " + territoryTo.getNumArmies());
			System.out.println("You have: " + territoryFrom.getNumArmies());
			valid = false;
			numArmies = 0;
			while(!valid)
			{
				String numArmiesStr = br.readLine();
				if(numArmiesStr.equals(""))
					numArmies = 1;
				else if(numArmiesStr.equals("all"))
					numArmies = TerritoryMap.getNumArmiesDeployedOn(territoryFromID) - 1;
				else
				{
					try
					{
						numArmies = Integer.parseInt(numArmiesStr);
					}
					catch(NumberFormatException e)
					{
						System.err.println("Bad number of armies");
						continue;
					}
				}
				if(numArmies < 0 || numArmies > territoryFrom.getNumArmies() - 1)
				{
					System.err.println("Bad number of armies");
				}
				else
				{
					valid = true;
				}
			}
			boolean result = p.attackOther(territoryFrom, territoryTo, numArmies);
			if(result)
			{
				timeline.addVictoryToTimeline(turn, territoryTo.getID(), p);
				Card c = CardDeck.deal();
				p.addCards(c);
			}
			else
			{
				timeline.addDefenseVictory(turn, territoryTo.getID(), p);
			}
		}
	}

	private void fortifyTroops(Player p) throws IOException
	{
		if(p.getOccupiedTerritories().size() == 1)
		{
			System.out.println("Nothing to fortify");
			return;
		}

		Boolean isDone = false;
		while(!isDone)
		{
			System.out.println("Select a territory to fortify");
			System.out.println(p.getOccupiedTerritories());
			String fortified = br.readLine();

			if(fortified.equals(""))
			{
				System.out.println("Not fortifying anything");
				isDone = true;
				continue;
			}
			if(!p.ownsTerritory(fortified))
			{
				System.err.println("You do not own this territory");
				continue;
			}
			Territory fortifiedTer = TerritoryMap.get(fortified);
			System.out.println(fortified + " has " + fortifiedTer.getNumArmies() + " armies");

			System.out.println("Select a territory to fortify from");
			System.out.println(fortifiedTer.getAdjacentTerritoriesOccupiedBy(p));
			String fortifier = br.readLine();

			if(!p.ownsTerritory(fortifier))
			{
				System.err.println("You do not own this territory");
				continue;
			}

			Territory fortifierTer = TerritoryMap.get(fortifier);
			System.out.println(fortifier + " has " + fortifierTer.getNumArmies() + " armies");

			if(!fortifiedTer.isNeighborWith(fortifier))
			{
				System.err.println("territories are not neighbors");
				continue;
			}

			System.out.println("Number of armies to send (default 1):");
			String numArmiesStr = br.readLine();
			int numArmies;

			if(numArmiesStr.equals(""))
				numArmies = 1;
			else if(numArmiesStr.equals("all"))
				numArmies = fortifierTer.getNumArmies() - 1;
			else
			{
				try
				{
					numArmies = Integer.parseInt(numArmiesStr);
				}
				catch(NumberFormatException e)
				{
					System.err.println("Bad number of armies");
					continue;
				}
			}

			if(numArmies >= fortifierTer.getNumArmies())
			{
				System.err.println("Number of armies exceeds the amount ");
				continue;
			}
			else if(numArmies < 0)
			{
				System.err.println("Invalid number of armies");
				continue;
			}
			else if(numArmies == 0)
			{
				System.out.println("Not fortifying anything");
				isDone = true;
				continue;
			}

			fortifierTer.decrementArmiesBy(numArmies);
			fortifiedTer.incrementArmiesBy(numArmies);

			isDone = true;
		}
	}

	////////////////////////////////////////////////////////////
	// Init Methods
	////////////////////////////////////////////////////////////

	private void init() throws IOException
	{
		TerritoryMap.init();
		AchievementManager.init();
		timeline = new Timeline();

		// For cmd line inputs, will be replaced by GUI inputs
		br = new BufferedReader(new InputStreamReader(System.in));

		int numPlayers = getNumPlayers();
		players = new ArrayList<Player>();

		for(int i = 0; i < numPlayers; i++)
		{
			String name = "";
			boolean valid = false;
			while(!valid)
			{
				valid = true;
				System.out.println("Enter player name");
				name = br.readLine();
				for(Player p : players)
				{
					if(p.getName().equals(name))
					{
						System.out.println("Already player with that name");
						valid = false;
					}
				}
			}
			String territory = "";
			valid = false;
			while(!valid)
			{
				System.out.println("Enter Starting Territory");
				territory = br.readLine();
				if(TerritoryMap.getOccupierOnTerritory(territory) == null||TerritoryMap.get(territory)!=null)
				{
					System.out.println("Not a valid territory");
				}
				else
				{
					valid = true;
				}
			}
			players.add(new Player(name, territory));
		}

		CardDeck.init(TerritoryMap.getAllTerritories());
	}
	*/
}
