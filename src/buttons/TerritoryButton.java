package buttons;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import userInterface.GUIManager;

public abstract class TerritoryButton extends JButton
{
	private static final long serialVersionUID = 1954471042174703089L;

	protected Polygon shape;
	private String thisTerritory;

	public TerritoryButton(String s)
	{
		shape = new Polygon();
		thisTerritory = s;
		this.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				System.out.println("You clicked on " + getTerritory());
				GUIManager.lastTerritorySelected = getTerritory();
			}

		});
	}

	public String getTerritory()
	{
		return thisTerritory;
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

	@Override
	public boolean contains(int x, int y)
	{
		return shape.contains(x, y);
	}
}