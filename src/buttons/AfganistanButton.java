package buttons;

import java.awt.Dimension;

public class AfganistanButton extends TerritoryButton
{
	public AfganistanButton(String s)
	{
		super(s);
		this.setPreferredSize(new Dimension(36, 25));
		this.setSize(new Dimension(36, 25));
		this.setLocation(658, 165);
		shape.addPoint(0, 8);
		shape.addPoint(36, 0);
		shape.addPoint(35, 8);
		shape.addPoint(19, 25);
		shape.addPoint(5, 25);
	}
}
