package buttons;

import java.awt.Dimension;

public class KazakhstanButton extends TerritoryButton
{

	public KazakhstanButton(String s)
	{
		super(s);
		this.setPreferredSize(new Dimension(109, 70));
		this.setSize(new Dimension(109, 70));
		this.setLocation(602, 99);
		shape.addPoint(0, 18);
		shape.addPoint(59, 0);
		shape.addPoint(109, 31);
		shape.addPoint(56, 70);
	}

}
