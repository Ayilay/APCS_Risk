package main;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import achievement.AchievementManager;
import battle.BattleResults;
import battle.Timeline;
import card.Card;
import card.CardDeck;
import territoryMap.Territory;
import territoryMap.TerritoryMap;
import userInterface.GUIManager;
import userInterface.UserInterface;

public class GameController
{
	private ArrayList<Player> players;
	private Timeline timeline;
	private int turn;
	private int currentPlayerTurn; // Player currently playing
	private CardDeck deck;
	private static UserInterface userInterface;

	public GameController()
	{
		players = new ArrayList<Player>();
		userInterface = new GUIManager();
		timeline = new Timeline();

		turn = 0;
		currentPlayerTurn = 0;


		TerritoryMap.init();
		deck = new CardDeck(TerritoryMap.getAllTerritories());
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
			useCards(p);
			deployReinforcements(p);
			attackTerritory(p);
			fortifyTroops(p);

			turn ++;
		}
	}

	////////////////////////////////////////////////////////////
	//	Player Turn Methods
	////////////////////////////////////////////////////////////

	private void useCards(Player p)
	{
		if(!p.hasCards())
			return;
		
		if(userInterface.promptUseCard())
		{
			Card c = userInterface.selectCard(p);
			if(c == null)
				return;
			p.getCards().remove(c);
			p.deployReinforcements(c.getTerritory(), c.getValue());
		}	
		
		if(userInterface.promptTradeCard())
		{
			if(p.getCards().size() < 3)
			{
				userInterface.generateWarning("You do not have enough cards");
				return;
			}
			Card c1 = null;
			Card c2 = null;
			Card c3 = null;
			boolean isDone = false;

			while(!isDone)
			{
				System.out.println("Select 3 cards to trade. The three cards must be the same value");
				c1 = userInterface.selectCard(p);
				c2 = userInterface.selectCard(p);
				c3 = userInterface.selectCard(p);
				if(c1.getValue() != c2.getValue() || c2.getValue() != c3.getValue() || c1.getValue() != c3.getValue())
				{
					userInterface.generateWarning("Must be 3 Cards of the same value");
					continue;
				}
				isDone = true;
			}

			p.getCards().remove(c1);
			p.getCards().remove(c2);
			p.getCards().remove(c3);
			deck.getDeck().add(c1);
			deck.getDeck().add(c2);
			deck.getDeck().add(c3);

			p.addReinforcements((p.getSetsTraded() + 1) * 2);
			p.incrementSets();
		}
		
	}

	private void deployReinforcements(Player p)
	{
		p.calculateReinforcements();
		while(p.getNumReinforcementsAvailable() > 0)
		{
			String territory = userInterface.getDeployTerritory(p);

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
		while(true)
		{
			// Check if user has enough armies to attack with
			if(!p.hasArmiesToAttackWith())
			{
				userInterface.generateWarning("You have no more armies to attack with!");
				break;
			}

			// See if user wants to continue attacking
			if(userInterface.getFinishedAttacking())
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
				if(TerritoryMap.getNumArmiesDeployedOn(territoryToAttackFromID) < 2)
				{
					userInterface.generateWarning("Selected territory does not have enough armies to attack");
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
				Card c = deck.deal();
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
		if(p.getOccupiedTerritories().size() == 1)
		{
			userInterface.generateWarning("Nowhere to fortify from, skipping turn");
			return;
		}
		if(!p.getCanFortify())
		{
			userInterface.generateWarning("Not enough armies to fortify anything, skipping turn");
			return;
		}

		if(!userInterface.getWantsToFortify(p))
			return;

		while(true)
		{
			// Get territory to fortify
			String territoryToFortifyID = userInterface.getTerritoryToFortify(p);
			if(!p.canFortifyTerritory(territoryToFortifyID))
			{
				userInterface.generateWarning("You cannot fortify this territory");
				continue;
			}
			Territory territoryToFortify = TerritoryMap.get(territoryToFortifyID);

			// Get territory to fortify from
			String territoryToFortifyFromID = userInterface.getTerritoryToFortifyFrom(p, territoryToFortifyID);
			if(!TerritoryMap.isValidTerritory(territoryToFortifyFromID))
			{
				userInterface.generateWarning("Not a valid territory");
				continue;
			}
			if(!territoryToFortify.isValidFortifier(territoryToFortifyFromID))
			{
				userInterface.generateWarning("Not a valid territory to fortify from");
				continue;
			}

			// Get num armies to fortify with
			int numArmies = userInterface.getNumArmiesToFortify(territoryToFortifyFromID);
			if(numArmies >= TerritoryMap.getNumArmiesDeployedOn(territoryToFortifyFromID))
			{
				userInterface.generateWarning("Don't have that many armies to fortify with");
				continue;
			}
			else if(numArmies < 0)
			{
				userInterface.generateWarning("Invalid number of armies");
				continue;
			}
			else if(numArmies == 0)
			{
				userInterface.generateWarning("Cancelling Fortification");
				break;
			}

			TerritoryMap.transferArmies(territoryToFortifyFromID, territoryToFortifyID, numArmies);
			break;
		}
	}

	////////////////////////////////////////////////////////////
	//	Utility Methods
	////////////////////////////////////////////////////////////

	private void initPlayers()
	{
		//int numPlayers = userInterface.getNumPlayers();

		//for(int i = 0; i < numPlayers; i++)
		//{
		//	String name = "";
		//	boolean valid = false;
		//	while(!valid)
		//	{
		//		valid = true;
		//		name = userInterface.getPlayerName();
		//		if(getPlayerNames().contains(name))
		//		{
		//			userInterface.generateWarning("Already player with that name");
		//			valid = false;
		//		}
		//	}
		//	String territory = "";
		//	valid = false;
		//	while(!valid)
		//	{
		//		territory = userInterface.getStartingTerritory(name);
		//		if(TerritoryMap.get(territory) == null ||
		//		        TerritoryMap.getOccupierOnTerritory(territory) != null)
		//		{
		//			userInterface.generateWarning("Not a valid territory");
		//		}
		//		else
		//		{
		//			valid = true;
		//		}
		//	}

		//	players.add(new Player(name, territory));
		//}
		players.add(new Player("George", "Germany"));
		players.add(new Player("Richard", "China"));
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
}
