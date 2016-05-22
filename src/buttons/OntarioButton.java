package buttons;

import java.awt.Dimension;

public class OntarioButton extends TerritoryButton
{
	public OntarioButton(String s)
	{
		super(s);
		this.setPreferredSize(new Dimension(68, 63));
		this.setSize(new Dimension(68, 63));
		this.setLocation(163, 83);
		shape.addPoint(21, 0);
		shape.addPoint(44, 2);
		shape.addPoint(66, 17);
		shape.addPoint(66, 27);
		shape.addPoint(59, 45);
		shape.addPoint(68, 53);
		shape.addPoint(44, 63);
		shape.addPoint(29, 42);
		shape.addPoint(19, 38);
		shape.addPoint(0, 38);
	}
}
