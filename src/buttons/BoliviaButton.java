package buttons;

import java.awt.Dimension;

public class BoliviaButton extends TerritoryButton
{

	public BoliviaButton(String s)
	{
		super(s);
		this.setPreferredSize(new Dimension(55, 66));
		this.setSize(new Dimension(55, 66));
		this.setLocation(224, 336);
		shape.addPoint(0, 7);
		shape.addPoint(2, 30);
		shape.addPoint(6, 43);
		shape.addPoint(11, 48);
		shape.addPoint(26, 45);
		shape.addPoint(44, 57);
		shape.addPoint(44, 66);
		shape.addPoint(51, 66);
		shape.addPoint(55, 61);
		shape.addPoint(55, 54);
		shape.addPoint(42, 46);
		shape.addPoint(41, 30);
		shape.addPoint(37, 24);
		shape.addPoint(32, 23);
		shape.addPoint(30, 16);
		shape.addPoint(15, 10);
		shape.addPoint(10, 0);
	}

}
