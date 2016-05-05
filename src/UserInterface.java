public interface UserInterface
{
	// Init methods for players
	public int getNumPlayers();
	public String getPlayerName();
	public String getStartingTerritory(String playerName);
	public void generateWarning(String string);
}
