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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

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
<<<<<<< HEAD
import buttons.*;
=======
import buttons.AlaskaButton;
import buttons.AlbertaButton;
import buttons.GreenlandButton;
import buttons.NorthwestTerritoryButton;
import buttons.OntarioButton;
import buttons.QuebecButton;
>>>>>>> a59abab2a70359564e89d6441c8a17206b0d73b3
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
	private JPanel messageArea;
	private JPanel playerNameArea;
	private JPanel playerStatsArea;
	private JPanel gameStateArea;
	private JPanel footerArea;
	
	
	private JLabel playerNameArea_label;
	private JLabel messageArea_label;

	private JPanel deck;
	private JPanel initPane;
	private JScrollPane scroll;

	private JButton cards;
	private JButton back;

	private Map<String, JButton> buttons;

	private CardLayout cardDisplay;

	private BufferedReader br;

	private boolean quit;
	
	public GUIManager()
	{
		br = new BufferedReader(new InputStreamReader(System.in));

		window = new JFrame();
		window.setTitle("Risk (GUI TEST)");
		window.setSize(1230, 745);

		mainPane = new JPanel(new GridBagLayout());
		mapArea = new JPanel();
		messageArea = new JPanel(new BorderLayout());
		playerNameArea = new JPanel(new BorderLayout());
		playerStatsArea = new JPanel();
		gameStateArea = new JPanel();
		footerArea = new JPanel();

		playerNameArea_label = new JLabel();
		messageArea_label = new JLabel();

		GridBagConstraints constr = new GridBagConstraints();

		deck = new JPanel();
		initPane = new JPanel();
		scroll = new JScrollPane(deck);

		cards = new JButton("Display cards");
		back = new JButton("Back");

		cardDisplay = new CardLayout();

		quit = false;
		
		System.out.println(window.getWidth());
		System.out.println(window.getHeight());

		//mainPane.setPreferredSize(new Dimension(1200, 700));
		mainPane.setBackground(Color.BLACK);

		ImageIcon img = new ImageIcon("Risk_Final_Map.png");

		window.add(mainPane);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //terminates the program on close
		//TODO: Setting the window to visible allows me to access the dimensions of the mainPane,
		//TODO: but I can not add anything to the window after this. Make sure setVisible is at the END

		//int width = mainPane.getWidth() - 100;
		//int height = mainPane.getHeight() - 20;
		//System.out.println(width);
		//System.out.println(height);
		//mainPane.setVisible(true);

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
		deck.add(back);
		deck.setBackground(Color.DARK_GRAY);
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

		
		mapArea.add(new JLabel(img));
		mapArea.setBackground(Color.WHITE);
		mapArea.setPreferredSize(new Dimension(1000, 512));
		mapArea.setVisible(true);
		mainPane.add(mapArea, constr);

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
		JSlider slider = getSlider(optionPane,2,5);
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
		updateCards(p);

		String text = p.getName() + "'s Turn";
		playerNameArea_label.setForeground(Color.BLACK);
		int fontSize = getFontSize(playerNameArea_label, text);
		playerNameArea_label.setFont(new Font("Times New Roman", Font.PLAIN, fontSize));
		playerNameArea_label.setText(text);
		try
		{
			br.readLine();
		}
		catch(IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	////////////////////////////////////////////////////////////
	//	Use Card Methods
	////////////////////////////////////////////////////////////

	public void updateCards(Player p)
	{
		deck.removeAll();
		deck.add(back);
		deck.setBackground(Color.DARK_GRAY);
		for(Card c : p.getCards())
		{
			System.out.println(c.getTerritory());
			JPanel panel = c.drawCard();
			deck.add(panel);
			deck.getComponents();
		}
	}
	
	@Override
	public Card selectCard(Player p)
	{
		
		cardDisplay.show(playerStatsArea, "2");
		
		if(!p.hasCards())
		{
			System.out.println("You do not have any cards");
			return null;
		}
		
		generateWarning("Select a card");
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
		JFrame parent = new JFrame();
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
		JFrame parent = new JFrame();
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
		System.err.println("Unimplemented Feature"); // TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNumArmiesToDeploy(Player p, String deployTerritory)
	{
		JFrame parent = new JFrame();
		JOptionPane optionPane = new JOptionPane();
		JSlider slider = getSlider(optionPane,0,p.getNumReinforcementsAvailable());
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
		System.err.println("Unimplemented Feature"); // TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getTerritoryToAttack(Player p)
	{
		System.err.println("Unimplemented Feature"); // TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTerritoryToAttackFrom(Player p, String territoryToAttack)
	{
		System.err.println("Unimplemented Feature"); // TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNumArmiesToAttackWith(Player p, String territoryToAttackID, String territoryToAttackFromID)
	{
		JFrame parent = new JFrame();
		JOptionPane optionPane = new JOptionPane();
		JSlider slider = getSlider(optionPane,0,TerritoryMap.get(territoryToAttackID).getNumArmies());
		optionPane.setMessage(new Object[]
		                      { "Choose number of armies to attack " + territoryToAttackFromID + " with.", slider });
		optionPane.setMessageType(JOptionPane.QUESTION_MESSAGE);
		optionPane.setOptionType(JOptionPane.DEFAULT_OPTION);
		JDialog dialog = optionPane.createDialog(parent, "Attack!");
		dialog.setVisible(true);
		return (Integer) slider.getValue();
	}

	@Override
	public void displayBattleResults(BattleResults results)
	{
		System.err.println("Unimplemented Feature"); // TODO Auto-generated method stub

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
		messageArea_label.setForeground(Color.BLACK);
		messageArea_label.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		messageArea_label.setText(string);
	}

	public int getFontSize(JLabel label, String text)
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
		
		
		////////////////////////////////////////////////////////////
		//	Add buttons to map
		////////////////////////////////////////////////////////////
		
		//North America
		buttons.put("Greenland", greenland);
		buttons.put("Quebec", quebec);
		buttons.put("Ontario", ontario);
		buttons.put("Alberta", alberta);
		buttons.put("Alaska", alaska);
		buttons.put("Northwest Territory", northWestTerritory);
		
		
		////////////////////////////////////////////////////////////
		//	Add to map
		////////////////////////////////////////////////////////////
		
		//North America
		mapArea.add(greenland);
<<<<<<< HEAD
		
		JButton china = new ChinaButton();
		buttons.put("China", china);
		mapArea.add(china);
=======
		mapArea.add(quebec);
		mapArea.add(ontario);
		mapArea.add(alberta);
		mapArea.add(alaska);
		mapArea.add(northWestTerritory);
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
>>>>>>> a59abab2a70359564e89d6441c8a17206b0d73b3
	}
}	

