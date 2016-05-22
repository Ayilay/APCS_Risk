package buttons;

import java.awt.Dimension;

public class ScandinaviaButton extends TerritoryButton
{

	public ScandinaviaButton(String s)
	{
		super(s);
		this.setPreferredSize(new Dimension(61, 51));
		this.setSize(new Dimension(61, 51));
		this.setLocation(479, 47);
		shape.addPoint(0, 31);
		shape.addPoint(46, 0);
		shape.addPoint(61, 2);
		shape.addPoint(44, 8);
		shape.addPoint(47, 14);
		shape.addPoint(31, 30);
		shape.addPoint(37, 38);
		shape.addPoint(23, 51);
		shape.addPoint(13, 38);
		shape.addPoint(6, 42);
	}
}
