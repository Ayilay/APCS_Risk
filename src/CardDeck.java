import java.util.*;
import java.math.*;
public class CardDeck 
{
	private Queue<Card> cards = new LinkedList<Card>();
	private String[] rearranged;
	
	public void init(Set<String> s)
	{
		rearranged = (String[])s.toArray();
		rearranged = shuffle(rearranged);
		
		for(int i = 0; i < s.size(); i++)
		{
			Card card = new Card(rearranged[i], (int)(2*Math.random()+1));
			cards.add(card);
		}
	}
	
	public static String[] shuffle(String[] t)
	{
		
		for (int i = t.length - 1; i > 0; i--)
	    {
			int rand = (int)((t.length - 1) * Math.random());
			
			String temp = t[i];
			t[rand] = t[i];
			t[i] = temp;
	    }
		return t;
	}
	
	public Card deal()
	{
		return cards.poll();
	}
}
