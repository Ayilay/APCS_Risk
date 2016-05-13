package battle;
/*
 * This program rolls the specified number of dice and records those rolls
 * in an array. It also keeps track of the highest roll.
 * Author: Ethan Yao
 */
public class Die_Roll
{
	//stores the die rolls
	private int[] rolls;

	/**
	 * Constructor: Takes in an integer value that is the number of times the
	 * dice should be rolled and "rolls" a dice, storing values into an array
	 * and keeping track of the highest roll.
	 *
	 * @param numTimes: Number of dice to be rolled.
	 */
	public Die_Roll(int numTimes)
	{
		rolls = new int[numTimes];
		int[] tempArray = new int[numTimes];
		for(int i = 0; i < numTimes; i++)
		{
			int temp = (int)(Math.random() * 6 + 1);
			tempArray[i] = temp;
		}
		rolls = sort(tempArray);
	}

	/*
	 * Returns the int array
	 */
	public int[] getRolls()
	{
		return rolls;
	}

	/*
	 * An easy insertion sort. I use this because we will only be rolling up to
	 * 3 die twice so it is relatively efficient.
	 * Reverses the order of the array at the end
	 * so the highest value will be first.
	 */
	public static int[] sort(int[] input)
	{

		int temp;
		// insertion sort
		for(int i = 1; i < input.length; i++)
		{
			for(int j = i; j > 0; j--)
			{
				if(input[j] < input[j - 1])
				{
					temp = input[j];
					input[j] = input[j - 1];
					input[j - 1] = temp;
				}
			}
		}
		// reverse the array
		for(int i = 0; i < input.length / 2; i++)
		{
			int t = input[i];
			input[i] = input[input.length - i - 1];
			input[input.length - i - 1] = t;
		}
		return input;
	}

}
