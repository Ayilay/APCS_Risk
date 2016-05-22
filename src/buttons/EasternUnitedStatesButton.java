package buttons;

import java.awt.Dimension;

public class EasternUnitedStatesButton extends TerritoryButton
{

	public EasternUnitedStatesButton(String s)
	{
		super(s);
		this.setPreferredSize(new Dimension(142, 87));
		shape.addPoint(52, 0);
		shape.addPoint(75, 2);
		shape.addPoint(93, 25);
		shape.addPoint(139, 6);
		shape.addPoint(142, 14);
		shape.addPoint(79, 66);
		shape.addPoint(77, 87);
		shape.addPoint(65, 67);
		shape.addPoint(43, 69);
		shape.addPoint(24, 83);
		shape.addPoint(0, 61);
		shape.addPoint(10, 61);
		shape.addPoint(12, 44);
		shape.addPoint(44, 44);
	}

}
