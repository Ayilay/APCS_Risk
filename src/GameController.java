import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class GameController
{
	private ArrayList<Player> players;
	private Timeline timeline;
	private int turn;
	private int currentPlayerTurn; // Player currently playing

	private static UserInterface userInterface;

	public GameController()
	{
		players = new ArrayList<Player>();
		userInterface = new CLIManager();
		timeline = new Timeline();

		turn = 0;
		currentPlayerTurn = 0;

		TerritoryMap.init();
		AchievementManager.init();

		// Get player names and starting territories
		initPlayers();
	}

	public void play()
	{
		while(isStillPlaying())
		{
			Player p = getNextPlayer();
			userInterface.promptPlayerTurn(p);

			// Perform the player actions
			deployReinforcements(p);
			attackTerritory(p);
			fortifyTroops(p);

			turn ++;
		}
	}

	////////////////////////////////////////////////////////////
	//	Player Turn Methods
	////////////////////////////////////////////////////////////

	private void deployReinforcements(Player p)
	{
		p.calculateReinforcements();
		while(p.getNumReinforcementsAvailable() > 0)
		{
			String territory = userInterface.selectDeployTerritory(p);

			if(!TerritoryMap.isValidTerritory(territory))
			{
				userInterface.generateWarning(territory + " is not a valid territory");
				continue;
			}
			if(!p.ownsTerritory(territory))
			{
				userInterface.generateWarning("You do not own this territory, try again");
				continue;
			}

			int numArmies = userInterface.getNumArmiesToDeploy(p, territory);
			// Check if numArmies is valid
			if(numArmies < 0 || numArmies > p.getNumReinforcementsAvailable())
			{
				userInterface.generateWarning("You do not have such number of armies, try again");
			}
			else if(numArmies == 0)
			{
				userInterface.generateWarning("Cancelling deploy to " + territory);
			}
			else
			{
				p.deployReinforcements(territory, numArmies);
			}
		}
	}

	private void attackTerritory(Player p)
	{
		boolean doneAttacking = false;
		while(!doneAttacking)
		{
			doneAttacking = userInterface.getFinishedAttacking();
			if(doneAttacking)
				break;

			// Get territory to attack
			String territoryToAttackID = "";
			while(true)
			{
				territoryToAttackID = userInterface.getTerritoryToAttack(p);
				if(!p.isValidAttackTarget(territoryToAttackID))
				{
					userInterface.generateWarning("not a valid territory to attack");
					continue;
				}

				break;
			}
			Territory territoryToAttack = TerritoryMap.get(territoryToAttackID);

			// Get territory to attack from
			String territoryToAttackFromID = "";
			while(true)
			{
				territoryToAttackFromID = userInterface.getTerritoryToAttackFrom(p, territoryToAttackID);
				if(!territoryToAttack.isNeighborWith(territoryToAttackFromID))
				{
					userInterface.generateWarning("Cannot attack from selected territory");
					continue;
				}

				break;
			}
			Territory territoryToAttackFrom = TerritoryMap.get(territoryToAttackFromID);

			// Get number of armies to attack with
			int numArmies = 0;
			while(true)
			{
				numArmies = userInterface.getNumArmiesToAttackWith(p, territoryToAttackID, territoryToAttackFromID);
				if(numArmies < 0 || numArmies > territoryToAttackFrom.getNumArmies() - 1)
				{
					userInterface.generateWarning("Bad number of armies");
					continue;
				}

				break;
			}

			// Do the actual battle
			BattleResults results = p.attackTerritory(territoryToAttackFromID, territoryToAttackID, numArmies);
			userInterface.displayBattleResults(results);
			if(results.getAttackSuccess())
			{
				timeline.addVictoryToTimeline(turn, territoryToAttack.getID(), p);
				Card c = CardDeck.deal();
				p.addCards(c);
			}
			else
			{
				timeline.addDefenseVictory(turn, territoryToAttack.getID(), p);
			}
		}

	}

	private void fortifyTroops(Player p)
	{
		// TODO Auto-generated method stub

	}

	////////////////////////////////////////////////////////////
	//	Utility Methods
	////////////////////////////////////////////////////////////

	private void initPlayers()
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
				if(getPlayerNames().contains(name))
				{
					userInterface.generateWarning("Already player with that name");
					valid = false;
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

			players.add(new Player(name, territory));
		}
	}

	private Set<String> getPlayerNames()
	{
		Set<String> names = new HashSet<String>();
		for(Player p : players)
			names.add(p.getName());

		return names;
	}

	private boolean isStillPlaying()
	{
		return players.size() != 1;
	}

	private Player getNextPlayer()
	{
		if(currentPlayerTurn >= players.size())
			currentPlayerTurn = 0;
		Player p = players.get(currentPlayerTurn);

		// TODO: Check if player is eliminated somewhere else
		if(p.hasNoTerritories())
		{
			players.remove(currentPlayerTurn);
			p = players.get(currentPlayerTurn);
		}

		currentPlayerTurn ++;

		return p;
	}

	//TODO: copied and pasted code below, integrate with new model
	/*
	////////////////////////////////////////////////////////////
	// Player Turn Methods
	////////////////////////////////////////////////////////////

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
	*/
}
