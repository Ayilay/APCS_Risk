package buttons;

import java.awt.Dimension;

public class AlaskaButton extends TerritoryButton
{

	public AlaskaButton(String s)
	{
		super(s);
		this.setPreferredSize(new Dimension(105, 44));
		shape.addPoint(105, 0);
		shape.addPoint(39, 5);
		shape.addPoint(0, 44);
		shape.addPoint(67, 31);
	}

}
