package buttons;

import java.awt.Dimension;

public class PeruButton extends TerritoryButton
{

	public PeruButton(String s)
	{
		super(s);
		this.setPreferredSize(new Dimension(47, 74));
		this.setSize(new Dimension(47, 74));
		this.setLocation(180, 295);
		shape.addPoint(8, 0);
		shape.addPoint(23, 7);
		shape.addPoint(31, 15);
		shape.addPoint(38, 15);
		shape.addPoint(38, 21);
		shape.addPoint(33, 22);
		shape.addPoint(31, 25);
		shape.addPoint(29, 31);
		shape.addPoint(32, 39);
		shape.addPoint(36, 43);
		shape.addPoint(40, 44);
		shape.addPoint(45, 48);
		shape.addPoint(47, 68);
		shape.addPoint(42, 74);
		shape.addPoint(24, 63);
		shape.addPoint(14, 47);
		shape.addPoint(1, 23);
		shape.addPoint(6, 16);
		shape.addPoint(0, 11);
	}

}
