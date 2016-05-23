package buttons;

import java.awt.Dimension;

public class EastAfricaButton extends TerritoryButton
{

	public EastAfricaButton(String s)
	{
		super(s);
		this.setPreferredSize(new Dimension(99, 122));
		this.setSize(new Dimension(99, 122));
		this.setLocation(534, 220);
		shape.addPoint(7, 0);
		shape.addPoint(47, 0);
		shape.addPoint(75, 43);
		shape.addPoint(99, 36);
		shape.addPoint(84, 68);
		shape.addPoint(55, 99);
		shape.addPoint(60, 119);
		shape.addPoint(41, 122);
		shape.addPoint(23, 99);
		shape.addPoint(44, 74);
		shape.addPoint(40, 66);
		shape.addPoint(19, 64);
		shape.addPoint(0, 33);
		shape.addPoint(5, 23);
	}

}
