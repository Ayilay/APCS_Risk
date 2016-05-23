package buttons;

import java.awt.Dimension;

public class MexicoButton extends TerritoryButton
{

	public MexicoButton(String s)
	{
		super(s);
		this.setPreferredSize(new Dimension(86, 68));
		this.setSize(new Dimension(86, 68));
		this.setLocation(83, 181);
		shape.addPoint(0, 0);
		shape.addPoint(7, 0);
		shape.addPoint(15, 4);
		shape.addPoint(24, 4);
		shape.addPoint(26, 2);
		shape.addPoint(31, 2);
		shape.addPoint(38, 12);
		shape.addPoint(43, 9);
		shape.addPoint(50, 21);
		shape.addPoint(56, 24);
		shape.addPoint(52, 33);
		shape.addPoint(52, 41);
		shape.addPoint(58, 51);
		shape.addPoint(67, 52);
		shape.addPoint(74, 43);
		shape.addPoint(82, 40);
		shape.addPoint(86, 42);
		shape.addPoint(83, 51);
		shape.addPoint(72, 52);
		shape.addPoint(67, 68);
		shape.addPoint(22, 45);
		shape.addPoint(24, 38);
		shape.addPoint(6, 4);
		shape.addPoint(12, 34);
		shape.addPoint(10, 34);
	}

}
