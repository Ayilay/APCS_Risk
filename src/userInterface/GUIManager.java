package userInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import battle.BattleResults;
import card.Card;
import main.Player;

public class GUIManager implements UserInterface
{
	private JFrame window;
	private JPanel mainPane;

	private JPanel mapArea;
	private JPanel messageArea;
	private JPanel playerNameArea;
	private JPanel playerStatsArea;
	private JPanel gameStateArea;
	private JPanel footerArea;

	private BufferedReader br;

	public GUIManager()
	{
		br = new BufferedReader(new InputStreamReader(System.in));

		window = new JFrame();
		window.setTitle("Risk (GUI TEST)");
		window.setSize(1200, 700);
		window.setResizable(false);

		mainPane = new JPanel(new GridBagLayout());
		mapArea = new JPanel(null);
		messageArea = new JPanel(null);
		playerNameArea = new JPanel(null);
		playerStatsArea = new JPanel(null);
		gameStateArea = new JPanel(null);
		footerArea = new JPanel(null);

		GridBagConstraints constr = new GridBagConstraints();

		// add the elements
		constr.weightx = 0;
		constr.weighty = 0;

		// Player Name area
		constr.gridx = 0;
		constr.gridy = 0;
		constr.fill = GridBagConstraints.VERTICAL;
		constr.gridheight = 2;
		playerNameArea.setBackground(Color.RED);
		playerNameArea.setVisible(true);
		playerNameArea.setPreferredSize(new Dimension(200, 120));
		mainPane.add(playerNameArea, constr);

		// Message Area
		constr.gridx = 1;
		constr.gridy = 0;
		constr.fill = GridBagConstraints.NONE;
		constr.gridheight = 1;
		messageArea.setBackground(Color.ORANGE);
		messageArea.setPreferredSize(new Dimension(1000, 600 - 512));
		mainPane.add(messageArea, constr);
		messageArea.setVisible(true);

		// Game State area
		constr.gridx = 0;
		constr.gridy = 2;
		gameStateArea.setBackground(Color.blue);
		gameStateArea.setPreferredSize(new Dimension(200, 300));
		mainPane.add(gameStateArea, constr);

		// Player Statistics Area
		constr.gridx = 0;
		constr.gridy = 3;
		constr.gridheight = 2;
		constr.fill = GridBagConstraints.VERTICAL;
		playerStatsArea.setBackground(Color.magenta);
		playerStatsArea.setPreferredSize(new Dimension(200, 100));
		mainPane.add(playerStatsArea, constr);

		// Map Area
		constr.gridx = 1;
		constr.gridy = 1;
		constr.fill = GridBagConstraints.BOTH;
		constr.gridheight = 3;
		mapArea.setBackground(Color.black);
		mapArea.setPreferredSize(new Dimension(1000, 512));
		mainPane.add(mapArea, constr);

		// Footer
		constr.gridx = 1;
		constr.gridy = 4;
		constr.gridheight = 1;
		footerArea.setBackground(Color.cyan);
		footerArea.setPreferredSize(new Dimension(100, 10));
		mainPane.add(footerArea, constr);


		//Allows termination of program when closed
		window.addWindowListener(new java.awt.event.WindowAdapter()
		{
			public void windowClosing(WindowEvent evt)
			{
				System.exit(0);
			}
		});

		window.add(mainPane);
		window.setVisible(true);
	}

	////////////////////////////////////////////////////////////
	//	Init Methods
	////////////////////////////////////////////////////////////

	@Override
	public int getNumPlayers()
	{
		return GUI_Init.getNumPlayers();
	}

	@Override
	public String getPlayerName()
	{
		return GUI_Init.getPlayerName();
	}

	@Override
	public String getStartingTerritory(String playerName)
	{
		return GUI_Init.getStartingTerritory(playerName);
	}

	////////////////////////////////////////////////////////////
	//	Pre Player Turn Methods
	////////////////////////////////////////////////////////////

	@Override
	public void promptPlayerTurn(Player p)
	{
		System.err.println("Unimplemented Feature"); // TODO Auto-generated method stub

	}

	////////////////////////////////////////////////////////////
	//	Use Card Methods
	////////////////////////////////////////////////////////////

	@Override
	public Card selectCard(Player p)
	{
		System.err.println("Unimplemented Feature"); // TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean promptUseCard()
	{
		// TODO Auto-generated method stub
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
		System.err.println("Unimplemented Feature"); // TODO Auto-generated method stub

	}
}
