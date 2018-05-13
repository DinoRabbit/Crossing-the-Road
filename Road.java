//This class goes with the CrossingRoadGame.java

public class Road
{
	int pos;
	int [] cars = new int [11];
	char[][] theRoad = new char[11][60];
	
	//Constructor sets up the two dimensional array that is the game board
	//@ = player, $ = Car's Position, + = Car's position next turn, - = empty space
	public Road(int [] positions)
	{
		int update;
		pos = 0;
		cars = positions;
		for(int i = 0; i < 11; i++)
		{
			for(int j = 0; j < 60; j++)
			{
				if(i == 0 && j == 0)
					theRoad[i][j] = '@';
				else if(cars[i] == j)
				{
					theRoad[i][j] = '$';
					if(cars[i] - (i + 5) >= 0)
					{
						update = cars[i] - (i+5);
						if(theRoad[i][update] != '@')
							theRoad[i][update] = '+';
					}
					if(cars[i] + (i + 18) < 60)
					{
						update = cars[i] + (i + 18);
						theRoad[i][update] = '$';
						update = update - (i + 5);
						theRoad[i][update] = '+';
					}
				}
				else if(theRoad[i][j] != '$' && theRoad[i][j] != '+')
					theRoad[i][j] = '-';
			}
		}
	}
	
	//Function to perform a player move
	public int move(int x)
	{
		theRoad[pos][0] = '-';
		pos = pos + x;
		if(pos < 0) //invalid, plyaer position in off the board
		{
			pos = 0;
			theRoad[pos][0] = '@';
			return 0;
		}
		else if(cars[pos] - (pos + 5) <= 0)  //player dies
		{
			theRoad[pos][0] = 'X';
			return -1;
		}
		else if(pos > 11) //player wins
			return 1;
		else  //valid move that does not result in a Win/Loss
		{
			//We need to move the player up as well as reposition all of the cars
			for(int i = 0; i < 11; i++)
			{
				theRoad[i][cars[i]] = '-';
				theRoad[i][cars[i] + (i + 18)] = '-';
				cars[i] = cars[i] - (i + 5);
				if(cars[i] < 0)
					cars[i] = cars[i] + (i + 18);
				theRoad[i][cars[i]] = '$';
				if(cars[i] - (i + 5) >= 0)
					theRoad[i][cars[i] - (i + 5)] = '+';
				if(cars[i] + (i + 18) < 60)
				{
					theRoad[i][cars[i] + (i + 18)] = '$';
					theRoad[i][cars[i] + (i + 18) - (i + 5)] = '+';
				}
			}
			theRoad[pos][0] = '@';
			return 2;
		}
	}
	//Function that outputs the current board state
	public void displayRoad()
	{
		for(int i = 0; i < 11; i++)
		{
			for(int j = 0; j < 60; j++)
				System.out.print(theRoad[i][j] + " ");
			System.out.print("\n");
		}
		System.out.println();
		System.out.println();
	}
}