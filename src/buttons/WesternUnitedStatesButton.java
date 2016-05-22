package buttons;

import java.awt.Dimension;

public class WesternUnitedStatesButton extends TerritoryButton
{

	public WesternUnitedStatesButton(String s)
	{
		super(s);
		this.setPreferredSize(new Dimension(95, 64));
		this.setSize(new Dimension(95, 64));
		this.setLocation(71, 121);
		shape.addPoint(26, 0);
		shape.addPoint(95, 0);
		shape.addPoint(90, 43);
		shape.addPoint(59, 44);
		shape.addPoint(53, 61);
		shape.addPoint(38, 62);
		shape.addPoint(36, 64);
		shape.addPoint(27, 64);
		shape.addPoint(19, 59);
		shape.addPoint(11, 59);
		shape.addPoint(4, 53);
		shape.addPoint(0, 30);
		shape.addPoint(16, 10);
		shape.addPoint(18, 3);
	}

}
