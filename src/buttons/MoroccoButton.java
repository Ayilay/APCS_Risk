package buttons;

import java.awt.Dimension;

public class MoroccoButton extends TerritoryButton
{

	public MoroccoButton(String s)
	{
		super(s);
		this.setPreferredSize(new Dimension(45, 28));
		this.setSize(new Dimension(45, 28));
		this.setLocation(415, 171);
		shape.addPoint(0, 28);
		shape.addPoint(25, 0);
		shape.addPoint(41, 0);
		shape.addPoint(45, 11);
		shape.addPoint(32, 12);
		shape.addPoint(31, 17);
		shape.addPoint(27, 20);
		shape.addPoint(17, 23);
		shape.addPoint(17, 28);
	}

}
