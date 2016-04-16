import java.util.*;
import java.math.*;
public class CardDeck 
{
	private static Queue<Card> cards = new LinkedList<Card>();
		
	public static void init(Set<String> s)
	{
		Object[] rearranged = s.toArray();
		rearranged = shuffle(rearranged);
		
		for(int i = 0; i < s.size(); i++)
		{
			Card card = new Card((String)rearranged[i], (int)(2*Math.random()+1));
			cards.add(card);
		}
	}
	
	public static Object[] shuffle(Object[] t)
	{
		
		for (int i = t.length - 1; i > 0; i--)
	    {
			int rand = (int)((t.length - 1) * Math.random());
			
			String temp = (String)t[i];
			t[rand] = t[i];
			t[i] = temp;
	    }
		return t;
	}
	
	public static Card deal()
	{
		return cards.poll();
	}
}
