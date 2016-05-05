import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CLIManager implements UserInterface
{
	BufferedReader br;

	public CLIManager()
	{
		br = new BufferedReader(new InputStreamReader(System.in));
	}

	@Override
	public int getNumPlayers()
	{
		System.out.println("How many players will be playing?");
		boolean valid = false;
		String input = "";
		int numPlayers = 0;
		while(!valid)
		{
			try
			{
				input = br.readLine();
				numPlayers = Integer.parseInt(input);
			}
			catch(NumberFormatException e)
			{
				System.err.println("Bad number of players");
				continue;
			}
			catch(IOException e)
			{
				System.out.println("weird IO Exception, don't know how to handle, continuing");
			}

			if(numPlayers <= 0 || numPlayers > 5)
			{
				System.out.println("Too many/too little");
			}
			else
			{
				valid = true;
			}
		}
		return numPlayers;
	}

	@Override
	public String getPlayerName()
	{
		System.out.print("Enter player name: ");

		String name = "";
		try
		{
			name = br.readLine();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

		return name;
	}

	@Override
	public String getStartingTerritory(String playerName)
	{
		System.out.print("Enter starting territory for " + playerName + ": ");

		String territoryID = "";
		try
		{
			territoryID = br.readLine();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

		return territoryID;
	}

	@Override
	public void generateWarning(String string)
	{
		System.out.println(string);
	}
}