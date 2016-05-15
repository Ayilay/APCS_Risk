package userInterface;


import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

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
		frame.setSize(600, 350);
		TerritoryMap.init();
		CardDeck deck = new CardDeck(TerritoryMap.getAllTerritories());	
		Card card = deck.deal();
		String territory = card.getTerritory();
		frame.add(new JLabel(new ImageIcon("territory icons/" + territory + ".png")));
		int numStars = card.getValue();
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
