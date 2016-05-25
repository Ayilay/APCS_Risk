package buttons;

import java.awt.Dimension;

public class JapanButton extends TerritoryButton
{

	public JapanButton(String s)
	{
		super(s);
		this.setPreferredSize(new Dimension(30, 53));
		this.setSize(new Dimension(30, 53));
		this.setLocation(878, 133);
		shape.addPoint(14, 0);
		shape.addPoint(29, 7);
		shape.addPoint(30, 36);
		shape.addPoint(5, 53);
		shape.addPoint(0, 45);
		shape.addPoint(21, 26);
		shape.addPoint(13, 0);
	}

}
