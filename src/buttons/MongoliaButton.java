package buttons;

import java.awt.Dimension;

public class MongoliaButton extends TerritoryButton
{

	public MongoliaButton(String s)
	{
		super(s);
		this.setPreferredSize(new Dimension(153, 67));
		this.setSize(new Dimension(153, 67));
		this.setLocation(722, 107);
		shape.addPoint(104, 0);
		shape.addPoint(134, 20);
		shape.addPoint(141, 17);
		shape.addPoint(144, 24);
		shape.addPoint(139, 29);
		shape.addPoint(143, 45);
		shape.addPoint(139, 48);
		shape.addPoint(153, 63);
		shape.addPoint(143, 67);
		shape.addPoint(130, 50);
		shape.addPoint(101, 36);
		shape.addPoint(80, 54);
		shape.addPoint(0, 15);
		shape.addPoint(12, 7);
		shape.addPoint(26, 11);
		shape.addPoint(91, 12);
	}

}
