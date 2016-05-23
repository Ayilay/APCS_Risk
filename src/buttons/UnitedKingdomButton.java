package buttons;

import java.awt.Dimension;

public class UnitedKingdomButton extends TerritoryButton
{
	public UnitedKingdomButton(String s)
	{
		super(s);
		this.setSize(new Dimension(34, 27));
		this.setLocation(434, 88);
		shape.addPoint(24, 0);
		shape.addPoint(34, 20);
		shape.addPoint(16, 27);
		shape.addPoint(0, 22);
		shape.addPoint(1, 13);
	}
}
