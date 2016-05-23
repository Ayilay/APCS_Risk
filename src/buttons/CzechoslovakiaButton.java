package buttons;

import java.awt.Dimension;

public class CzechoslovakiaButton extends TerritoryButton
{
	public CzechoslovakiaButton(String s)
	{
		super(s);
		this.setPreferredSize(new Dimension(30, 17));
		this.setSize(new Dimension(30, 17));
		this.setLocation(498, 114);
		shape.addPoint(18, 7);
		shape.addPoint(30, 7);
		shape.addPoint(30, 12);
		shape.addPoint(24, 17);
		shape.addPoint(13, 15);
		shape.addPoint(16, 10);
		shape.addPoint(12, 7);
		shape.addPoint(0, 7);
		shape.addPoint(0, 2);
		shape.addPoint(8, 0);
	}
}
