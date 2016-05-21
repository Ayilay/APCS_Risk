package buttons;

import java.awt.Dimension;

public class GreenlandButton extends TerritoryButton
{
	private static final long serialVersionUID = -4017492434666001404L;

	public GreenlandButton()
	{
		this.setPreferredSize(new Dimension(111, 63));
		shape.addPoint(0, 9);
		shape.addPoint(111, 0);
		shape.addPoint(81, 35);
		shape.addPoint(48, 46);
		shape.addPoint(32, 63);
		shape.addPoint(18, 54);
		shape.addPoint(31, 33);
		shape.addPoint(18, 13);
	}
}
