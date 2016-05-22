package buttons;

import java.awt.Dimension;

public class SwedenButton extends TerritoryButton
{
	public SwedenButton(String s)
	{
		super(s);
		this.setPreferredSize(new Dimension(22, 29));
		this.setSize(new Dimension(22, 29));
		this.setLocation(522, 52);
		shape.addPoint(14, 0);
		shape.addPoint(22, 25);
		shape.addPoint(17, 29);
		shape.addPoint(0, 25);
		shape.addPoint(7, 13);
		shape.addPoint(4, 3);
		shape.addPoint(11, 5);
	}
}
