package buttons;

import java.awt.Dimension;
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
	public void paintBorder(Graphics g)
	{
		((Graphics2D) g).draw(shape);
	}

	@Override
	public void paintComponent(Graphics g)
	{
		((Graphics2D) g).fill(shape);
	}

	//@Override
	//public Dimension getPreferredSize()
	//{
	//	return new Dimension(50, 90);
	//}

	@Override
	public boolean contains(int x, int y)
	{
		return shape.contains(x, y);
	}
}