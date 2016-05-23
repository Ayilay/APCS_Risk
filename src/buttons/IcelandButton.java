package buttons;

import java.awt.Dimension;

public class IcelandButton extends TerritoryButton
{
	public IcelandButton(String s)
	{
		super(s);
		this.setPreferredSize(new Dimension(21, 10));
		this.setSize(new Dimension(21, 10));
		this.setLocation(408, 62);
		shape.addPoint(0, 3);
		shape.addPoint(19, 0);
		shape.addPoint(21, 5);
		shape.addPoint(7, 10);
	}
}
