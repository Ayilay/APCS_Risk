package userInterface;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.OverlayLayout;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import battle.BattleResults;
import buttons.*;
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

	private JLabel playerNameArea_label;
	private JLabel messageArea_label;

	private JPanel deckPane;
	private JPanel initPane;
	private JScrollPane scroll;

	private JButton cards;
	private JButton back;

	private Map<String, JButton> buttons;

	private CardLayout cardDisplay;

	private boolean quit;

	public GUIManager()
	{
		window = new JFrame();
		window.setTitle("Risk");
		window.setSize(1230, 745);

		mainPane = new JPanel(new GridBagLayout());
		mapArea = new JPanel(null);
		mapBackground = new JPanel();
		mapOverlayArea = new JPanel();
		messageArea = new JPanel(new BorderLayout());
		playerNameArea = new JPanel(new BorderLayout());
		playerStatsArea = new JPanel();
		gameStateArea = new JPanel();
		footerArea = new JPanel();

		playerNameArea_label = new JLabel();
		messageArea_label = new JLabel();

		GridBagConstraints constr = new GridBagConstraints();

		deckPane = new JPanel();
		initPane = new JPanel();
		scroll = new JScrollPane(deckPane);

		cards = new JButton("Display cards");
		back = new JButton("Back");

		cardDisplay = new CardLayout();

		quit = false;

		//mainPane.setPreferredSize(new Dimension(1200, 700));
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
		playerNameArea.setBackground(Color.RED);
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
		messageArea.setBackground(Color.ORANGE);
		messageArea.setPreferredSize(new Dimension(1000, (600 - 512)));
		messageArea_label.setPreferredSize(new Dimension(1000, (600 - 512)));
		messageArea_label.setHorizontalAlignment(JLabel.CENTER);
		messageArea_label.setVerticalAlignment(JLabel.CENTER);
		messageArea.add(messageArea_label, BorderLayout.CENTER);
		messageArea.setVisible(true);
		mainPane.add(messageArea, constr);


		// Game State area
		constr.gridx = 0;
		constr.gridy = 2;
		gameStateArea.setBackground(Color.blue);
		gameStateArea.setPreferredSize(new Dimension(200, 300));
		gameStateArea.setVisible(true);
		mainPane.add(gameStateArea, constr);

		// Player Statistics Area
		constr.gridx = 0;
		constr.gridy = 3;
		constr.gridheight = 2;
		constr.fill = GridBagConstraints.VERTICAL;
		playerStatsArea.setLayout(cardDisplay);
		playerStatsArea.setBackground(Color.magenta);
		playerStatsArea.setPreferredSize(new Dimension(200, 100));

		initPane.add(cards);
		initPane.setBackground(Color.GREEN);
		deckPane.add(back);
		deckPane.setBackground(Color.DARK_GRAY);
		back.setAlignmentX(Component.CENTER_ALIGNMENT);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		//    scroll.setBounds(50, 30, 300, 50);
		scroll.setBackground(Color.MAGENTA);
		playerStatsArea.add(initPane, "1");
		playerStatsArea.add(scroll, "2");

		cardDisplay.show(playerStatsArea, "1");

		cards.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				cardDisplay.show(playerStatsArea, "2");
			}
		});

		back.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				cardDisplay.show(playerStatsArea, "1");
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
		footerArea.setBackground(Color.cyan);
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
		JFrame parent = new JFrame();
		JOptionPane optionPane = new JOptionPane();
		optionPane.setOptionType(JOptionPane.DEFAULT_OPTION);
		String s = (String) optionPane.showInputDialog(parent, "Enter Starting territory, " + playerName, "Territory");
		return s;
	}

	////////////////////////////////////////////////////////////
	//	Pre Player Turn Methods
	////////////////////////////////////////////////////////////

	@Override
	public void promptPlayerTurn(Player p)
	{
		updateCards(p, false);

		String text = p.getName() + "'s Turn";
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
			//System.out.println(p.getCards().size());
			System.out.println(c.getTerritory());
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
				generateWarning("You selected card with: " + lastCardSelected.toString());
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
		JOptionPane optionPane = new JOptionPane();
		optionPane.setOptionType(JOptionPane.DEFAULT_OPTION);
		if(optionPane.showConfirmDialog(null, "Do you want to attack another territory?", "Attack!", JOptionPane.YES_NO_OPTION)
		        == JOptionPane.NO_OPTION)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public String getTerritoryToAttack(Player p)
	{
		this.generateWarning("Choose a territory to attack");
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
	public String getTerritoryToAttackFrom(Player p, String territoryToAttack)
	{
		this.generateWarning("Choose territory to attack from");
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
		JLabel label;
		if(results.getAttackSuccess())
		{
			label = new JLabel("Attack was a victory! Attacker lost " + results.getNumAttackerLosses()
			                   + " armies and defender lost " + results.getNumDefenderLosses() + " armies");
		}
		else
		{
			label = new JLabel("Attack was a unsuccessful. Attacker lost " + results.getNumAttackerLosses()
			                   + " armies and defender lost " + results.getNumDefenderLosses() + " armies");
		}
		JPanel panel = new JPanel();
		panel.add(label);
		JOptionPane.showConfirmDialog(null, panel, "Results", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
	}

	////////////////////////////////////////////////////////////
	//	Fortify Troops methods
	////////////////////////////////////////////////////////////

	@Override
	public boolean getWantsToFortify(Player p)
	{
		System.err.println("Unimplemented Feature"); // TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getTerritoryToFortify(Player p)
	{
		System.err.println("Unimplemented Feature"); // TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTerritoryToFortifyFrom(Player p, String terrID)
	{
		System.err.println("Unimplemented Feature"); // TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNumArmiesToFortify(String terrToFortFrom)
	{
		System.err.println("Unimplemented Feature"); // TODO Auto-generated method stub
		return 0;
	}

	////////////////////////////////////////////////////////////
	//	Utility Methods
	////////////////////////////////////////////////////////////

	@Override
	public void generateWarning(String string)
	{
		messageArea_label.setText("");
		try
		{
			Thread.sleep(80);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		messageArea_label.setForeground(Color.BLACK);
		messageArea_label.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		messageArea_label.setText(string);
	}

	@Override
	public void clearWarnings()
	{
		messageArea_label.setText("");
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
}

