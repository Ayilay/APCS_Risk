package buttons;

import java.awt.Dimension;

public class EasternAustraliaButton extends TerritoryButton
{
	public EasternAustraliaButton(String s)
	{
		super(s);
		this.setPreferredSize(new Dimension(86, 88));
		this.setSize(new Dimension(86, 88));
		this.setLocation(834, 352);
		shape.addPoint(56, 4);
		shape.addPoint(46, 44);
		shape.addPoint(86, 47);
		shape.addPoint(71, 88);
		shape.addPoint(50, 67);
		shape.addPoint(0, 79);
		shape.addPoint(0, 31);
		shape.addPoint(29, 21);
		shape.addPoint(48, 0);
	}
}
