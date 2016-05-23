package buttons;

import java.awt.Dimension;

public class VenezuelaButton extends TerritoryButton
{

	public VenezuelaButton(String s)
	{
		super(s);
		this.setPreferredSize(new Dimension(93, 62));
		this.setSize(new Dimension(93, 62));
		this.setLocation(190, 255);
		shape.addPoint(0, 40);
		shape.addPoint(5, 29);
		shape.addPoint(4, 20);
		shape.addPoint(13, 7);
		shape.addPoint(24, 0);
		shape.addPoint(28, 0);
		shape.addPoint(46, 8);
		shape.addPoint(54, 8);
		shape.addPoint(68, 16);
		shape.addPoint(79, 24);
		shape.addPoint(87, 24);
		shape.addPoint(93, 30);
		shape.addPoint(88, 37);
		shape.addPoint(79, 37);
		shape.addPoint(66, 41);
		shape.addPoint(62, 35);
		shape.addPoint(65, 30);
		shape.addPoint(61, 27);
		shape.addPoint(54, 31);
		shape.addPoint(49, 33);
		shape.addPoint(51, 38);
		shape.addPoint(44, 43);
		shape.addPoint(39, 38);
		shape.addPoint(31, 39);
		shape.addPoint(31, 62);
		shape.addPoint(29, 61);
		shape.addPoint(27, 54);
		shape.addPoint(20, 54);
		shape.addPoint(14, 46);
	}

}
