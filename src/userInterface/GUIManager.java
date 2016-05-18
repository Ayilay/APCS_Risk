package userInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.WindowEvent;
import java.awt.Toolkit;
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

	private final int WIDTH = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	private final int HEIGHT = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	
	public GUIManager()
	{
		br = new BufferedReader(new InputStreamReader(System.in));

		window = new JFrame();
		window.setTitle("Risk (GUI TEST)");
		window.setSize(WIDTH,HEIGHT);
		//window.setResizable(false);

		mainPane = new JPanel(new GridBagLayout());
		mapArea = new JPanel();
		messageArea = new JPanel();
		playerNameArea = new JPanel();
		playerStatsArea = new JPanel();
		gameStateArea = new JPanel();
		footerArea = new JPanel();

		
		GridBagConstraints constr = new GridBagConstraints();
		
		System.out.println(window.getWidth());
		System.out.println(window.getHeight());
		
		mainPane.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		mainPane.setBackground(Color.BLACK);
		
		ImageIcon img = new ImageIcon("Risk_Final_Map.png");		
		
		window.add(mainPane);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //terminates the program on close
		window.setVisible(true);
//TODO: Setting the window to visible allows me to access the dimensions of the mainPane,
//TODO: but I can not add anything to the window after this. Make sure setVisible is at the END
		
		int width = mainPane.getWidth() - 100;
		int height = mainPane.getHeight() - 20;
		System.out.println(width);
		System.out.println(height);
		//mainPane.setVisible(true);

		// add the elements
		constr.weightx = 0;
		constr.weighty = 0;

		
		// Player Name area
		constr.gridx = 0;
		constr.gridy = 0;
		constr.fill = GridBagConstraints.VERTICAL;
		constr.gridheight = 2;
		playerNameArea.setBackground(Color.RED);
		playerNameArea.setPreferredSize(new Dimension((int)(width*200/1200),(int)(height*120/700)));
		playerNameArea.setVisible(true);
		mainPane.add(playerNameArea, constr);

 		// Message Area
		constr.gridx = 1;
		constr.gridy = 0;
		constr.fill = GridBagConstraints.NONE;
		constr.gridheight = 1;
		messageArea.setBackground(Color.ORANGE);
		messageArea.setPreferredSize(new Dimension((int)(width*1000/1200),(int)(height*(600-512)/700)));
		messageArea.setVisible(true);
		mainPane.add(messageArea, constr);
		

		// Game State area
		constr.gridx = 0;
		constr.gridy = 2;
		gameStateArea.setBackground(Color.blue);
		gameStateArea.setPreferredSize(new Dimension((int)(width*200/1200),(int)(height*300/700)));
		gameStateArea.setVisible(true);
		mainPane.add(gameStateArea, constr);

		// Player Statistics Area
		constr.gridx = 0;
		constr.gridy = 3;
		constr.gridheight = 2;
		constr.fill = GridBagConstraints.VERTICAL;
		playerStatsArea.setBackground(Color.magenta);
		playerStatsArea.setPreferredSize(new Dimension((int)(width*200/1200),(int)(height*100/700)));
		playerStatsArea.setVisible(true);
		mainPane.add(playerStatsArea, constr);

		// Map Area
		constr.gridx = 1;
		constr.gridy = 1;
		constr.fill = GridBagConstraints.BOTH;
		constr.gridheight = 3;
		mapArea.add(new JLabel(img));
		mapArea.setBackground(Color.WHITE);
		mapArea.setPreferredSize(new Dimension((int)(width*1000/1200),(int)(height*512/700)));
		mapArea.setVisible(true);
		mainPane.add(mapArea, constr);
//1200
		// Footer
		constr.gridx = 1;
		constr.gridy = 4;
		constr.gridheight = 1;
		footerArea.setBackground(Color.cyan);
		footerArea.setPreferredSize(new Dimension((int)(width*100/1200),(int)(height*10/700)));
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
