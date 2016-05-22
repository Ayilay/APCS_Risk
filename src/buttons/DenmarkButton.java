package buttons;

import java.awt.Dimension;

public class DenmarkButton extends TerritoryButton
{
	public DenmarkButton(String s)
	{
		super(s);
		this.setSize(new Dimension(5, 7));
		this.setLocation(487, 92);
		shape.addPoint(0, 0);
		shape.addPoint(5, 0);
		shape.addPoint(5, 7);
		shape.addPoint(0, 7);
	}
}
