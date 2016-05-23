package buttons;

import java.awt.Dimension;

public class HawaiiButton extends TerritoryButton
{

	public HawaiiButton(String s)
	{
		super(s);
		this.setPreferredSize(new Dimension(10, 20));
		this.setSize(new Dimension(10, 20));
		this.setLocation(12, 178);
		shape.addPoint(0, 0);
		shape.addPoint(10, 0);
		shape.addPoint(10, 10);
		shape.addPoint(0, 10);
	}

}
