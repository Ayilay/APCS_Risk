package userInterface;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.OverlayLayout;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import buttons.*;

import battle.BattleResults;
import battle.Timeline;
import card.Card;
import main.Player;
import territoryMap.TerritoryMap;

public class GUIManager implements UserInterface
{
	public static Card lastCardSelected;
	public static String lastTerritorySelected;

	private JFrame window;
	private JPanel mainPane;

	private JPanel mapArea;
	private JPanel mapBackground;
	private JPanel mapOverlayArea;
	private JPanel messageArea;
	private JPanel playerNameArea;
	private JPanel playerStatsArea;
	private JPanel gameStateArea;
	private JPanel footerArea;

	private JLabel gameState_territoryLabel;
	private JLabel gameState_numArmies;
	private JLabel gameState_battleResults;
	private JLabel gameState_numAttackLosses;
	private JLabel gameState_numDefendLosses;
	private JButton gameState_cancelButton;
	private JLabel playerNameArea_label;
	private JLabel messageArea_label;
	private JLabel warningArea_label;

	private JPanel deckPane;
	private JPanel initPane;
	private JScrollPane scroll;

	private JButton cards;
	private JButton back;
	private JButton instructions;

	private Map<String, JButton> buttons;

	private CardLayout cardDisplay;

	private boolean quit;
	private boolean cancelOperation;

