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

	// Attack Territories methods
	public boolean getFinishedAttacking();
	public String getTerritoryToAttack(Player p);
	public String getTerritoryToAttackFrom(Player p, String territoryToAttack);
	public int getNumArmiesToAttackWith(Player p, String territoryToAttackID, String territoryToAttackFromID);
	public void displayBattleResults(BattleResults results);

	// Utility Methods
	public void generateWarning(String string);
}
