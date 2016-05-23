package buttons;

import java.awt.Dimension;

public class CubaButton extends TerritoryButton
{

	public CubaButton(String s)
	{
		super(s);
		this.setPreferredSize(new Dimension(56, 20));
		this.setSize(new Dimension(56, 20));
		this.setLocation(183, 215);
		shape.addPoint(0, 0);
		shape.addPoint(9, 0);
		shape.addPoint(56, 19);
		shape.addPoint(12, 20);
	}

}
