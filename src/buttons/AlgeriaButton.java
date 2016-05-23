package buttons;

import java.awt.Dimension;

public class AlgeriaButton extends TerritoryButton
{

	public AlgeriaButton(String s)
	{
		super(s);
		this.setPreferredSize(new Dimension(63, 65));
		this.setSize(new Dimension(63, 65));
		this.setLocation(434, 165);
		shape.addPoint(24, 4);
		shape.addPoint(59, 0);
		shape.addPoint(56, 41);
		shape.addPoint(63, 48);
		shape.addPoint(34, 65);
		shape.addPoint(0, 38);
		shape.addPoint(0, 30);
		shape.addPoint(13, 26);
		shape.addPoint(14, 19);
		shape.addPoint(28, 19);
	}

}
