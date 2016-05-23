package buttons;

import java.awt.Dimension;

public class PhilippinesButton extends TerritoryButton
{
	public PhilippinesButton(String s)
	{
		super(s);
		this.setPreferredSize(new Dimension(33, 47));
		this.setSize(new Dimension(33, 47));
		this.setLocation(855, 233);
		shape.addPoint(7, 0);
		shape.addPoint(12, 1);
		shape.addPoint(15, 6);
		shape.addPoint(12, 12);
		shape.addPoint(16, 16);
		shape.addPoint(19, 16);
		shape.addPoint(29, 27);
		shape.addPoint(33, 44);
		shape.addPoint(24, 47);
		shape.addPoint(17, 38);
		shape.addPoint(27, 34);
		shape.addPoint(20, 21);
		shape.addPoint(15, 21);
		shape.addPoint(21, 34);
		shape.addPoint(17, 35);
		shape.addPoint(12, 24);
		shape.addPoint(0, 38);
		shape.addPoint(0, 33);
		shape.addPoint(6, 26);
		shape.addPoint(12, 24);
		shape.addPoint(5, 9);
	}
}
