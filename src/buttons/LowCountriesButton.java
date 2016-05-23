package buttons;

import java.awt.Dimension;

public class LowCountriesButton extends TerritoryButton
{

	public LowCountriesButton(String s)
	{
		super(s);
		this.setPreferredSize(new Dimension(11, 15));
		this.setSize(new Dimension(11, 15));
		this.setLocation(471, 105);
		shape.addPoint(9, 15);
		shape.addPoint(0, 8);
		shape.addPoint(11, 0);
	}

}
