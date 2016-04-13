
public class Main 
{
	public static void main(String[] args)
	{
		//TODO: implement GUI
		int numPlayers = 2; //not sure how many players
		Player[] players = new Player[numPlayers]; 
		TerritoryMap.init();
		
		Die_Roll die;
		
		for(int i = 0; i < numPlayers; i++)
		{
			String name = "";
			Territory t = null;
			
			//Change these displays to GUI later and pass in values to variables
			System.out.println("Player " + i + ": Enter name");
			System.out.println("Player " + i + ": Choose Territory");
			
			players[i] = new Player(name, t);
		}
		
		while(true)
		{
			for(Player p : players)
			{
				//GUI stuff 
			}
		}
	}
}
