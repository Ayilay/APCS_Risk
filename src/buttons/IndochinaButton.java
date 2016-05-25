package buttons;

import java.awt.Dimension;

public class IndochinaButton extends TerritoryButton
{

	public IndochinaButton(String s)
	{
		super(s);
		this.setPreferredSize(new Dimension(61, 99));
		this.setSize(new Dimension(61, 99));
		this.setLocation(765, 197);
		shape.addPoint(13, 0);
		shape.addPoint(27, 25);
		shape.addPoint(44, 18);
		shape.addPoint(51, 24);
		shape.addPoint(47, 33);
		shape.addPoint(61, 53);
		shape.addPoint(61, 63);
		shape.addPoint(48, 72);
		shape.addPoint(46, 99);
		shape.addPoint(39, 96);
		shape.addPoint(25, 74);
		shape.addPoint(19, 44);
		shape.addPoint(0, 26);
	}

}
