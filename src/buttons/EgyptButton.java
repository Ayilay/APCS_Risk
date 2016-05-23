package buttons;

import java.awt.Dimension;

public class EgyptButton extends TerritoryButton
{
	public EgyptButton(String s)
	{
		super(s);
		this.setPreferredSize(new Dimension(81, 48));
		this.setSize(new Dimension(81, 48));
		this.setLocation(492, 180);
		shape.addPoint(33, 0);
		shape.addPoint(77, 6);
		shape.addPoint(81, 38);
		shape.addPoint(47, 38);
		shape.addPoint(46, 48);
		shape.addPoint(21, 35);
		shape.addPoint(10, 34);
		shape.addPoint(0, 27);
		shape.addPoint(6, 0);
		shape.addPoint(31, 9);
	}
}
