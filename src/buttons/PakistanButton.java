package buttons;

import java.awt.Dimension;

public class PakistanButton extends TerritoryButton
{

	public PakistanButton(String s)
	{
		super(s);
		this.setPreferredSize(new Dimension(309, 48));
		this.setSize(new Dimension(309, 48));
		this.setLocation(660, 164);
		shape.addPoint(0, 27);
		shape.addPoint(19, 28);
		shape.addPoint(39, 0);
		shape.addPoint(55, 10);
		shape.addPoint(21, 48);
		shape.addPoint(3, 42);
	}

}
