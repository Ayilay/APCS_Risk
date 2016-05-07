public interface UserInterface
{
	// Init methods for players
	public int getNumPlayers();
	public String getPlayerName();
	public String getStartingTerritory(String playerName);

	// Pre Player Turn Methods
	void promptPlayerTurn(Player p);

	// Deploy Armies Methods
	public String selectDeployTerritory(Player p);
	public int getNumArmiesToDeploy(Player p, String deployTerritory);

	// Utility Methods
	public void generateWarning(String string);
}
