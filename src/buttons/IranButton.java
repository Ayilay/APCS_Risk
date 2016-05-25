package buttons;

import java.awt.Dimension;

public class IranButton extends TerritoryButton
{

	public IranButton(String s)
	{
		super(s);
		this.setPreferredSize(new Dimension(60, 53));
		this.setSize(new Dimension(60, 53));
		this.setLocation(601, 155);
		shape.addPoint(0, 0);
		shape.addPoint(53, 13);
		shape.addPoint(60, 53);
		shape.addPoint(16, 31);
	}

}
