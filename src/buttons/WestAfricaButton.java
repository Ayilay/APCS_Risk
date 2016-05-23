package buttons;

import java.awt.Dimension;

public class WestAfricaButton extends TerritoryButton
{
	public WestAfricaButton(String s)
	{
		super(s);
		this.setPreferredSize(new Dimension(136, 90));
		this.setSize(new Dimension(136, 90));
		this.setLocation(401, 201);
		shape.addPoint(13, 0);
		shape.addPoint(31, 0);
		shape.addPoint(32, 4);
		shape.addPoint(64, 32);
		shape.addPoint(97, 13);
		shape.addPoint(136, 28);
		shape.addPoint(133, 58);
		shape.addPoint(104, 77);
		shape.addPoint(111, 90);
		shape.addPoint(90, 90);
		shape.addPoint(69, 75);
		shape.addPoint(28, 83);
		shape.addPoint(0, 53);
	}
}
