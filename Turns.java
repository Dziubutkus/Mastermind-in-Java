/*************************************************************************************
| A class for turns		
| Description: A class created for turns. Every turn has an instance of this class, which holds 
| 2 arrays for player's guesses and correct answers.
| Author: Dziugas Butkus
 *************************************************************************************/
public class Turns 
{
	char[] playersGuess = new char[4]; // Player's guesses for the specific turn
	char[] placement = new char[4]; // Player's correct colors and placements for the specific turn
	
	// Constructor
	public Turns()
	{
		for(int i = 0; i < 4; i++)
		{
			this.playersGuess[i] = ' ';
			this.placement[i] = ' ';
		}
	}
	
	public Turns(char[] playersGuess, char[] placement) {
		super();
		this.playersGuess = playersGuess;
		this.placement = placement;
	}

	// Getters and Setters
	public char[] getPlayersGuess() {
		return playersGuess;
	}

	public void setPlayersGuess(char[] playersGuess) {
		this.playersGuess = playersGuess;
	}

	public char[] getPlacement() {
		return placement;
	}

	public void setPlacement(char[] placement) {
		this.placement = placement;
	}
	
}
