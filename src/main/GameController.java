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
import userInterface.CLIManager;
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
		// players.add(new Player("Richard","China"));
		// players.add(new Player("Fluffles","Mongolia"));
	}

	public void play()
	{
		while(isStillPlaying())
		{
			Player p = getNextPlayer();
			userInterface.promptPlayerTurn(p);
			userInterface.generateWarning("This is a test!");
<<<<<<< HEAD
=======

>>>>>>> a59abab2a70359564e89d6441c8a17206b0d73b3
			// // Perform the player actions
			// useCards(p);
			// deployReinforcements(p);
			// attackTerritory(p);
			// fortifyTroops(p);

			// turn ++;
		}
	}
	////////////////////////////////////////////////////////////
	// Player Turn Methods
	////////////////////////////////////////////////////////////

	private void useCards(Player p)
	{
		if(!p.hasCards())
			return;

		if(p.hasCardsToUse())
		{
			test: if(userInterface.promptUseCard())
			{
<<<<<<< HEAD
				Card c = userInterface.selectCard(p);
				if(c == null)
=======
				userInterface.generateWarning("Select a card");

				Card c = null;
				boolean valid = false;
				while(!valid)
				{
					c = userInterface.selectCard(p);
					if(c == null)
						break;
					if(p.ownsTerritory(c.getTerritory()))
					{
						p.getCards().remove(c);
						userInterface.updateCards(p);
						p.deployReinforcements(c.getTerritory(), c.getValue());
						valid = true;
					}
				}
				
				/*if(c == null)
					break test;
				if(!p.ownsTerritory(c.getTerritory()))
>>>>>>> a59abab2a70359564e89d6441c8a17206b0d73b3
					break test;
				p.getCards().remove(c);
				
				p.deployReinforcements(c.getTerritory(), c.getValue());*/

			}
		}
		else if(!p.hasCardsToUse())
		{
			userInterface.generateWarning("You do not have cards to use");
		}

		if(p.hasCardsToTrade())
		{
			if(userInterface.promptTradeCard())
			{
				Card c1 = null;
				Card c2 = null;
				Card c3 = null;
				boolean isDone = false;

				while(!isDone)
				{
<<<<<<< HEAD
					System.out.println("Select 3 cards to trade. The three cards must be the same value");
=======
					userInterface.generateWarning("Select 3 cards to trade. The three cards must be the same value"
							+ " or be all unique");
>>>>>>> a59abab2a70359564e89d6441c8a17206b0d73b3
					c1 = userInterface.selectCard(p);
					c2 = userInterface.selectCard(p);
					c3 = userInterface.selectCard(p);
					if(c1 == null || c2 == null || c3 == null)
						break;
					if((c1.getValue() == c2.getValue() && c2.getValue() == c3.getValue())
							|| (c1.getValue() != c2.getValue() && c2.getValue() != c3.getValue() && c1.getValue() != c3.getValue()))
					{
						isDone = true;
					}
					else
					{
						userInterface.generateWarning("Must be 3 Cards of the same value or unique");
						continue;
					}
				}

				p.getCards().remove(c1);
				p.getCards().remove(c2);
				p.getCards().remove(c3);
				deck.getDeck().add(c1);
				deck.getDeck().add(c2);
				deck.getDeck().add(c3);
				userInterface.updateCards(p);

				p.addReinforcements((p.getSetsTraded() + 1) * 2);
				p.incrementSets();
			}
		}
		else if(!p.hasCardsToTrade())
		{
			userInterface.generateWarning("You do not have cards to trade");
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
				if(TerritoryMap.getOccupierOnTerritory(territoryToAttackFromID) == null
				        || !p.getName().equals(TerritoryMap.getOccupierOnTerritory(territoryToAttackFromID).getName()))
				{
					userInterface.generateWarning("You do not own this territory");
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
				if(numArmies <= 0 || numArmies > territoryToAttackFrom.getNumArmies() - 1)
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
	// Utility Methods
	////////////////////////////////////////////////////////////

	private void initPlayers()
	{
		int numPlayers = userInterface.getNumPlayers();

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
		players.add(new Player("Richard's long name", "China"));
		players.add(new Player("George", "Germany"));
		//Testing card drawings
<<<<<<< HEAD
		players.get(0).addCards(deck.deal());
=======
		players.get(0).addCards(new Card("China", 3));
		players.get(0).addCards(new Card("New Guinea", 2));
		for(int i = 0; i < 5; i++)
		{
			players.get(0).addCards(deck.deal());
		}
		players.get(1).addCards(new Card("Germany", 3));
		for(int i = 0; i < 5; i++)
		{
			players.get(1).addCards(deck.deal());
		}

>>>>>>> a59abab2a70359564e89d6441c8a17206b0d73b3
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

		currentPlayerTurn++;

		return p;
	}
}
