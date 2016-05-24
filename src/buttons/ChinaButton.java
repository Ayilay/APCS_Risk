package buttons;

import java.awt.Dimension;

public class ChinaButton extends TerritoryButton
{
	public ChinaButton(String s)
	{
		super(s);
		this.setPreferredSize(new Dimension(165, 101));
		this.setSize(new Dimension(165, 101));
		this.setLocation(692, 122);
		shape.addPoint(31, 0);
		shape.addPoint(97, 42);
		shape.addPoint(132, 23);
		shape.addPoint(143, 33);
		shape.addPoint(145, 42);
		shape.addPoint(156, 42);
		shape.addPoint(151, 51);
		shape.addPoint(165, 70);
		shape.addPoint(155, 95);
		shape.addPoint(126, 101);
		shape.addPoint(116, 93);
		shape.addPoint(98, 97);
		shape.addPoint(93, 81);
		shape.addPoint(78, 71);
		shape.addPoint(69, 77);
		shape.addPoint(22, 62);
		shape.addPoint(21, 48);
		shape.addPoint(5, 42);
		shape.addPoint(0, 34);
		shape.addPoint(18, 21);
		shape.addPoint(14, 15);
	}
}
