package buttons;

import java.awt.Dimension;

public class WesternUnitedStatesButton extends TerritoryButton
{

	public WesternUnitedStatesButton(String s)
	{
		super(s);
		this.setPreferredSize(new Dimension(91, 59));
		shape.addPoint(22, 0);
		shape.addPoint(91, 0);
		shape.addPoint(85, 42);
		shape.addPoint(57, 43);
		shape.addPoint(50, 59);
		shape.addPoint(6, 59);
		shape.addPoint(0, 27);
		
	}

}
