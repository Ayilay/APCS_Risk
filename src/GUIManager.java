import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.swing.JFrame;

public class GUIManager implements UserInterface
{
	private JFrame window;
	BufferedReader br;

	public GUIManager()
	{
		br = new BufferedReader(new InputStreamReader(System.in));

		window = new JFrame();
		window.setTitle("Risk (GUI TEST)");
		window.setSize(1200, 700);

		// Uncomment the following if necessary
		//window.setResizable(false);

		window.setVisible(true);

		try
		{
			br.readLine();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	////////////////////////////////////////////////////////////
	//	Init Methods
	////////////////////////////////////////////////////////////

	@Override
	public int getNumPlayers()
	{
		System.err.println("Unimplemented Feature"); // TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getPlayerName()
	{
		System.err.println("Unimplemented Feature"); // TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getStartingTerritory(String playerName)
	{
		System.err.println("Unimplemented Feature"); // TODO Auto-generated method stub
		return null;
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
	public int selectCardUse()
	{
		System.err.println("Unimplemented Feature"); // TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void useCard(Player p)
	{
		System.err.println("Unimplemented Feature"); // TODO Auto-generated method stub

	}

	@Override
	public void tradeCardSets(Player p, CardDeck deck)
	{
		System.err.println("Unimplemented Feature"); // TODO Auto-generated method stub

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
