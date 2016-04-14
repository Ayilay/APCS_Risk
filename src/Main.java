
public class Main 
{
    private Player[] players;
    private int numPlayers;
    
	public static void main(String[] args)
	{
	    Main riskGame = new Main();
	    riskGame.play();
	}
	
	private void play()
	{
		//TODO: implement GUI
	    init();
		
		Die_Roll die;
		
		
		while(true)
		{
			for(Player p : players)
			{
				//GUI stuff 
			    System.out.println(p.getName() + "'s turn");
			}
		}
	}
	
	/*
	 * Initialize the players and the TerritoryMap
	 */
	private void init()
	{
		TerritoryMap.init();

	    getNumPlayers();
		players = new Player[numPlayers]; 

		for(int i = 0; i < numPlayers; i++)
		{
			String name = "Derp" + i;
			String territoryID = "null";
			
			//TODO: Change these displays to GUI later and pass in values to variables
			System.out.println("Player " + (i + 1) + ": Enter name");
			System.out.println("Player " + (i + 1) + ": Choose Territory");
			
			players[i] = new Player(name, territoryID);
		}
	}

	private void getNumPlayers()
	{
	    // TODO: get input from player
		numPlayers = 2;
	}
}
