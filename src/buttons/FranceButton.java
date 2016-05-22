package buttons;

import java.awt.Dimension;

public class FranceButton extends TerritoryButton
{
	public FranceButton(String s)
	{
		super(s);
		this.setPreferredSize(new Dimension(30, 26));
		this.setSize(new Dimension(30, 26));
		this.setLocation(453, 116);
		shape.addPoint(0, 8);
		shape.addPoint(5, 22);
		shape.addPoint(18, 26);
		shape.addPoint(30, 25);
		shape.addPoint(24, 13);
		shape.addPoint(28, 7);
		shape.addPoint(16, 0);
	}
}
