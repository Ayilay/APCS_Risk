package buttons;

import java.awt.Dimension;

public class NorthwestTerritoryButton extends TerritoryButton
{

	public NorthwestTerritoryButton(String s)
	{
		super(s);
		this.setPreferredSize(new Dimension(177, 46));
		this.setSize(new Dimension(177, 46));
		this.setLocation(87, 37);
		shape.addPoint(0, 46);
		shape.addPoint(33, 15);
		shape.addPoint(101, 0);
		shape.addPoint(177, 15);
		shape.addPoint(121, 44);
	}

}
