package buttons;

import java.awt.Dimension;

public class WesternAustraliaButton extends TerritoryButton
{

	public WesternAustraliaButton(String s)
	{
		super(s);
		this.setPreferredSize(new Dimension(81, 99));
		this.setSize(new Dimension(81, 99));
		this.setLocation(881, 343);
		shape.addPoint(0, 53);
		shape.addPoint(40, 55);
		shape.addPoint(26, 99);
		shape.addPoint(52, 99);
		shape.addPoint(80, 68);
		shape.addPoint(81, 50);
		shape.addPoint(66, 26);
		shape.addPoint(60, 1);
		shape.addPoint(47, 23);
		shape.addPoint(34, 14);
		shape.addPoint(38, 4);
		shape.addPoint(26, 0);
		shape.addPoint(10, 13);
	}

}
