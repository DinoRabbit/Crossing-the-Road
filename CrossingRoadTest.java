import java.lang.Object;

/*	This porgram takes a hypothetical board for Crossing the Road and attempts to determine the fewest
	number of moves needed to win, if winning is possible.*/

public class CrossingRoadTest
{
	public static void main(String [] args)
	{
		int [] cars;
		
		//as with the actual game, we need eleven arguments to represent the initial positions of each car
		if(args.length != 11)
		{
			System.out.println("Eleven arguments required, please try again.");
			System.exit(0);
		}
		int moves;
		cars = new int[11];
		//put our arguments into an array, throw an error on non-integer values
		for(int i = 0; i < args.length; i++)
		{
			try
			{
				cars[i] = Integer.parseInt(args[i]);
			}
			catch(NumberFormatException e)
			{
				System.out.println("Invalid number format, please enter integers.");
				System.exit(0);
			}
		}
		//find the path and output the results.
		moves = FindPath(0, 0, cars);
		if(moves == -1)
			System.out.println("This game is not possible.");
		else
			System.out.println("This game can be won in " +moves+ " moves.");
	}
	
	//A recursive function to determine the fewest moves needed to win, if possible.
	public static int FindPath(int pos, int moves, int [] cars)
	{
		int value;
		//Since arrays are pass by reference, we need to save a copy of the current cars array
		int [] positions = new int [11];
		for(int i = 0; i < positions.length; i++)
			positions[i] = cars[i];
		if(moves != 0)
		{
			if(pos < 0) //base case: ivalid move
				return -1;
			if(pos >= positions.length) //base case: path found, return number of moves
				return moves;
			//Calculate the positions of each car for the current turn
			for(int i = 0; i < positions.length; i++)
			{
				positions[i] = positions[i] - (i + 5);
				if(positions[i] <= 0 && i == pos) //base case: player dies, try a different path
					return -1;
			}
			//Set up a new car for each car that passed the player
			for(int i = 0; i < positions.length; i++)
				if(positions[i] < 0)
					positions[i] = positions[i] + (i + 18);
		}
		value = FindPath(pos + 1, moves + 1, positions); //attempt to move forward
		if(value == -1)
			value = FindPath(pos, moves + 1, positions); //recursive case: attempt to hold position
		else
			return value;
		if(value == -1)
			value = FindPath(pos - 1, moves + 1, positions); //recursive case: attempt to move backward
		else
			return value;
		return value; //base case: all moves failed, go back
	}
}