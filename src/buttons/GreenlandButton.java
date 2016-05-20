package buttons;

import java.awt.Dimension;

public class GreenlandButton extends TerritoryButton
{
	private static final long serialVersionUID = -4017492434666001404L;

	public GreenlandButton()
	{
		this.setPreferredSize(new Dimension(70, 90));
		shape.addPoint(0, 5);
		shape.addPoint(70, 0);
		shape.addPoint(10, 90);
		shape.addPoint(20, 20);
	}
}
