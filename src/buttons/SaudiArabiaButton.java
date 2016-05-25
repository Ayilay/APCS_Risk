package buttons;

import java.awt.Dimension;

public class SaudiArabiaButton extends TerritoryButton
{

	public SaudiArabiaButton(String s)
	{
		super(s);
		this.setPreferredSize(new Dimension(85, 162));
		this.setSize(new Dimension(85, 162));
		this.setLocation(572, 192);
		shape.addPoint(0, 0);
		shape.addPoint(47, 5);
		shape.addPoint(85, 28);
		shape.addPoint(35, 62);
	}

}
