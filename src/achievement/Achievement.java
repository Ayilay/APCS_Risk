package achievement;
import main.Player;

/*
 * This class provides the template for an achievement system
 * . Each achievement will be a subclass that defines a constructor and one method
 */
public abstract class Achievement
{
	public String name; 		// the name of the achievement
	public String description; 	// the description
	public int bonus; 			// number of armies provided.
	public Achievement(String name, String description, int bonus)
	{
		this.name = name;
		this.description = description;
		this.bonus = bonus;
	}
	public abstract boolean isFullfilled(Player p);
	public String achieved()//message printed when achievement achieved
	{
		String congrats = "Achievement achieved! " + this.name + "\n" + this.description;
		congrats += "\n You receive a one time bonus of " + this.bonus + " armies next turn";
		return congrats;
	}
	public int getBonus()
	{
		return bonus;
	}
}
