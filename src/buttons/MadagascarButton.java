package buttons;

import java.awt.Dimension;

public class MadagascarButton extends TerritoryButton
{

	public MadagascarButton(String s)
	{
		super(s);
		this.setPreferredSize(new Dimension(26, 47));
		this.setSize(new Dimension(26, 47));
		this.setLocation(602, 346);
		shape.addPoint(26, 0);
		shape.addPoint(12, 47);
		shape.addPoint(0, 45);
		shape.addPoint(6, 15);
	}

}
