package userInterface;


import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.WindowEvent;
import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.*;

import card.Card;
import card.CardDeck;
import main.Player;
import territoryMap.TerritoryMap;

public class GUI_Cards
{

	//Test rendering cards.
	public static void main(String[] args)
	{
		JFrame frame = new JFrame();
		frame.setTitle("Card Test");
		frame.setLayout(new FlowLayout());

		frame.setSize(350, 400);
		frame.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.RED));
		
		JPanel p = new JPanel(new GridBagLayout());
		

		frame.setSize(600, 350);

		TerritoryMap.init();
		CardDeck deck = new CardDeck(TerritoryMap.getAllTerritories());
		Card card = deck.deal();
		String territory = card.getTerritory();
		int numStars = card.getValue();

		
		ImageIcon ter = new ImageIcon("territory icons/" + territory + ".png");
		ImageIcon val = new ImageIcon("territory icons/" + numStars + "stars.jpg");
		
		int height = val.getIconHeight();
		int width = val.getIconWidth();
		
		Image img = val.getImage();
		Image newImg = img.getScaledInstance((int)(width*0.8),(int)(height*0.8), java.awt.Image.SCALE_SMOOTH);
		val = new ImageIcon(newImg);
		
		JLabel lab = new JLabel(territory);
		lab.setFont(new Font(lab.getFont().getFontName(), Font.BOLD, 18));
		GridBagConstraints c = new GridBagConstraints();
		
		JLabel scaledTer = new JLabel(ter);
		scaledTer.setPreferredSize(new Dimension(5,5));
		
		c.gridx = 0;
		c.gridy = 0;
		p.add(lab,c);
		c.gridx = 0;
		c.gridy = 1;
		p.add(new JLabel(ter), c);
		c.gridx = 0;
		c.gridy = 2;
		p.add(new JLabel(val), c);
		
		
		frame.add(p);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.add(new JLabel(new ImageIcon("territory icons/" + numStars + "stars.jpg")));


		frame.setVisible(true);

		frame.addWindowListener(new java.awt.event.WindowAdapter()
		{
			public void windowClosing(WindowEvent evt)
			{
				System.exit(0);
			}
		});

	}
	public static Card selectCard(Player p)
	{
		System.err.println("Unimplemented Feature"); // TODO Auto-generated method stub
		return null;
	}


	public static boolean promptUseCard()
	{
		// TODO Auto-generated method stub
		return false;
	}


	public static boolean promptTradeCard()
	{
		// TODO Auto-generated method stub
		return false;
	}
}
