package card;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
		card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));

		String territory = this.getTerritory();
		int numStars = this.getValue();

		ImageIcon ter = new ImageIcon("territory icons/" + territory + ".png");
		ImageIcon val = new ImageIcon("territory icons/" + numStars + "stars.png");

		Image img = ter.getImage();//rescale
		Image newImg = img.getScaledInstance(ter.getIconWidth() / 2, ter.getIconHeight() / 2, Image.SCALE_SMOOTH);
		ter = new ImageIcon(newImg);

		img = val.getImage();
		newImg = img.getScaledInstance(val.getIconWidth() / 2, val.getIconHeight() / 2, Image.SCALE_SMOOTH);
		val = new ImageIcon(newImg);

		card.add(new JLabel(ter));
		card.add(new JLabel(val));

		card.setBorder(BorderFactory.createLineBorder(Color.black));

		card.setSize(new Dimension(20, 20));
		return card;

	}

}
