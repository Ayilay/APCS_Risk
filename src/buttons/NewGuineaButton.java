package buttons;

import java.awt.Dimension;

public class NewGuineaButton extends TerritoryButton
{

	public NewGuineaButton(String s)
	{
		super(s);
		this.setPreferredSize(new Dimension(64, 40));
		this.setSize(new Dimension(64, 40));
		this.setLocation(902, 303);
		shape.addPoint(23, 28);
		shape.addPoint(39, 33);
		shape.addPoint(45, 26);
		shape.addPoint(64, 40);
		shape.addPoint(54, 18);
		shape.addPoint(24, 4);
		shape.addPoint(14, 8);
		shape.addPoint(9, 0);
		shape.addPoint(0, 4);
		shape.addPoint(1, 12);
		shape.addPoint(25, 22);
	}
}
