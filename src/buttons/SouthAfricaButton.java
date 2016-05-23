package buttons;

import java.awt.Dimension;

public class SouthAfricaButton extends TerritoryButton
{
	public SouthAfricaButton(String s)
	{
		super(s);
		this.setPreferredSize(new Dimension(86, 101));
		this.setSize(new Dimension(86, 101));
		this.setLocation(501, 324);
		shape.addPoint(0, 0);
		shape.addPoint(86, 39);
		shape.addPoint(65, 80);
		shape.addPoint(45, 101);
		shape.addPoint(18, 101);
	}
}
