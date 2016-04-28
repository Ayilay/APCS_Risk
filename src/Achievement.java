/*
 * This class provides the template for an achievement system
 * . Each achievement will be a subclass that defines a constructor and one method
 */
public abstract class Achievement
{
	public String name; 		// the name of the achievement
	public String description; 	// the description
	public int bonus; 			// number of armies provided.
	public boolean oneTime;		// true if it is a one time bonus, false for
								// reinforcement bonus
	public Achievement(String name, String description, int bonus, boolean oneTime)
	{
		this.name = name;
		this.description = description;
		this.bonus = bonus;
		this.oneTime = oneTime;
	}
	public abstract boolean isFullfilled(Player p);
}
