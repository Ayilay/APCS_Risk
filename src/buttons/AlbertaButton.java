package buttons;

import java.awt.Dimension;

public class AlbertaButton extends TerritoryButton
{
	public AlbertaButton(String s)
	{
		super(s);
		this.setPreferredSize(new Dimension(97, 44));
		this.setSize(new Dimension(97, 44));
		this.setLocation(87, 77);
		shape.addPoint(0, 5);
		shape.addPoint(97, 5);
		shape.addPoint(74, 48);
		shape.addPoint(11, 49);
		shape.addPoint(3, 35);
		shape.addPoint(5, 22);
		shape.addPoint(9, 22);
		shape.addPoint(10, 12);
		shape.addPoint(6, 7);
		shape.addPoint(1, 8);
	}
}
