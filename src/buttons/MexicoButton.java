package buttons;

import java.awt.Dimension;

public class MexicoButton extends TerritoryButton
{

	public MexicoButton(String s)
	{
		super(s);
		// TODO Auto-generated constructor stub
		this.setPreferredSize(new Dimension(84, 68));
		shape.addPoint(0, 0);
		shape.addPoint(55, 26);
		shape.addPoint(63, 50);
		shape.addPoint(84, 41);
		shape.addPoint(82, 51);
		shape.addPoint(68, 68);
		shape.addPoint(18, 41);
		shape.addPoint(11, 35);
	}

}
