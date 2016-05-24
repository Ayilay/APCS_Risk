package buttons;

import java.awt.Dimension;

public class YakutskButton extends TerritoryButton
{

	public YakutskButton(String s)
	{
		super(s);
		this.setPreferredSize(new Dimension(138, 57));
		this.setSize(new Dimension(138, 57));
		this.setLocation(727, 39);
		shape.addPoint(0, 0);
		shape.addPoint(138, 13);
		shape.addPoint(92, 57);
		shape.addPoint(14, 26);
		shape.addPoint(0, 1);
		
	}

}
