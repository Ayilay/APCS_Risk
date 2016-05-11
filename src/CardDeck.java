import java.util.*;

public class CardDeck
{
	private Queue<Card> cards = new LinkedList<Card>();

	public CardDeck(Set<String> s)
	{
		String[] rearranged = s.toArray(new String[s.size()]);
		rearranged = shuffle(rearranged);

		for(int i = 0; i < s.size(); i++)
		{
			Card card = new Card((String)rearranged[i], (int)(2 * Math.random() + 1));
			cards.add(card);
		}
	}

	public static String[] shuffle(String[] t)
	{
		for(int i = t.length - 1; i > 0; i--)
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
		if(cards.size() == 0)
			return null;

		return cards.poll();
	}

	public Queue<Card> getDeck()
	{
		return cards;
	}

}
