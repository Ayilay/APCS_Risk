package card;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import userInterface.GUIManager;

public class Card
{
	private String territory;
	private int stars;
	private JPanel card;

	public Card(String ter, int value)
	{
		this.territory = ter;
		stars = value;
	}
	public Card getCard()
	{
		return this;
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
		card = new JPanel();
		card.setBackground(Color.WHITE);
		card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));

		String territory = this.getTerritory();
		int numStars = this.getValue();

		ImageIcon ter = null;
		ImageIcon val = null;
		try
		{
			ter = new ImageIcon(ImageIO.read(getClass().getResource("/resources/" + territory + ".png")));
			val = new ImageIcon(ImageIO.read(getClass().getResource("/resources/" + numStars + "stars.png")));
		}
		catch(Exception e)
		{
			System.err.println("Failed in reading card icon for " + territory);
		}


		Image img = ter.getImage();//rescale
		Image newImg = img.getScaledInstance(ter.getIconWidth() / 2, ter.getIconHeight() / 2, Image.SCALE_SMOOTH);
		ter = new ImageIcon(newImg);

		img = val.getImage();
		newImg = img.getScaledInstance(val.getIconWidth() / 2, val.getIconHeight() / 2, Image.SCALE_SMOOTH);
		val = new ImageIcon(newImg);

		JLabel name = new JLabel(territory);
		name.setForeground(Color.BLACK);

		card.add(name);
		card.add(new JLabel(ter));
		card.add(new JLabel(val));

		card.setBorder(BorderFactory.createLineBorder(Color.BLACK));


		JButton btn = new JButton("Use");
		btn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("You clicked on card: " + getCard().toString());
				card.setBackground(Color.GREEN);
				GUIManager.lastCardSelected = getCard();
			}

		});
		card.add(btn);
		return card;

	}

}
