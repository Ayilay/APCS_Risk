package buttons;

public class ChinaButton extends TerritoryButton 
{
	public ChinaButton(String s)
	{
		super(s);
		shape.addPoint(0, 0);
		shape.addPoint(30, 0);
		shape.addPoint(30, 90);
		shape.addPoint(0, 90);
	}
}	
