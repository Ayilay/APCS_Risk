package card;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import territoryMap.TerritoryMap;

public class Card
{
	private String territory;
	private int stars;

	public Card(String ter, int value)
	{
		this.territory = ter;
		stars = value;
	}

	public int getValue()
	{
		return stars;
	}

	public String getTerritory()
	{
		return territory;
	}

	@Override
	public String toString()
	{
		return territory + " - " + stars;
	}

	@Override
	public boolean equals(Object obj)
	{
		if(!(obj instanceof Card))
			return false;
		Card other = (Card) obj;

		return territory.equals(other.getTerritory()) && stars == other.getValue();
	}
	
	public JPanel drawCard()
	{
		JPanel card = new JPanel();
		card.setBackground(Color.MAGENTA);
		card.setLayout(new BoxLayout(card,BoxLayout.Y_AXIS));

		String territory = this.getTerritory();
		int numStars = this.getValue();
		
		ImageIcon ter = new ImageIcon("territory icons/" + territory + ".png");
		ImageIcon val = new ImageIcon("territory icons/" + numStars + "stars.png");
		
		card.add(new JLabel(ter));
		card.add(new JLabel(val));
		
		card.setBorder(BorderFactory.createLineBorder(Color.black));
		return card;
		
	}
	
}
