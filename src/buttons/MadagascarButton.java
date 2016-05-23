package buttons;

import java.awt.Dimension;

public class MadagascarButton extends TerritoryButton
{

	public MadagascarButton(String s)
	{
		super(s);
		this.setPreferredSize(new Dimension(25, 47));
		this.setSize(new Dimension(25, 47));
		this.setLocation(602, 347);
		shape.addPoint(21, 0);
		shape.addPoint(25, 6);
		shape.addPoint(25, 11);
		shape.addPoint(12, 47);
		shape.addPoint(2, 47);
		shape.addPoint(0, 36);
		shape.addPoint(4, 27);
		shape.addPoint(4, 18);
		shape.addPoint(8, 13);
		shape.addPoint(10, 13);
	}
}
