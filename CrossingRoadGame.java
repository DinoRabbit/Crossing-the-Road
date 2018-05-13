import java.lang.*;
import java.util.Scanner;

/*  This is a game in whice the player is tasked with making it across the road without getting hit by any of the cars which are moving along the road.
	The Z, A, and Q keys are used to make on of three moves, forward, stay, or bacward.  Cars move forward a pre-determined number of spaces each turn
	which is based on which lane of the road they are in.  11 inputs are required which are the starting positions of each car (A 10 places it 10 spaces away from the players position)*/

public class CrossingRoadGame
{
	public static void main(String [] args)
	{
		//We need 11 arguments for the position of the cars
		if(args.length != 11)
		{
			System.out.println("Eleven arguments required, please try again.");
			System.exit(0);
		}
		
		//Put our inputs int an array, throw an error if a non-integer is encountered
		int [] input = new int[11];
		for(int i = 0; i < 11; i++)
		{
			try
			{
				input[i] = Integer.parseInt(args[i]);
			}
			catch(NumberFormatException e)
			{
				System.out.println("Invalid number format, please enter integers.");
				System.exit(0);
			}
		}
		Scanner reader = new Scanner(System.in);
		boolean play = true;
		boolean prompt;
		String turn = new String("");  //Stores players input
		int condition;  //Determine game state (2 = valid move, 0 = invalid move, 1 = player wins, anything else means the player has lost)
		Road theRoad = new Road(input);
		
		//Game logic, get players input, make the move, and determine outcome
		do
		{
			prompt = true;
			condition = 2;
			do
			{
				theRoad.displayRoad();
				System.out.println("Make a move(z - Down, a - Stay, q - Back)");
				turn = reader.next();
				turn = turn.toLowerCase();
				if(turn.equals("z"))
				{
					condition = theRoad.move(1);
					prompt = false;
				}
				else if(turn.equals("a"))
				{
					condition = theRoad.move(0);
					prompt = false;
				}
				else if(turn.equals("q"))
				{
					condition = theRoad.move(-1);
					prompt = false;
				}
				else
					System.out.println("Inapropriate input, try again.");
				if(condition == 0)
					System.out.println("Invalid move, try again.");
				else if(condition == 2)
					prompt = true;
				else
				{
					prompt = false;
					play = false;
				}
			}
			while(prompt);
		}
		while(play);
		
		//Output player Win/Loss
		if(condition == 1)
			System.out.println("You Win.");
		else
			theRoad.displayRoad();
			System.out.println("You Lose.");
	}
}