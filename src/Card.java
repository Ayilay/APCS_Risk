
public class Card 
{
	private Territory territory; 
	private String unit;
	private int value; 
	
	public Card(Territory ter, String type)
	{
		this.territory = ter;
		this.unit = type;
		
		if(unit.equals("Infantry"))
			value = 1;
		else if(unit.equals("Cavalry"))
			value = 2;
		else if(unit.equals("Artillery"))
			value = 3;
	}
	
	public String getUnitType()
	{
		return unit;
	}
	
	public int getValue()
	{
		return value;
	}
	
	public Territory getTerritory()
	{
		return territory;
	}

	
}
