package buttons;

import java.awt.Dimension;

public class QuebecButton extends TerritoryButton
{
	public QuebecButton(String s)
	{
		super(s);
		this.setPreferredSize(new Dimension(81, 66));
		this.setSize(new Dimension(81, 66));
		this.setLocation(222, 69);
		shape.addPoint(9, 39);
		shape.addPoint(41, 0);
		shape.addPoint(81, 33);
		shape.addPoint(44, 42);
		shape.addPoint(61, 55);
		shape.addPoint(37, 66);
		shape.addPoint(33, 54);
		shape.addPoint(25, 59);
		shape.addPoint(0, 54);
	}
}
