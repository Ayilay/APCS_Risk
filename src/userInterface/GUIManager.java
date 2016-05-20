package userInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.Toolkit;
import java.awt.CardLayout;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import battle.BattleResults;
import card.Card;
import main.Player;

public class GUIManager implements UserInterface
{
	public static Card lastCardSelected;
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

	private JButton cards;
	private JButton back;

	private CardLayout cardDisplay;

	private BufferedReader br;

	private final int WIDTH = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	private final int HEIGHT = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();

	public GUIManager()
	{
		br = new BufferedReader(new InputStreamReader(System.in));

		window = new JFrame();
		window.setTitle("Risk (GUI TEST)");
		window.setSize(1230, 745);
		//window.setResizable(false);

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

		cards = new JButton("Display cards");
		back = new JButton("Back");

		cardDisplay = new CardLayout();

		System.out.println(window.getWidth());
		System.out.println(window.getHeight());

		//mainPane.setPreferredSize(new Dimension(1200, 700));
		mainPane.setBackground(Color.BLACK);

		ImageIcon img = new ImageIcon("Risk_Final_Map.png");

		window.add(mainPane);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //terminates the program on close
		window.setVisible(true);
		//TODO: Setting the window to visible allows me to access the dimensions of the mainPane,
		//TODO: but I can not add anything to the window after this. Make sure setVisible is at the END

		// add the elements
		constr.weightx = 0;
		constr.weighty = 0;


		// Player Name area
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
		deck.setBackground(Color.MAGENTA);
		
		playerStatsArea.add(initPane, "1");
		playerStatsArea.add(deck, "2");

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



		//Allows termination of program when closed
		//window.addWindowListener(new java.awt.event.WindowAdapter()
		//{
		//	public void windowClosing(WindowEvent evt)
		//	{
		//		System.exit(0);
		//	}
		//});

	}


	////////////////////////////////////////////////////////////
	//	Init Methods
	////////////////////////////////////////////////////////////
	/*
	 * Slider code modified off: http://www.java2s.com/Tutorial/Java/0240__Swing/UsingJOptionPanewithaJSlider.htm
	 * Textfield code modified off http://docs.oracle.com/javase/tutorial/uiswing/components/dialog.html#input
	 */
	@Override
	public int getNumPlayers()
	{
		JFrame parent = new JFrame();

		JOptionPane optionPane = new JOptionPane();
		JSlider slider = getSlider(optionPane);
		optionPane.setMessage(new Object[]
		                      { "How many players will be playing?", slider });
		optionPane.setMessageType(JOptionPane.QUESTION_MESSAGE);
		optionPane.setOptionType(JOptionPane.DEFAULT_OPTION);
		JDialog dialog = optionPane.createDialog(parent, "Risk Set-Up");
		dialog.setVisible(true);
		return (Integer) slider.getValue();
	}

	private static JSlider getSlider(final JOptionPane dialog)
	{
		JSlider slider = new JSlider(2, 5);
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
		//TODO: Does not clear card pane on new turn
		for(Card c : p.getCards())
		{
			JPanel panel = c.drawCard();
			deck.add(panel);
		}

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


	@Override
	public Card selectCard(Player p)
	{
		System.out.println("You selected this card!: " + lastCardSelected.toString());
		return lastCardSelected;
	}

	@Override
	public boolean promptUseCard()
	{
		JFrame parent = new JFrame();
		JOptionPane optionPane = new JOptionPane();
		optionPane.setOptionType(JOptionPane.DEFAULT_OPTION);
		String s = (String) optionPane.showInputDialog(parent, "Use card? (Y/N)");
		if(s.equals("Y"))
		{
			return true;
		}
		return false;
	}

	@Override
	public boolean promptTradeCard()
	{
		// TODO Auto-generated method stub
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
		System.err.println("Unimplemented Feature"); // TODO Auto-generated method stub
		return 0;
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
		System.err.println("Unimplemented Feature"); // TODO Auto-generated method stub
		return 0;
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
}
