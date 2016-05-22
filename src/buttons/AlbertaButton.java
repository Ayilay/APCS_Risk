package buttons;

import java.awt.Dimension;

public class AlbertaButton extends TerritoryButton
{
	public AlbertaButton(String s)
	{
		super(s);
		this.setPreferredSize(new Dimension(89, 38));
		shape.addPoint(0, 1);
		shape.addPoint(89, 0);
		shape.addPoint(62, 38);
		shape.addPoint(5, 38);
		shape.addPoint(5, 5);
	}
}
