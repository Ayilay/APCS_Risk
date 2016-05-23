package buttons;

import java.awt.Dimension;

public class GreenlandButton extends TerritoryButton
{
	public GreenlandButton(String s)
	{
		super(s);
		this.setPreferredSize(new Dimension(111, 63));
		this.setSize(new Dimension(111, 63));
		this.setLocation(314, 17);
		shape.addPoint(0, 9);
		shape.addPoint(111, 0);
		shape.addPoint(81, 35);
		shape.addPoint(48, 46);
		shape.addPoint(32, 63);
		shape.addPoint(18, 54);
		shape.addPoint(31, 33);
		shape.addPoint(18, 13);
	}
}
