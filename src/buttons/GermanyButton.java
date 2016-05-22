package buttons;

import java.awt.Dimension;

public class GermanyButton extends TerritoryButton
{
	public GermanyButton(String s)
	{
		super(s);
		this.setPreferredSize(new Dimension(23, 26));
		this.setSize(new Dimension(23, 26));
		this.setLocation(481, 101);
		shape.addPoint(7, 0);
		shape.addPoint(23, 2);
		shape.addPoint(23, 11);
		shape.addPoint(16, 14);
		shape.addPoint(17, 23);
		shape.addPoint(11, 26);
		shape.addPoint(0, 17);
		shape.addPoint(3, 4);
	}
}
