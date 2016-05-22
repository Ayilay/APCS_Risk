package buttons;

import java.awt.Dimension;

public class AlaskaButton extends TerritoryButton
{

	public AlaskaButton(String s)
	{
		super(s);
		this.setPreferredSize(new Dimension(109, 52));
		this.setSize(new Dimension(109, 52));
		this.setLocation(11, 42);
		shape.addPoint(109, 10);
		shape.addPoint(89, 5);
		shape.addPoint(73, 5);
		shape.addPoint(53, 10);
		shape.addPoint(42, 13);
		shape.addPoint(42, 19);
		shape.addPoint(30, 20);
		shape.addPoint(25, 24);
		shape.addPoint(33, 27);
		shape.addPoint(14, 33);
		shape.addPoint(11, 44);
		shape.addPoint(16, 46);
		shape.addPoint(0, 55);
		shape.addPoint(40, 40);
		shape.addPoint(57, 39);
		shape.addPoint(81, 48);
		shape.addPoint(82, 57);
		shape.addPoint(86, 57);
		shape.addPoint(83, 42);
		shape.addPoint(86, 41);
		shape.addPoint(73, 38);
	}

}
