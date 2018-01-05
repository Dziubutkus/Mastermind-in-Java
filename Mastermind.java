/*************************************************************************************
| Mastermind Board Game			
| Description: A mastermind board game written in Java
| Due: 10/27/2017
| Submitted: 10/27/2017
| Instructor: Victoria Eisele
| Author: Dziugas Butkus
| Part 5: Place the guesses and answers into a class and create an array of the class
 *************************************************************************************/
import java.util.Scanner;
public class Mastermind
{
	
	public static Scanner input = new Scanner(System.in);
	
	public static void main( String[ ] args )
	{
		Turns[] turn = new Turns[10];
		// Call the constructor for each object
		for(int i = 0; i < 10; i++)
		{
			turn[i] = new Turns();
		}
		
		char[] generatedColors = new char[4]; 
		//char[][] playersGuess = new char[10][4];
		int numberOfGuesses = 0;
		//char[][] placement = new char[10][4];
		int placementNum = 0; // Number of correct colors. Declared here because if placementNum == 4 ? playGame == false
		boolean playGame = true; // Game is run while true
		//char playAgain; // Answer if user wants to play again
		
		if(playGame == true)
		{
			// Create borders and squares
			makeBoard(turn, false, numberOfGuesses);
			// Generate 4 different colors 
			generateColors(generatedColors);
		}
		
		while(playGame && numberOfGuesses < 10)
		{
			for(int i = 0; i < 4; i++)
			{
				System.out.println(generatedColors[i]);
			}
			// Player's guess
			getPlayersGuess(turn[numberOfGuesses].getPlayersGuess(), numberOfGuesses);
			
			// Check placement
			placementNum = checkPlacement(turn[numberOfGuesses].getPlacement(), turn[numberOfGuesses].getPlayersGuess(), generatedColors, numberOfGuesses);
			//System.out.println(turn[0].getPlacement()[0]);
			// Redraw the board
			makeBoard(turn, true, numberOfGuesses);
			numberOfGuesses++;
			
			if(placementNum == 4)
			{
				playGame = false;
			}
		}
		// End game prompt
		endGame(numberOfGuesses, playGame, generatedColors);
		
		/* SHOULD BE INSIDE WHILE LOOP
		System.out.println ( "Play again? (y or n)" );
		playAgain = input.next ( ).charAt ( 0 );
		if(playAgain == 'y')
		{
			playGame = true;
			numberOfGuesses = 0;
		}
		*/
		
	}
	
	/**********************************************
	 * makeBoard()
	 * Calls makeSolidLine() and makeSides() to create a board
	 * @parameters: none
	 * @return: none
	 **********************************************/
	public static void makeBoard(Turns[] turn, boolean putGuess, int numberOfGuesses)
	{
		int numberOfRow = 1;
		for(int i = 0; i < 10; i++)
		{
			makeSolidLine();
			makeSides(numberOfRow, turn, putGuess, numberOfGuesses, i);
			numberOfRow++;
		}
		makeSolidLine(); // To have a bottom border
	}
	
	/**********************************************
	 * makeSolidLine()
	 * Outputs a solid line of starts
	 * @parameters: none
	 * @return: none
	 **********************************************/
	public static void makeSolidLine()
	{
		for(int i = 0; i < 56; i++)
		{
			System.out.print ( "-" );
		}
		System.out.println (  );
		
	}
	/**********************************************
	 * makeSides()
	 * Outputs side lines to create a box
	 * @parameters: number - number of each row
	 * @return: none
	 **********************************************/
	public static void makeSides(int number, Turns[] turn, boolean putGuess, int numberOfGuesses, int line)
	{
		int firstHalf = 4; // First half of the row 
		// Top line
		for(int i = 0; i < 5; i++)
		{
			System.out.print ( "|     " );
		}
		System.out.print ( "|" );
		for(int i = 0; i < 5; i++)
		{
			System.out.print ( "|     " );
		}
		System.out.println ();
		
		// Middle line
		System.out.print ( "|" );
		if(number == 0)
		{
			firstHalf = 5;
		}
		else
		{
			System.out.printf ( "%3d  ", number );
		}
		for(int i = 0; i < firstHalf; i++)
		{
			if(putGuess)
			{
				System.out.print ( "|  " + turn[number-1].getPlayersGuess()[i] + "  " );
			}
			else
			{
				System.out.print ( "|     " );
			}
		}
		System.out.print ( "|" );
		for(int i = 0; i < 4; i++)
		{
			if(putGuess)
			{
				System.out.print ( "|  " + turn[number-1].getPlacement()[i] + "  " );
			}
			else
			{
				System.out.print ( "|     " );
			}
		}
		System.out.println ("|");
		
		// Bottom line
		for(int i = 0; i < 5; i++)
		{
			System.out.print ( "|     " );
		}
		System.out.print ( "|" );
		for(int i = 0; i < 5; i++)
		{
			System.out.print ( "|     " );
		}
		System.out.println ();
	}
	
