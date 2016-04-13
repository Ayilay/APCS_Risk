import java.util.HashSet;
import java.util.Set;

public class ContinentSet
{
	private String name;
	private HashSet<String> theSet;
	private int bonus;
	
	public ContinentSet(String name, HashSet<String> contents, int boost)
	{
		this.name = name;
		theSet = contents;
		bonus = boost;
	}
	
	public boolean containsAll(Set<String> other)
	{
		for(String s : theSet)
		{
			if(!other.contains(s))
			{
				return false;
			}
		}
		return true;
	}
}
