package buttons;

import java.awt.Dimension;

public class CongoButton extends TerritoryButton
{
	public CongoButton(String s)
	{
		super(s);
		this.setPreferredSize(new Dimension(83, 83));
		this.setSize(new Dimension(83, 83));
		this.setLocation(491, 261);
		shape.addPoint(0, 32);
		shape.addPoint(24, 33);
		shape.addPoint(17, 18);
		shape.addPoint(44, 0);
		shape.addPoint(62, 25);
		shape.addPoint(82, 27);
		shape.addPoint(83, 36);
		shape.addPoint(65, 57);
		shape.addPoint(61, 83);
		shape.addPoint(43, 79);
		shape.addPoint(41, 65);
		shape.addPoint(8, 61);
	}
}
