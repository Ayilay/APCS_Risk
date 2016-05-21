package buttons;

import java.awt.Dimension;

public class OntarioButton extends TerritoryButton
{
	public OntarioButton(String s)
	{
		super(s);
		this.setPreferredSize(new Dimension(65, 58));
		shape.addPoint(19, 0);
		shape.addPoint(43, 0);
		shape.addPoint(65, 18);
		shape.addPoint(56, 58);
		shape.addPoint(17, 36);
		shape.addPoint(0, 39);
	}
}
