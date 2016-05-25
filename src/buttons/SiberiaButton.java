package buttons;

import java.awt.Dimension;

public class SiberiaButton extends TerritoryButton
{

	public SiberiaButton(String s)
	{
		super(s);
		this.setPreferredSize(new Dimension(96, 83));
		this.setSize(new Dimension(96, 83));
		this.setLocation(655, 40);
		shape.addPoint(0, 3);
		shape.addPoint(71, 0);
		shape.addPoint(96, 44);
		shape.addPoint(62, 83);
		shape.addPoint(55, 44);
	}

}
