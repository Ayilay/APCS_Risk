package buttons;

import java.awt.Dimension;

public class TurkeyButton extends TerritoryButton
{

	public TurkeyButton(String s)
	{
		super(s);
		this.setPreferredSize(new Dimension(74, 51));
		this.setSize(new Dimension(74, 51));
		this.setLocation(545, 146);
		shape.addPoint(0, 0);
		shape.addPoint(46, 3);
		shape.addPoint(74, 51);
		shape.addPoint(46, 39);
		shape.addPoint(28, 46);
		shape.addPoint(29, 21);
		shape.addPoint(2, 18);
	}

}
