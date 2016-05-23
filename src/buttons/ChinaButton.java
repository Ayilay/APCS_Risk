package buttons;

import java.awt.Dimension;

public class ChinaButton extends TerritoryButton
{
	public ChinaButton(String s)
	{
		super(s);
		this.setPreferredSize(new Dimension(45, 41));
		this.setSize(new Dimension(45, 41));
		this.setLocation(151, 233);
		shape.addPoint(14, 0);
		shape.addPoint(10, 8);
		shape.addPoint(27, 8);
		shape.addPoint(25, 27);
		shape.addPoint(29, 33);
		shape.addPoint(45, 34);
		shape.addPoint(43, 40);
		shape.addPoint(39, 36);
		shape.addPoint(34, 41);
		shape.addPoint(15, 30);
		shape.addPoint(11, 20);
		shape.addPoint(0, 16);
	}
}