	public GUIManager()
	{
		window = new JFrame();
		window.setTitle("Risk");
		window.setSize(1230, 745);

		mainPane = new JPanel(new GridBagLayout());
		mapArea = new JPanel(null);
		mapBackground = new JPanel();
		mapOverlayArea = new JPanel();
		playerNameArea = new JPanel(new BorderLayout());
		playerStatsArea = new JPanel();
		gameStateArea = new JPanel(new GridLayout(8, 0, 0, 0));
		messageArea = new JPanel(new GridLayout(2, 0, 0, 0));
		footerArea = new JPanel();

		playerNameArea_label = new JLabel();
		messageArea_label = new JLabel();
		warningArea_label = new JLabel();

		GridBagConstraints constr = new GridBagConstraints();
		Color bgPaneColor = new Color(200, 200, 200);

		deckPane = new JPanel();
		initPane = new JPanel();
		scroll = new JScrollPane(deckPane);

		cards = new JButton("Display cards");
		back = new JButton("Back");
		instructions = new JButton("Instructions");

		cardDisplay = new CardLayout();

		quit = false;

		mainPane.setBackground(Color.BLACK);

		ImageIcon img = null;
		try
		{
			img = new ImageIcon(ImageIO.read(getClass().getResource("/resources/Risk_Final_Map.png")));
		}
		catch(Exception e)
		{
			System.err.println("Failed in reading the map image");
		}

		window.add(mainPane);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //terminates the program on close

		// add the elements
		constr.weightx = 0;
		constr.weighty = 0;

		// Player Name area
		playerNameArea_label = new JLabel();
		constr.gridx = 0;
		constr.gridy = 0;
		constr.fill = GridBagConstraints.VERTICAL;
		constr.gridheight = 2;
		playerNameArea.setBackground(bgPaneColor);
		playerNameArea.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		playerNameArea.setPreferredSize(new Dimension(200, 120));

		playerNameArea_label.setPreferredSize(new Dimension(200, 120));
		playerNameArea_label.setHorizontalAlignment(JLabel.CENTER);
		playerNameArea_label.setVerticalAlignment(JLabel.CENTER);
		playerNameArea.add(playerNameArea_label, BorderLayout.CENTER);

		playerNameArea.setVisible(true);
		mainPane.add(playerNameArea, constr);

		// Message Area
		constr.gridx = 1;
		constr.gridy = 0;
		constr.fill = GridBagConstraints.NONE;
		constr.gridheight = 1;
		messageArea.setBackground(bgPaneColor);
		messageArea.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		messageArea.setPreferredSize(new Dimension(1000, (600 - 512)));
		messageArea_label.setPreferredSize(new Dimension(1000, (600 - 512)));
		messageArea_label.setHorizontalAlignment(JLabel.CENTER);
		messageArea_label.setVerticalAlignment(JLabel.CENTER);
		warningArea_label.setPreferredSize(new Dimension(1000, (600 - 512)));
		warningArea_label.setHorizontalAlignment(JLabel.CENTER);
		warningArea_label.setVerticalAlignment(JLabel.CENTER);
		warningArea_label.setForeground(Color.red);
		messageArea.add(messageArea_label);
		messageArea.add(warningArea_label);
		messageArea.setVisible(true);
		mainPane.add(messageArea, constr);


		// Game State area
		constr.gridx = 0;
		constr.gridy = 2;
		gameStateArea.setBackground(bgPaneColor);
		gameStateArea.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		gameStateArea.setPreferredSize(new Dimension(200, 300));

		gameState_territoryLabel = new JLabel();
		gameState_numArmies = new JLabel();
		gameState_battleResults = new JLabel();
		gameState_numAttackLosses = new JLabel();
		gameState_numDefendLosses = new JLabel();
		gameState_cancelButton = new JButton();
		gameState_cancelButton.setText("Cancel");

		gameState_territoryLabel.setHorizontalAlignment(JLabel.CENTER);
		gameState_numArmies.setHorizontalAlignment(JLabel.CENTER);
		gameState_battleResults.setHorizontalAlignment(JLabel.CENTER);
		gameState_numAttackLosses.setHorizontalAlignment(JLabel.CENTER);
		gameState_numDefendLosses.setHorizontalAlignment(JLabel.CENTER);
		gameState_cancelButton.setHorizontalAlignment(JLabel.CENTER);
		gameStateArea.add(gameState_territoryLabel);
		gameStateArea.add(gameState_numArmies);
		gameStateArea.add(new JLabel()); // For spacing
		gameStateArea.add(gameState_battleResults);
		gameStateArea.add(gameState_numAttackLosses);
		gameStateArea.add(gameState_numDefendLosses);
		gameStateArea.add(new JLabel()); // For spacing
		gameStateArea.add(gameState_cancelButton);
		gameState_cancelButton.setVisible(false);
		gameStateArea.setVisible(true);
		mainPane.add(gameStateArea, constr);

		// Player Statistics Area
		constr.gridx = 0;
		constr.gridy = 3;
		constr.gridheight = 2;
		constr.fill = GridBagConstraints.VERTICAL;
		playerStatsArea.setLayout(cardDisplay);
		playerStatsArea.setBackground(bgPaneColor);
		playerStatsArea.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		playerStatsArea.setPreferredSize(new Dimension(200, 100));

		initPane.add(cards);
		initPane.add(instructions);
		initPane.setBackground(bgPaneColor);
		deckPane.add(back);
		deckPane.setBackground(Color.DARK_GRAY);
		back.setAlignmentX(Component.CENTER_ALIGNMENT);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		scroll.setBackground(Color.MAGENTA);
		playerStatsArea.add(initPane, "1");
		playerStatsArea.add(scroll, "2");

		cardDisplay.show(playerStatsArea, "1");

		instructions.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				displayInstructions();
			}

		});
		cards.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				cardDisplay.show(playerStatsArea, "2");
				clearBattleStats();
			}
		});

		back.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				cardDisplay.show(playerStatsArea, "1");
				clearBattleStats();
			}
		});

		gameState_cancelButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				cancelOperation = true;
				clearBattleStats();
			}
		});

		playerStatsArea.setVisible(true);
		mainPane.add(playerStatsArea, constr);

		// Map Area
		initButtons();

		constr.gridx = 1;
		constr.gridy = 1;
		constr.fill = GridBagConstraints.BOTH;
		constr.gridheight = 3;

		mapBackground.add(new JLabel(img));
		mapBackground.setBackground(Color.WHITE);
		mapBackground.setPreferredSize(new Dimension(1000, 512));
		mapBackground.setVisible(true);

		mapArea.setPreferredSize(new Dimension(1000, 512));
		mapArea.setOpaque(false);

		LayoutManager overlay = new OverlayLayout(mapOverlayArea);
		mapOverlayArea.setLayout(overlay);

		mapOverlayArea.add(mapArea);
		mapOverlayArea.add(mapBackground);
		mainPane.add(mapOverlayArea, constr);

		// Footer
		constr.gridx = 1;
		constr.gridy = 4;
		constr.gridheight = 1;
		footerArea.setBackground(bgPaneColor);
		footerArea.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		footerArea.setPreferredSize(new Dimension(100, 10));
		footerArea.setVisible(true);
		mainPane.add(footerArea, constr);

		window.pack();
		window.setVisible(true);

	}

	////////////////////////////////////////////////////////////
	//	Init Methods
	////////////////////////////////////////////////////////////
	/*
	 * getSlider code modified off: http://www.java2s.com/Tutorial/Java/0240__Swing/UsingJOptionPanewithaJSlider.htm
	 * Textfield code modified off http://docs.oracle.com/javase/tutorial/uiswing/components/dialog.html#input
	 */
	@Override
	public int getNumPlayers()
	{
		JFrame parent = new JFrame();

		JOptionPane optionPane = new JOptionPane();
		JSlider slider = getSlider(optionPane, 2, 5);
		optionPane.setMessage(new Object[]
				{ "How many players will be playing?", slider });
		optionPane.setMessageType(JOptionPane.QUESTION_MESSAGE);
		optionPane.setOptionType(JOptionPane.DEFAULT_OPTION);
		JDialog dialog = optionPane.createDialog(parent, "Risk Set-Up");
		dialog.setVisible(true);
		return (Integer) slider.getValue();
	}

	@Override
	public String getPlayerName()
	{
		JFrame parent = new JFrame();
		JOptionPane optionPane = new JOptionPane();
		optionPane.setMessage(JOptionPane.QUESTION_MESSAGE);
		optionPane.setOptionType(JOptionPane.DEFAULT_OPTION);
		String s = (String) optionPane.showInputDialog(parent, "Enter player name", "Player");
		return s;
	}

	@Override
	public String getStartingTerritory(String playerName)
	{
		lastTerritorySelected = null;
		while(lastTerritorySelected == null)
		{
			try
			{
				Thread.sleep(200);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return lastTerritorySelected;
	}

	////////////////////////////////////////////////////////////
	//	Pre Player Turn Methods
	////////////////////////////////////////////////////////////

	@Override
	public void promptPlayerTurn(Player p)
	{
		updateCards(p, false);

		String text = p.getName() + "'s Turn";
		playerNameArea.setBackground(p.getColor());
		playerNameArea_label.setForeground(Color.BLACK);
		int fontSize = getFontSize(playerNameArea_label, text);
		playerNameArea_label.setFont(new Font("Times New Roman", Font.PLAIN, fontSize));
		playerNameArea_label.setText(text);
	}

	////////////////////////////////////////////////////////////
	//	Use Card Methods
	////////////////////////////////////////////////////////////

	public void updateCards(Player p, boolean turn)
	{
		deckPane.setVisible(false);

		deckPane.removeAll();
		deckPane.add(back);
		deckPane.setBackground(Color.DARK_GRAY);
		for(Card c : p.getCards())
		{
			JPanel panel = c.drawCard(turn);
			deckPane.add(panel);
			//deck.getComponents();
		}
		deckPane.setVisible(true);
	}

	@Override
	public Card selectCard(Player p)
	{
		cardDisplay.show(playerStatsArea, "2");

		if(!p.hasCards())
		{
			generateWarning("You do not have any cards");
			return null;
		}

		boolean selected = false;
		quit = false;
		lastCardSelected = null;

		while(!selected)
		{
			back.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					quit = true;
				}
			});

			if(quit == true)
				return null;

			if(lastCardSelected != null)
			{
				createAnnouncement("You selected card with: " + lastCardSelected.toString());
				selected = true;
			}
		}
		return lastCardSelected;

	}
	@Override
	public boolean promptUseCard()
	{
		JOptionPane optionPane = new JOptionPane();
		optionPane.setOptionType(JOptionPane.DEFAULT_OPTION);
		if(optionPane.showConfirmDialog(null, "Do you want to use a card?", "Card", JOptionPane.YES_NO_OPTION)
				== JOptionPane.YES_OPTION)
		{
			return true;
		}
		return false;
	}

	@Override
	public boolean promptTradeCard()
	{
		JOptionPane optionPane = new JOptionPane();
		optionPane.setOptionType(JOptionPane.DEFAULT_OPTION);
		if(optionPane.showConfirmDialog(null, "Do you want to trade cards?", "Trading", JOptionPane.YES_NO_OPTION)
				== JOptionPane.YES_OPTION)
		{
			return true;
		}
		return false;
	}

	////////////////////////////////////////////////////////////
	//	Deploy Armies methods
	////////////////////////////////////////////////////////////

	@Override
	public String getDeployTerritory(Player p)
	{
		lastTerritorySelected = null;
		while(lastTerritorySelected == null)
		{
			try
			{
				Thread.sleep(200);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return lastTerritorySelected;
	}

	@Override
	public int getNumArmiesToDeploy(Player p, String deployTerritory)
	{
		JFrame parent = new JFrame();
		JOptionPane optionPane = new JOptionPane();
		JSlider slider = getSlider(optionPane, 0, p.getNumReinforcementsAvailable());
		optionPane.setMessage(new Object[]
				{ "Choose number of armies to deploy to " + deployTerritory, slider });
		optionPane.setMessageType(JOptionPane.QUESTION_MESSAGE);
		optionPane.setOptionType(JOptionPane.DEFAULT_OPTION);
		JDialog dialog = optionPane.createDialog(parent, "Deploy");
		dialog.setVisible(true);
		return (Integer) slider.getValue();
	}

	////////////////////////////////////////////////////////////
	//	Attack Territories methods
	////////////////////////////////////////////////////////////

	@Override
	public boolean getFinishedAttacking()
	{
		//JOptionPane optionPane = new JOptionPane();
		//optionPane.setOptionType(JOptionPane.DEFAULT_OPTION);
		//if(optionPane.showConfirmDialog(null, "Do you want to attack another territory?", "Attack!", JOptionPane.YES_NO_OPTION)
		//        == JOptionPane.NO_OPTION)
		//{
		//	return true;
		//}
		//else
		//{
		//	return false;
		//}
		return false;
	}

	@Override
	public String getTerritoryToAttack(Player p)
	{
		lastTerritorySelected = null;
		cancelOperation = false;
		gameState_cancelButton.setText("Cancel Attack");
		gameState_cancelButton.setVisible(true);
		while(lastTerritorySelected == null && cancelOperation == false)
		{
			try
			{
				Thread.sleep(200);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		gameState_cancelButton.setVisible(false);

		if(cancelOperation == true)
			return null;
		else
			return lastTerritorySelected;
	}

	@Override
	public String getTerritoryToAttackFrom(Player p, String territoryToAttack)
	{
		lastTerritorySelected = null;
		cancelOperation = false;
		gameState_cancelButton.setText("Cancel Attack");
		gameState_cancelButton.setVisible(true);
		while(lastTerritorySelected == null && cancelOperation == false)
		{
			try
			{
				Thread.sleep(200);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		gameState_cancelButton.setVisible(false);

		if(cancelOperation)
			return null;
		return lastTerritorySelected;
	}

	@Override
	public int getNumArmiesToAttackWith(Player p, String territoryToAttackID, String territoryToAttackFromID)
	{
		JFrame parent = new JFrame();
		JOptionPane optionPane = new JOptionPane();
		JSlider slider = getSlider(optionPane, 0, TerritoryMap.get(territoryToAttackFromID).getNumArmies() - 1);
		optionPane.setMessage(new Object[]
				{ "Choose number of armies to attack " + territoryToAttackID + " with.", slider });
		optionPane.setMessageType(JOptionPane.QUESTION_MESSAGE);
		optionPane.setOptionType(JOptionPane.DEFAULT_OPTION);
		JDialog dialog = optionPane.createDialog(parent, "Attack!");
		dialog.setVisible(true);
		return (Integer) slider.getValue();
	}

	@Override
	public void displayBattleResults(BattleResults results)
	{
		gameState_battleResults.setText("Attack was " + (results.getAttackSuccess() ? "a victory!" : "unsuccessful"));
		gameState_numAttackLosses.setText("Attacker lost " + results.getNumAttackerLosses() + " armies");
		gameState_numDefendLosses.setText("Defender lost " + results.getNumDefenderLosses() + " armies");
		//gameState_numArmies.setText("");
		//JLabel label;
		//if(results.getAttackSuccess())
		//{
		//	label = new JLabel("Attack was a victory! Attacker lost " + results.getNumAttackerLosses()
		//	                   + " armies and defender lost " + results.getNumDefenderLosses() + " armies");
		//}
		//else
		//{
		//	label = new JLabel("Attack was a unsuccessful. Attacker lost " + results.getNumAttackerLosses()
		//	                   + " armies and defender lost " + results.getNumDefenderLosses() + " armies");
		//}
		//JPanel panel = new JPanel();
		//panel.add(label);
		//JOptionPane.showConfirmDialog(null, panel, "Results", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
	}

	////////////////////////////////////////////////////////////
	//	Fortify Troops methods
	////////////////////////////////////////////////////////////

	@Override
	public boolean getWantsToFortify(Player p)
	{
		return true;
		//JOptionPane optionPane = new JOptionPane();
		//optionPane.setOptionType(JOptionPane.DEFAULT_OPTION);
		//if(optionPane.showConfirmDialog(null, "Do you want to fortify territories?", "Fortify", JOptionPane.YES_NO_OPTION)
		//        == JOptionPane.YES_OPTION)
		//{
		//	return true;
		//}
		//return false;
	}

	@Override
	public String getTerritoryToFortify(Player p)
	{
		lastTerritorySelected = null;
		cancelOperation = false;
		gameState_cancelButton.setText("Cancel Fortify");
		gameState_cancelButton.setVisible(true);
		while(lastTerritorySelected == null && cancelOperation == false)
		{
			try
			{
				Thread.sleep(200);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		gameState_cancelButton.setVisible(false);

		if(cancelOperation)
			return null;
		return lastTerritorySelected;
	}

	@Override
	public String getTerritoryToFortifyFrom(Player p, String terrID)
	{
		lastTerritorySelected = null;
		cancelOperation = false;
		gameState_cancelButton.setText("Cancel Fortify");
		gameState_cancelButton.setVisible(true);
		while(lastTerritorySelected == null && cancelOperation == false)
		{
			try
			{
				Thread.sleep(200);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		gameState_cancelButton.setVisible(false);

		if(cancelOperation)
			return null;
		return lastTerritorySelected;
	}

	@Override
	public int getNumArmiesToFortify(String terrToFortFrom)
	{
		int maxArmies = TerritoryMap.getNumArmiesDeployedOn(terrToFortFrom) - 1;

		JFrame parent = new JFrame();
		JOptionPane optionPane = new JOptionPane();
		JSlider slider = getSlider(optionPane, 0, maxArmies);
		optionPane.setMessage(new Object[]
				{ "Choose number of armies to fortify from " + terrToFortFrom, slider });
		optionPane.setMessageType(JOptionPane.QUESTION_MESSAGE);
		optionPane.setOptionType(JOptionPane.DEFAULT_OPTION);
		JDialog dialog = optionPane.createDialog(parent, "Fortify");
		dialog.setVisible(true);
		return (Integer) slider.getValue();

	}

	////////////////////////////////////////////////////////////
	//	Utility Methods
	////////////////////////////////////////////////////////////

	@Override
	public void createAnnouncement(String string)
	{
		messageArea_label.setText("");
		messageArea_label.setForeground(Color.BLACK);
		messageArea_label.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		messageArea_label.setText(string);
	}

	@Override
	public void generateWarning(String string)
	{
		warningArea_label.setText("");
		try
		{
			// Makes a flashing effect
			Thread.sleep(80);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		warningArea_label.setForeground(Color.RED);
		warningArea_label.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		warningArea_label.setText(string);
	}

	@Override
	public void clearWarnings()
	{
		warningArea_label.setText("");
	}

	private int getFontSize(JLabel label, String text)
	{
		int fontSizeToUse = (int)(300.0 / text.length());
		return fontSizeToUse;
	}

	private void initButtons()
	{
		buttons = new HashMap<String, JButton> ();

		////////////////////////////////////////////////////////////
		//	Init Buttons
		////////////////////////////////////////////////////////////

		//North America
		JButton greenland = new GreenlandButton("Greenland");
		JButton quebec = new QuebecButton("Quebec");
		JButton ontario = new OntarioButton("Ontario");
		JButton alberta = new AlbertaButton("Alberta");
		JButton alaska = new AlaskaButton("Alaska");
		JButton northWestTerritory = new NorthwestTerritoryButton("Northwest Territory");
		JButton westernUnitedStates = new WesternUnitedStatesButton("Western United States");
		JButton easternUnitedStates = new EasternUnitedStatesButton("Eastern United States");
		JButton mexico = new MexicoButton("Mexico");
		JButton centralAmerica = new CentralAmericaButton("Central America");
		JButton cuba = new CubaButton("Cuba");
		JButton hawaii = new HawaiiButton("Hawaii");

		//South America
		JButton venezuela = new VenezuelaButton("Venezuela");
		JButton peru = new PeruButton("Peru");
		JButton argentina = new ArgentinaButton("Argentina");
		JButton bolivia = new BoliviaButton("Bolivia");
		JButton brazil = new BrazilButton("Brazil");

		//Asia
		JButton china = new ChinaButton("China");
		JButton kamchatka = new KamchatkaButton("Kamchatka");
		JButton yakutsk = new YakutskButton("Yakutsk");
		JButton irkutsk = new IrkutskButton("Irkutsk");
		JButton mongolia = new MongoliaButton("Mongolia");
		JButton siberia = new SiberiaButton("Siberia");
		JButton ural = new UralButton("Ural");
		JButton kazakhstan = new KazakhstanButton("Kazakhstan");
		JButton afganistan = new AfganistanButton("Afganistan");
		JButton pakistan = new PakistanButton("Pakistan");
		JButton india = new IndiaButton("India");
		JButton indochina = new IndochinaButton("Indochina");
		JButton iran = new IranButton("Iran");
		JButton turkey = new TurkeyButton("Turkey");
		JButton saudiArabia = new SaudiArabiaButton("Saudi Arabia");
		JButton japan = new JapanButton("Japan");

		//Europe
		JButton unitedKingdom = new UnitedKingdomButton("United Kingdom");
		JButton iceland = new IcelandButton("Iceland");
		JButton scandinavia = new ScandinaviaButton("Scandinavia");
		JButton sweden = new SwedenButton("Sweden");
		JButton eastEurope = new EasternEuropeButton("Eastern Europe");
		JButton lowCountries = new LowCountriesButton("Low Countries");
		JButton spain = new SpainButton("Spain");
		JButton france = new FranceButton("France");
		JButton denmark = new DenmarkButton("Denmark");
		JButton germany = new GermanyButton("Germany");
		JButton poland = new PolandButton("Poland");
		JButton czechoslovakia = new CzechoslovakiaButton("Czechoslovakia");
		JButton southernEurope = new SouthernEuropeButton("Southern Europe");

		//Africa
		JButton morocco = new MoroccoButton("Morocco");
		JButton algeria = new AlgeriaButton("Algeria");
		JButton egypt = new EgyptButton("Egypt");
		JButton eastAfrica = new EastAfricaButton("East Africa");
		JButton westAfrica = new WestAfricaButton("West Africa");
		JButton madagascar = new MadagascarButton("Madagascar");
		JButton southAfrica = new SouthAfricaButton("South Africa");
		JButton congo = new CongoButton("Congo");

		//Australia
		JButton eastAustralia = new EasternAustraliaButton("Eastern Australia");
		JButton westAustralia = new WesternAustraliaButton("Western Australia");
		JButton newGuinea = new NewGuineaButton("New Guinea");
		JButton indonesia = new IndonesiaButton("Indonesia");
		JButton philippines = new PhilippinesButton("Philippines");

		////////////////////////////////////////////////////////////
		//	Add buttons to HashMap
		////////////////////////////////////////////////////////////

		//North America
		buttons.put("Greenland", greenland);
		buttons.put("Quebec", quebec);
		buttons.put("Ontario", ontario);
		buttons.put("Alberta", alberta);
		buttons.put("Alaska", alaska);
		buttons.put("Northwest Territory", northWestTerritory);
		buttons.put("Western United States", westernUnitedStates);
		buttons.put("Eastern United States", easternUnitedStates);
		buttons.put("Mexico", mexico);
		buttons.put("Central America", centralAmerica);
		buttons.put("Cuba", cuba);
		buttons.put("Hawaii", hawaii);

		//South America
		buttons.put("Venezuela", venezuela);
		buttons.put("Peru", peru);
		buttons.put("Argentina", argentina);
		buttons.put("Bolivia", bolivia);
		buttons.put("Brazil", brazil);

		//Asia
		buttons.put("China", china);
		buttons.put("Kamchatka", kamchatka);
		buttons.put("Yakutsk", yakutsk);
		buttons.put("Irkutsk", irkutsk);
		buttons.put("Mongolia", mongolia);
		buttons.put("Siberia", siberia);
		buttons.put("Ural", ural);
		buttons.put("Kazakhstan", kazakhstan);
		buttons.put("Afganistan", afganistan);
		buttons.put("Pakistan", pakistan);
		buttons.put("India", india);
		buttons.put("Indochina", indochina);
		buttons.put("Iran", iran);
		buttons.put("Turkey", turkey);
		buttons.put("Saudi Arabia", saudiArabia);
		buttons.put("Japan", japan);

		//Europe
		buttons.put("United Kingdom", unitedKingdom);
		buttons.put("Iceland", iceland);
		buttons.put("Scandinavia", scandinavia);
		buttons.put("Sweden", sweden);
		buttons.put("Eastern Europe", eastEurope);
		buttons.put("Low Countries", lowCountries);
		buttons.put("Spain", spain);
		buttons.put("France", france);
		buttons.put("Denmark", denmark);
		buttons.put("Germany", germany);
		buttons.put("Poland", poland);
		buttons.put("Czechoslovakia", czechoslovakia);
		buttons.put("Southern Europe", southernEurope);

		//Africa
		buttons.put("Morocco", morocco);
		buttons.put("Algeria", algeria);
		buttons.put("Egypt", egypt);
		buttons.put("East Africa", eastAfrica);
		buttons.put("West Africa", westAfrica);
		buttons.put("Madagascar", madagascar);
		buttons.put("South Africa", southAfrica);
		buttons.put("Congo", congo);

		//Australia
		buttons.put("Eastern Australia", eastAustralia);
		buttons.put("Western Australia", westAustralia);
		buttons.put("New Guinea", newGuinea);
		buttons.put("Indonesia", indonesia);
		buttons.put("Philippines", philippines);

		////////////////////////////////////////////////////////////
		//	Add to GUI map area
		////////////////////////////////////////////////////////////

		//North America
		mapArea.add(greenland);
		mapArea.add(quebec);
		mapArea.add(ontario);
		mapArea.add(alberta);
		mapArea.add(alaska);
		mapArea.add(northWestTerritory);
		mapArea.add(westernUnitedStates);
		mapArea.add(easternUnitedStates);
		mapArea.add(mexico);
		mapArea.add(centralAmerica);
		mapArea.add(cuba);
		mapArea.add(hawaii);

		//South America
		mapArea.add(venezuela);
		mapArea.add(peru);
		mapArea.add(argentina);
		mapArea.add(bolivia);
		mapArea.add(brazil);

		//Asia
		mapArea.add(china);
		mapArea.add(kamchatka);
		mapArea.add(yakutsk);
		mapArea.add(irkutsk);
		mapArea.add(mongolia);
		mapArea.add(siberia);
		mapArea.add(ural);
		mapArea.add(kazakhstan);
		mapArea.add(afganistan);
		mapArea.add(pakistan);
		mapArea.add(india);
		mapArea.add(indochina);
		mapArea.add(iran);
		mapArea.add(turkey);
		mapArea.add(saudiArabia);
		mapArea.add(japan);

		//Europe
		mapArea.add(unitedKingdom);
		mapArea.add(iceland);
		mapArea.add(scandinavia);
		mapArea.add(sweden);
		mapArea.add(eastEurope);
		mapArea.add(lowCountries);
		mapArea.add(spain);
		mapArea.add(france);
		mapArea.add(denmark);
		mapArea.add(germany);
		mapArea.add(poland);
		mapArea.add(czechoslovakia);
		mapArea.add(southernEurope);

		//Africa
		mapArea.add(morocco);
		mapArea.add(algeria);
		mapArea.add(egypt);
		mapArea.add(eastAfrica);
		mapArea.add(westAfrica);
		mapArea.add(madagascar);
		mapArea.add(southAfrica);
		mapArea.add(congo);

		//Australia
		mapArea.add(eastAustralia);
		mapArea.add(westAustralia);
		mapArea.add(newGuinea);
		mapArea.add(indonesia);
		mapArea.add(philippines);

		//Add hover listener to update side panel
		for(String s : buttons.keySet())
		{

		}
	}
	/*
	 * See above for credits
	 */
	private static JSlider getSlider(final JOptionPane dialog, int start, int end)
	{
		JSlider slider = new JSlider(start, end);
		slider.setMajorTickSpacing(1);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		ChangeListener changeListener = new ChangeListener()
		{
			public void stateChanged(ChangeEvent changeEvent)
			{
				JSlider theSlider = (JSlider) changeEvent.getSource();
				if(!theSlider.getValueIsAdjusting())
				{
					dialog.setInputValue(new Integer(theSlider.getValue()));
				}
			}
		};
		return slider;
	}

	public void updateButtonColors()
	{
		for(String buttonID : buttons.keySet())
		{
			if(buttons.get(buttonID) instanceof TerritoryButton)
				((TerritoryButton) buttons.get(buttonID)).updateColor();
			else
			{
				System.out.println(buttonID);
				System.out.println("weird class: " + buttons.get(buttonID).getClass());
			}
		}
	}

	public void clearBattleStats()
	{
		gameState_battleResults.setText("");
		gameState_numAttackLosses.setText("");
		gameState_numDefendLosses.setText("");
	}

	public void displayTerritoryStats(String terr)
	{
		if(terr != null)
		{
			gameState_territoryLabel.setText(terr);
			gameState_numArmies.setText("has " + TerritoryMap.getNumArmiesDeployedOn(terr) + " armies");
		}
		else
		{
			gameState_territoryLabel.setText("Hover over a territory");
			gameState_numArmies.setText("to see stats");
		}
	}

	public void displayTimeline(Timeline t)
	{
		JTextArea text = new JTextArea(t.toString());
		JScrollPane scrollPane = new JScrollPane(text);
		text.setLineWrap(true);
		text.setWrapStyleWord(true);
		scrollPane.setPreferredSize(new Dimension(500, 500));
		JOptionPane.showMessageDialog(null, scrollPane, "Time line",
				JOptionPane.DEFAULT_OPTION);
	}

	public boolean promptTimeline()
	{
		JOptionPane optionPane = new JOptionPane();
		optionPane.setOptionType(JOptionPane.DEFAULT_OPTION);
		if(optionPane.showConfirmDialog(null, "Do you want to see the timeline?", "Timeline", JOptionPane.YES_NO_OPTION)
				== JOptionPane.YES_OPTION)
		{
			return true;
		}
		return false;
	}
	
	public void displayInstructions()
	{
		JFrame parent = new JFrame();

		JTextArea textArea = new JTextArea(100, 100);
		textArea.setText("						Welcome To Risk !\n\n"
				+ "	Risk is a turn based game where the players attempt to conquer the world with their armies. "
				+ "Each turn players will be asked to use/trade cards if possible, deploy reinforcements to a \n"
				+ "player's territories, attack opposing territories, and fortify a player's territories.\n\n"
				+ "CARDS:\n	Cards contain two values, a territory and a value from 1-3. Each player can hold up to a maximum of 5 cards. Cards are given everytime a player conquers a territory"
				+ " but they are only given once\n"
				+ " per turn. Players can use a card if they hold the territory displayed on the card. Using a card gives that territory extra armies based on how the value of the card. Players can also trade\n"
				+ " 3 cards in for extra reinforcements that turn"
				+ "If the player owns 3 cards of all different values (ex. 1, 2, 3) or 3 cards of the same value (ex. 1,1,1 or 2,2,2 or 3,3,3), the player can trade in the cards\n"
				+ "The more set of cards traded in, the more reinforcements a player gets from tradings. The number of reinforcements is given by 2(n+1) where n is the number of sets traded.\n\n "
				+ "REINFORCEMENTS:\n	Every turn, a player gets 3 reinforcements to deploy to their territories. Player MUST deploy all 3 of their reinforcements before continuing the turn. "
				+ "After controlling more than 9 territories,\n players get more reinforcements per turn. \n\n"
				+ "ATTACKING:\n	After deploying reinforcements, players can attack territories ADJACENT to the ones they already own. Players use armies from their territories to attack other territories."
				+ "Neutral territories automatically contain 2 armies in them. Defending territories generally have the advantage during battles, so the attacker should generally send more armies than the defending"
				+ "territory has.\n"
				+ "There is no definite win. All battles can result in defeats for either side no matter how many armies are sent or defending, but sending more armies during attack increases a player's success rate. \n\n"
				+ "FORTIFY:\n	After attacking, players can fortify their territories by moving troops and repositioning troops from their territories. Players can only transfer armies to adjacent territories but can do multiple per turn"
				
				);
	    textArea.setEditable(false);
	    
		
	    // wrap a scrollpane around it
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		// display them in a message dialog
		JOptionPane.showMessageDialog(parent, scrollPane, "Instructions", 1);
		//JOptionPane.showMessageDialog(parent, scrollPane);				
	}
}

