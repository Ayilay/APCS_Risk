
public class Card 
{
	private String territory;
	private int stars; 
	
	public Card(String ter, int value)
	{
		this.territory = ter;
		stars = value;
	}
	
	public int getValue()
	{
		return stars;
	}
	
	public String getTerritory()
	{
		return territory;
	}
}
