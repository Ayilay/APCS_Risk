package buttons;

import java.awt.Dimension;

public class QuebecButton extends TerritoryButton
{
	public QuebecButton(String s)
	{
		super(s);
		this.setPreferredSize(new Dimension(79, 66));
		this.setSize(new Dimension(79, 66));
		this.setLocation(223, 74);
		shape.addPoint(38, 0);
		shape.addPoint(53, 6);
		shape.addPoint(53, 13);
		shape.addPoint(60, 13);
		shape.addPoint(67, 8);
		shape.addPoint(69, 21);
		shape.addPoint(72, 27);
		shape.addPoint(79, 31);
		shape.addPoint(79, 35);
		shape.addPoint(65, 41);
		shape.addPoint(44, 43);
		shape.addPoint(60, 56);
		shape.addPoint(36, 66);
		shape.addPoint(33, 52);
		shape.addPoint(22, 60);
		shape.addPoint(10, 60);
		shape.addPoint(0, 54);
		shape.addPoint(16, 24);
		shape.addPoint(26, 18);
		shape.addPoint(27, 11);
		shape.addPoint(32, 6);
	}
}
