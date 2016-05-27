package buttons;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import territoryMap.TerritoryMap;
import userInterface.GUIManager;

public abstract class TerritoryButton extends JButton
{
	private static final long serialVersionUID = 1954471042174703089L;

	private static GUIManager guiManager;

	protected Polygon shape;
	private String thisTerritory;

	public static void initGUIManager(GUIManager gui)
	{
		guiManager = gui;
	}

	public TerritoryButton(String s)
	{
		super();
		this.setOpaque(false);
		this.setContentAreaFilled(false);
		this.setForeground(new Color(0, 0, 0, 0));

		shape = new Polygon();
		thisTerritory = s;

		this.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				GUIManager.lastTerritorySelected = getTerritory();
				System.out.println("You clicked: " + GUIManager.lastTerritorySelected);
			}
		});

		this.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseEntered(MouseEvent e)
			{
				TerritoryButton.this.setForeground(Color.GREEN);
				TerritoryButton.this.guiManager.displayTerritoryStats(TerritoryButton.this.thisTerritory);
			}
			@Override
			public void mouseExited(MouseEvent e)
			{
				updateColor();
				TerritoryButton.this.guiManager.displayTerritoryStats(null);
			}
		});

		this.setToolTipText(s);
	}

	public String getTerritory()
	{
		return thisTerritory;
	}

	@Override
	public void paintBorder(Graphics g)
	{
		Graphics2D graphics = (Graphics2D) g;
		graphics.draw(shape);
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

	public void updateColor()
	{
		if(TerritoryMap.get(thisTerritory).getOccupier() == null)
		{
			this.setForeground(new Color(0, 0, 0, 0));
		}
		else
		{
			this.setForeground(TerritoryMap.get(thisTerritory).getOccupier().getColor());
		}
	}
}