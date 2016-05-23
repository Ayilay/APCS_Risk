package buttons;

import java.awt.Dimension;

public class SpainButton extends TerritoryButton
{

	public SpainButton(String s)
	{
		super(s);
		this.setPreferredSize(new Dimension(37, 25));
		this.setSize(new Dimension(37, 25));
		this.setLocation(433, 140);
		shape.addPoint(1, 0);
		shape.addPoint(37, 5);
		shape.addPoint(22, 25);
		shape.addPoint(0, 24);
	}

}
