package buttons;

import java.awt.Dimension;

public class PolandButton extends TerritoryButton
{

	public PolandButton(String s)
	{
		super(s);
		this.setPreferredSize(new Dimension(26, 20));
		this.setSize(new Dimension(26, 20));
		this.setLocation(506, 99);
		shape.addPoint(0, 4);
		shape.addPoint(18, 0);
		shape.addPoint(26, 16);
		shape.addPoint(22, 20);
		shape.addPoint(11, 20);
		shape.addPoint(0, 13);
	}

}
