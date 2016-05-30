package main;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import buttons.TerritoryButton;

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
		TerritoryButton.initGUIManager((GUIManager) userInterface);
		timeline = new Timeline();

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
			if(p.equals(players.get(0)))
			{
				timeline.incrementTurns();
			}
			userInterface.promptPlayerTurn(p);

			//Perform the player actions
			useCards(p);
			deployReinforcements(p);
			attackTerritory(p);
			fortifyTroops(p);

		}
		userInterface.createAnnouncement("Congratulations, " + players.get(0).getName() + ", you won!");
		if(((GUIManager) userInterface).promptTimeline())
		{
			((GUIManager) userInterface).displayTimeline(timeline);
		}
		
	}
	////////////////////////////////////////////////////////////
	// Player Turn Methods
	////////////////////////////////////////////////////////////

	private void useCards(Player p)
	{
		userInterface.clearWarnings();
		if(!p.hasCards())
			return;

		if(p.hasCardsToUse())
		{
			userInterface.createAnnouncement("Using Cards");
			if(userInterface.promptUseCard())
			{
				userInterface.updateCards(p, true);
				Card c = null;
				boolean valid = false;
				while(!valid)
				{
					c = userInterface.selectCard(p);
					if(c == null)
					{
						userInterface.updateCards(p, false);
						break;
					}
					if(!p.ownsTerritory(c.getTerritory()))
					{
						userInterface.generateWarning("You do not own this territory, select another card");
						// generate warning gets overriden immediately by selectCard
						userInterface.updateCards(p, true);
					}
					else if(p.ownsTerritory(c.getTerritory()))
					{
						p.getCards().remove(c);
						userInterface.updateCards(p, false);
						TerritoryMap.get(c.getTerritory()).incrementArmiesBy(c.getValue());
						// p.deployReinforcements(c.getTerritory(),
						// c.getValue());
						valid = true;
					}

				}

			}
		}

		if(p.hasCardsToTrade())
		{
			test: if(userInterface.promptTradeCard())
			{
				userInterface.updateCards(p, true);
				Card c1 = null;
				Card c2 = null;
				Card c3 = null;
				boolean isDone = false;

				while(!isDone)
				{
					userInterface.createAnnouncement(
					    "Select 3 cards to trade. The three cards must be the same value" + " or be all unique");
					c1 = userInterface.selectCard(p);
					c2 = userInterface.selectCard(p);
					c3 = userInterface.selectCard(p);
					if(c1 == null || c2 == null || c3 == null)
					{
						userInterface.updateCards(p, false);
						break test;
					}
					if(c1.getTerritory().equals(c2.getTerritory()) || c1.getTerritory().equals(c3.getTerritory())
					        || c2.getTerritory().equals(c3.getTerritory()))
					{
						userInterface.generateWarning("Please select 3 different cards");
						userInterface.updateCards(p, true);
						continue;

					}

					if((c1.getValue() == c2.getValue() && c2.getValue() == c3.getValue())
					        || (c1.getValue() != c2.getValue() && c2.getValue() != c3.getValue()
					            && c1.getValue() != c3.getValue()))
					{
						isDone = true;
						userInterface.updateCards(p, false);
					}
					else
					{
						userInterface.generateWarning("Must be 3 Cards of the same value or unique");
						userInterface.updateCards(p, true);
					}

					if(c1.getTerritory().equals(c2.getTerritory()) || c1.getTerritory().equals(c3.getTerritory())
					        || c2.getTerritory().equals(c3.getTerritory()))
					{
						userInterface.generateWarning("Please select 3 different cards");
						userInterface.updateCards(p, true);

					}

				}

				p.getCards().remove(c1);
				p.getCards().remove(c2);
				p.getCards().remove(c3);
				deck.getDeck().add(c1);
				deck.getDeck().add(c2);
				deck.getDeck().add(c3);
				userInterface.updateCards(p, false);

				p.addAvailableReinforcements((p.getSetsTraded() + 1) * 2);
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
		userInterface.clearWarnings();
		userInterface.createAnnouncement("Select a territory to deploy reinforcements to");
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
			userInterface.clearWarnings();

			int numArmies = userInterface.getNumArmiesToDeploy(p, territory);
			// Check if numArmies is valid
			if(numArmies < 0 || numArmies > p.getNumReinforcementsAvailable())
			{
				userInterface.generateWarning("You do not have such number of armies, try again");
			}
			else if(numArmies == 0)
			{
				userInterface.createAnnouncement("Cancelling deploy to " + territory);
			}
			else
			{
				userInterface.clearWarnings();
				p.deployReinforcements(territory, numArmies);
			}
		}
	}

	private void attackTerritory(Player p)
	{
		userInterface.clearWarnings();
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
			userInterface.clearWarnings();
			userInterface.createAnnouncement("Choose a territory to attack");
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
			userInterface.clearWarnings();
			userInterface.createAnnouncement("Choose territory to attack from");
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
			userInterface.clearWarnings();
			while(true)
			{
				numArmies = userInterface.getNumArmiesToAttackWith(p, territoryToAttackID, territoryToAttackFromID);
				if(numArmies < 0 || numArmies > territoryToAttackFrom.getNumArmies() - 1)
				{
					userInterface.generateWarning("Bad number of armies");
					continue;
				}
				else if(numArmies == 0)
				{
					userInterface.generateWarning("Cancelling attack");
					break;
				}

				break;
			}

			if(numArmies == 0)
				continue;

			// Do the actual battle
			BattleResults results = p.attackTerritory(territoryToAttackFromID, territoryToAttackID, numArmies);
			userInterface.displayBattleResults(results);
			if(results.getAttackSuccess())
			{
				timeline.addVictoryToTimeline(territoryToAttack.getID(), p);
				if(p.getCards().size() < 5)
				{
					Card c = deck.deal();
					p.addCards(c);
					userInterface.updateCards(p, false);
				}
				else
				{
					userInterface.generateWarning("Card deck has 5 cards, can't deal more");
				}
			}
			else
			{
				timeline.addDefenseVictory(territoryToAttack.getID(), p);
			}

			((GUIManager) userInterface).updateButtonColors();
		}
	}

	private void fortifyTroops(Player p)
	{
		userInterface.clearWarnings();
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

		userInterface.clearWarnings();
		userInterface.createAnnouncement("Choose territory to fortify");
		if(!userInterface.getWantsToFortify(p))
			return;

		userInterface.clearWarnings();
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
			userInterface.createAnnouncement("Choose territory to fortify from");
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
		Color[] color = {Color.BLUE, Color.ORANGE, Color.CYAN, Color.PINK, Color.RED};

		for(int i = 0; i < numPlayers; i++)
		{
			userInterface.clearWarnings();
			userInterface.createAnnouncement("Enter player " + (i + 1) + "'s name");
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

			userInterface.clearWarnings();
			userInterface.createAnnouncement("Select a starting territory for " + name);
			String territory = "";
			valid = false;
			while(!valid)
			{
				territory = userInterface.getStartingTerritory(name);
				if(TerritoryMap.get(territory) == null || TerritoryMap.getOccupierOnTerritory(territory) != null)
				{
					userInterface.generateWarning("Not a valid territory");
				}
				else
				{
					valid = true;
				}
			}

			players.add(new Player(name, territory, color[i]));
		}
		((GUIManager) userInterface).updateButtonColors();
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
		int decrement = 0;
		for(int i = 0; i < players.size(); i++)
		{
			if(players.get(i).hasNoTerritories())
			{
				if(i<currentPlayerTurn)
				{
					decrement++;
				}
				timeline.addPlayerConquered(players.get(i));
				players.remove(i);
				i--;
			}
		}
		currentPlayerTurn -= decrement;
		if(currentPlayerTurn >= players.size())
			currentPlayerTurn = 0;

		//Should correctly update current player turn
		
		Player p = players.get(currentPlayerTurn);
		
		currentPlayerTurn++;

		return p;
	}
}
