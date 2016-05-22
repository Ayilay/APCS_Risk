package buttons;

import java.awt.Dimension;

public class SouthernEuropeButton extends TerritoryButton
{

	public SouthernEuropeButton(String s)
	{
		super(s);
		this.setPreferredSize(new Dimension(67, 44));
		this.setSize(new Dimension(67, 44));
		this.setLocation(482, 122);
		shape.addPoint(28, 38);
		shape.addPoint(3, 16);
		shape.addPoint(0, 7);
		shape.addPoint(7, 5);
		shape.addPoint(11, 7);
		shape.addPoint(25, 0);
		shape.addPoint(30, 3);
		shape.addPoint(27, 8);
		shape.addPoint(32, 11);
		shape.addPoint(42, 11);
		shape.addPoint(48, 4);
		shape.addPoint(62, 4);
		shape.addPoint(67, 15);
		shape.addPoint(65, 23);
		shape.addPoint(59, 28);
		shape.addPoint(50, 31);
		shape.addPoint(49, 44);
		shape.addPoint(36, 22);
		shape.addPoint(19, 12);
		shape.addPoint(16, 13);
		shape.addPoint(36, 30);
	}

}