	/**********************************************
	 * generateColors()
	 * Generate 4 unique colors for computer
	 * @parameters: generatedNumColors[] - will hold 4 unique numbers, which later will be converted to characters
	 * @return: generatedColors[] - char array of 4 different colors
	 **********************************************/
	public static void generateColors(char[] generatedColors)
	{
		// Generate 4 different numbers for colors
		int[] generatedNumColors = new int[4]; // Generated numbers of colors
		generatedNumColors[0] = (int)(Math.random() * 5 + 1);
		for(int i = 1; i < 4; i++)
		{
			generatedNumColors[i] = (int)(Math.random() * 5 + 1);
			for(int j = i-1; j >= 0; j--)
			{
				if(generatedNumColors[i] == generatedNumColors[j]) // If this colors is already generated, change it
				{
					generatedNumColors[i] = (int)(Math.random() * 5 + 1);
					j = i; // Double check if the new color is not already used
				}
			}
		}
		// Convert numbers to characters
		for(int i = 0; i < 4; i++)
		{
			int choice = generatedNumColors[i];
			switch(choice)
			{
				case 0:
					generatedColors[i] = 'Y';
					break;
				case 1:
					generatedColors[i] = 'P';
					break;
				case 2:
					generatedColors[i] = 'R';
					break;
				case 3:
					generatedColors[i] = 'G';
					break;
				case 4:
					generatedColors[i] = 'B';
					break;
				case 5:
					generatedColors[i] = 'O';
					break;
			}
		}
	}
	
	/**********************************************
	 * outputGeneratedColors()
	 * output generatedColors array
	 * @parameters: generatedColors[] - generated 4 different colors
	 * @return: none
	 **********************************************/
	public static void outputGeneratedColors(char[] generatedColors)
	{
		for(int i = 0; i < 4; i++)
		{
			System.out.print(generatedColors[i] + " ");
		}
		System.out.println();
	}
	
	/**********************************************
	 * getPlayersGuess()
	 * Get players guess of 4 different colors
	 * @parameters: playersGuess[] - player's guess stored in array
	 * @return: playersGuess[] - player's guess stored in array
	 **********************************************/
	public static void getPlayersGuess(char[] playersGuess, int turn)
	{
		System.out.println ( "Guess the pattern of colors (type the first letter of the color)" );
		char guess;
		for(int i = 0; i < 4; i++)
		{
			System.out.println ("Enter the color #" + (i+1) + ":");
			guess = input.next().toUpperCase().charAt ( 0 );
			if(!(guess == 'Y' || guess == 'P' || guess == 'R' || guess == 'G' || guess == 'B' || guess == 'O'))
			{
				do
				{
					System.out.print ( "The color you entered is invalid." );
					System.out.println (" Enter the color #" + (i+1) + ":");
					guess = input.next().toUpperCase().charAt ( 0 );
				}while(!(guess == 'Y' || guess == 'P' || guess == 'R' || guess == 'G' || guess == 'B' || guess == 'O'));
			}
			playersGuess[i] = guess;
			// Check if colors user entered do not repeat
			for(int j = i-1; j >= 0; j--)
			{
				if(playersGuess[i] == playersGuess[j]) // If this colors is already generated, change it
				{
					System.out.println ( "You cannot duplicate colors.");
					i--;
				}
			}
		}
	}
	
	/**********************************************
	 * checkPlacement()
	 * Checks player's guess and returns his correct answers
	 * @parameters: []playersGuess & []generatedColors - needed to check player's correct answers
	 * []placement - an array of correct placements and colors 
	 * @return: []placement - an array of correct placements and colors
	 **********************************************/
	public static int checkPlacement(char[] placement, char[] playersGuess, char[] generatedColors, int numberOfGuesses)
	{
		int placementNum = 0;
		int colorNum = 0;
		for(int i = 0; i < 4; i++)
		{
			placement[i] = ' '; // To make sure every element in array has a space
			if(playersGuess[i] == generatedColors[i])
			{
				placementNum++;
			}
			else
			{
				for(int j = 0; j < 4; j++)
				{
					if(playersGuess[i] == generatedColors[j])
					{
						colorNum++;
					}
				}
			}
		}
		// Place cs and ps into an array
		//int i = 0;
		for(int i = 0; i < colorNum; i++)
		{
			placement[i] = 'c';
		}
		for(int i = colorNum; i < placementNum + colorNum; i++)
		{
			placement[i] = 'p';
			//i++;
		}
		return placementNum;
	}
	
	/**********************************************
	 * endGame()
	 * Outputs the ending prompt
	 * @parameters: numberOfGuesses, playGame - to understand whether user lost or won
	 * generatedColors - to output the correct pattern
	 * @return: none
	 **********************************************/
	public static void endGame(int numberOfGuesses, boolean playGame, char[] generatedColors)
	{
		if(numberOfGuesses == 10)
		{
			System.out.println ( "Game Over! It was the last turn." );
			System.out.print("The correct pattern was: ");
			outputGeneratedColors(generatedColors);
		}
		else if(playGame == false || numberOfGuesses == 10 && playGame == false)
		{
			System.out.println("You won!");
			System.out.println ( "The correct pattern was: " );
			outputGeneratedColors(generatedColors);
		}
	}
}


// No problems
