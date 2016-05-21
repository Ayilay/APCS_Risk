package buttons;

import java.awt.Dimension;

public class QuebecButton extends TerritoryButton
{
	public QuebecButton()
	{
		this.setPreferredSize(new Dimension(70, 65));
		shape.addPoint(0, 39);
		shape.addPoint(30, 0);
		shape.addPoint(60, 10);
		shape.addPoint(70, 34);
		shape.addPoint(44, 42);
		shape.addPoint(52, 58);
		shape.addPoint(30, 65);
		shape.addPoint(27, 52);
		shape.addPoint(1, 55);
	}
}
