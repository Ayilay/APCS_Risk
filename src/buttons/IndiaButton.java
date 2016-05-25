package buttons;

import java.awt.Dimension;

public class IndiaButton extends TerritoryButton
{
	public IndiaButton(String s)
	{
		super(s);
		this.setPreferredSize(new Dimension(77, 86));
		this.setSize(new Dimension(77, 86));
		this.setLocation(694, 184);
		shape.addPoint(19, 0);
		shape.addPoint(77, 19);
		shape.addPoint(70, 37);
		shape.addPoint(67, 35);
		shape.addPoint(35, 60);
		shape.addPoint(28, 86);
		shape.addPoint(5, 40);
		shape.addPoint(0, 15);
		shape.addPoint(10, 13);
	}
}
