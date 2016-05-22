package buttons;

import java.awt.Dimension;

public class NorthwestTerritoryButton extends TerritoryButton
{

	public NorthwestTerritoryButton(String s)
	{
		super(s);
		this.setPreferredSize(new Dimension(179, 48));
		shape.addPoint(36, 15);
		shape.addPoint(104, 0);
		shape.addPoint(179, 17);
		shape.addPoint(123, 48);
		shape.addPoint(0, 46);
	}

}
