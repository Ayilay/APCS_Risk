package buttons;

public class ChinaButton extends TerritoryButton 
{
	public ChinaButton(String s)
	{
		//this.setSize(30, 30);
		super(s);
		shape.addPoint(0, 0);
		shape.addPoint(30, 0);
		shape.addPoint(30, 90);
		shape.addPoint(0, 90);

		this.setMinimumSize(this.getSize());
		this.setMaximumSize(this.getSize());
		this.setPreferredSize(this.getSize());
	}
}	
