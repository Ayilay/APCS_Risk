package buttons;

import java.awt.Dimension;

public class EasternEuropeButton extends TerritoryButton
{
	public EasternEuropeButton(String s)
	{
		super(s);
		this.setPreferredSize(new Dimension(109, 101));
		this.setSize(new Dimension(109, 101));
		this.setLocation(527, 53);
		shape.addPoint(0, 41);
		shape.addPoint(24, 79);
		shape.addPoint(54, 76);
		shape.addPoint(75, 101);
		shape.addPoint(86, 98);
		shape.addPoint(72, 64);
		shape.addPoint(85, 56);
		shape.addPoint(109, 60);
		shape.addPoint(93, 39);
		shape.addPoint(87, 14);
		shape.addPoint(98, 6);
		shape.addPoint(94, 1);
		shape.addPoint(17, 0);
		shape.addPoint(23, 26);
	}
}
