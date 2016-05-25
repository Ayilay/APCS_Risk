package buttons;

import java.awt.Dimension;

public class UralButton extends TerritoryButton
{

	public UralButton(String s)
	{
		super(s);
		this.setPreferredSize(new Dimension(93, 86));
		this.setSize(new Dimension(93, 86));
		this.setLocation(624, 43);
		shape.addPoint(0, 9);
		shape.addPoint(30, 0);
		shape.addPoint(88, 49);
		shape.addPoint(78, 51);
		shape.addPoint(90, 64);
		shape.addPoint(83, 71);
		shape.addPoint(93, 82);
		shape.addPoint(88, 86);
		shape.addPoint(57, 59);
		shape.addPoint(10, 59);
	}

}
