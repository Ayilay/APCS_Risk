import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Set;

public class Main
{
	private ArrayList<Player> players;
	private Timeline timeline;
	private int turn = 0;

	// for cmd line inputs, will be replaced by GUI input
	private BufferedReader br;

	public static void main(String[] args) throws IOException
	{
		Main riskGame = new Main();
		riskGame.play();
	}

	private void play() throws IOException
	{
		// TODO: implement GUI
		init();
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
			System.out.println(territory + " has " + TerritoryMap.get(territory).getNumArmies() + " armies on it");
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
					numArmies = TerritoryMap.get(territoryFromID).getNumArmies() - 1;
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

	/*
	 * Initialize the players and the TerritoryMap
	 */
	private void init() throws IOException
	{
		TerritoryMap.init();
		timeline = new Timeline();

		// For cmd line inputs, will be replaced by GUI inputs
		br = new BufferedReader(new InputStreamReader(System.in));

		int numPlayers = getNumPlayers();
		players = new ArrayList<Player>();

		for(int i = 0; i < numPlayers; i++)
		{
			System.out.println("Enter player name");
			String name = br.readLine();
			String territory = "";
			boolean valid = false;
			while(!valid)
			{
				System.out.println("Enter Starting Territory");
				territory = br.readLine();
				if(TerritoryMap.get(territory) == null||TerritoryMap.get(territory).getOccupier()!=null)
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

	private int getNumPlayers() throws IOException
	{
		System.out.println("How many players will be playing?");
		boolean valid = false;
		String input = "";
		int numPlayers = 0;
		while(!valid)
		{
			input = br.readLine();
			try
			{
				numPlayers = Integer.parseInt(input);
			}
			catch(NumberFormatException e)
			{
				System.err.println("Bad number of players");
				continue;
			}
			if(numPlayers <= 0 || numPlayers > 5)
			{
				System.out.println("Too many/too little");
			}
			else
			{
				valid = true;
			}
		}
		return numPlayers;
	}
}
