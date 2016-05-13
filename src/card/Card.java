package card;
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

	@Override
	public String toString()
	{
		return territory + " - " + stars;
	}

	@Override
	public boolean equals(Object obj)
	{
		if(!(obj instanceof Card))
			return false;
		Card other = (Card) obj;

		return territory.equals(other.getTerritory()) && stars == other.getValue();
	}
}
