package buttons;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;

import javax.swing.JButton;

public abstract class TerritoryButton extends JButton
{
	private static final long serialVersionUID = 1954471042174703089L;
	protected Polygon shape;

	public TerritoryButton()
	{
		shape = new Polygon();
	}

	@Override
	public boolean contains(int x, int y)
	{
		return this.shape.contains(x, y);
	}

	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.setColor(Color.GRAY);
		g.fillPolygon(shape);
		g.drawPolygon(shape);
	}

	//@Override
	//public void paintBorder(Graphics g)
	//{
	//}

}