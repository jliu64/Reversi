package controller;

import model.ReversiModel;

/**
 * Controller for the Reversi game. Allows the view to interact with the model indirectly.
 * 
 * Stores a model for the game, and contains methods for indirectly interacting with said
 * model; namely, methods for processing a human player's turn, processing the
 * computer's turn, checking if the game is over, and returning the winner (or rather,
 * whoever has the advantage at that moment). Additionally, contains methods that
 * return the current scores of each player, as well as one that returns a 2D character
 * array of the game board, so that view can display them. This is done despite model
 * already having such data so that view can still print out said data in the same way
 * even if model's implementation changes.
 * 
 * @author Jesse Liu
 *
 */
public class ReversiController {
	
	private ReversiModel model;
	
	// Constructor (reads in model object)
	public ReversiController(ReversiModel model) {
		this.model = model;
	}
	
	/**
	 * getWScore() is a getter method for the current score of the human player.
	 * 
	 * This method returns the 'W' player's current score as an integer. It was
	 * already implemented in model. It is re-implemented here so that view will
	 * have access to it.
	 * 
	 * @return an integer representing the current score of the human player.
	 */
	public int getWScore() {
		return model.getWScore();
	}
	
	/**
	 * getBScore() is a getter method for the current score of the computer player.
	 * 
	 * This method returns the 'B' player's current score as an integer. It was
	 * already implemented in model. It is re-implemented here so that view will
	 * have access to it.
	 * 
	 * @return an integer representing the current score of the computer.
	 */
	public int getBScore() {
		return model.getBScore();
	}
	
	/**
	 * humanTurn(int, int) processes a single turn for the human player attempting
	 * to place a token at the position given by the parameters.
	 * 
	 * Specifically, row and col are the indices at which the human would like to place
	 * a token. humanTurn(int, int) checks if this given position is a legal place for
	 * the human to place a token (by calling a method from model), adding the token
	 * and converting all captured tokens if it is (again, with a method from model),
	 * and throwing an IllegalArgumentException if it isn't.
	 * 
	 * @param row is an integer representing the vertical position of the desired token
	 * position, with 0 representing the top row and 7 representing the bottom row.
	 * @param col is an integer representing the horizontal position of the desired token
	 * position, with 0 representing the leftmost column and 7 representing the rightmost.
	 * @throws IllegalArgumentException if the desired token position is not a legal
	 * move for the player (checks if said position is out of bounds here, the rest
	 * of the check is done via a method from model).
	 */
	public void humanTurn(int row, int col) throws IllegalArgumentException {
		if ((row >= 0) && (row < 8) && (col >= 0) && (col < 8) &&
				model.isLegalW(row, col))
			model.setPosW(row, col);
		else
			throw new IllegalArgumentException();
	}
	
	/**
	 * computerTurn() processes a single turn for the computer, and returns the
	 * position at which the computer placed a token (as a string coordinate), if
	 * any.
	 * 
	 * This method iterates through each position in the board, checking the potential
	 * score increase (for the computer) if the computer placed a token at the
	 * position in question, and thus finding the move that will give the computer the
	 * greatest amount of points. This check is not made if the position in question is
	 * already occupied, and other illegal moves will not affect the search for the
	 * highest-point-yielding move, as their potential score increases are equal to 1,
	 * which is the starting point for the variable storing the current highest potential
	 * point gain (maxScoreIncrease). If any legal moves are available, one of the
	 * highest-point-yielding moves will be made, and the indices of the position of
	 * said move will be converted to string coordinates and returned. Otherwise, null
	 * will be returned.
	 * 
	 * @return a string representing the position of the move made by the computer
	 * (given by two characters: 'a'-'f' first, representing the column index, followed
	 * by '1'-'8' to represent the row index, such that position (0, 0) in the board is
	 * represented by "a1"), or null if no such move could be made.
	 */
	public String computerTurn() {
		int maxScoreIncrease = 1;
		int maxRow = -1;
		int maxCol = -1;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (model.getElementAt(i, j) == '_') {
					int scoreInc = model.hypotheticalB(i, j);
					if (scoreInc > maxScoreIncrease) {
						maxScoreIncrease = scoreInc;
						maxRow = i;
						maxCol = j;
					}
				}
			}
		}
		if (maxRow == -1)
			return null;
		model.setPosB(maxRow, maxCol);
		String coordinates = String.valueOf((char) (maxCol + 'a')) + String.valueOf(maxRow + 1);
		return coordinates;
	}
	
	/**
	 * isGameOver() returns a boolean indicating whether the game is over or not.
	 * 
	 * If any more moves can be made by either the human player or the computer, then
	 * the game is not yet over. Obviously, it is otherwise over. In order to determine
	 * this, this method checks every position in the board, calling methods from model
	 * at each position in order to check whether either the human or the computer can
	 * make a move at said position. False is returned if either can make a move at any
	 * position. Otherwise, true is returned.
	 * 
	 * @return false if any moves can be made by either player, true otherwise.
	 */
	public boolean isGameOver() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (model.isLegalW(i, j) || (model.hypotheticalB(i, j) > 1))
					return false;
			}
		}
		return true;
	}
	
	/**
	 * reconstructBoard() returns a 2D character array representing the current state
	 * of the game board.
	 * 
	 * More specifically, it creates a new 8x8 2D character array and calls the
	 * getElementAt(int, int) method from model in order to fill the array with
	 * the correct characters representing the current state of the game board ('W',
	 * 'B', or '_'). This method is used to allow view to more easily display the
	 * game board, while preventing it from directly interacting with model. Although
	 * the current implementation of model uses an 8x8 2D character array exactly
	 * like the one constructed here, a new one is made using getElementAt(int, int)
	 * here so that the view and controller do not have to change if the model is
	 * modified to store the game board differently.
	 * 
	 * @return a copy of the game board, represented as an 8x8 2D array of characters.
	 */
	public char[][] reconstructBoard() {
		char[][] reconstructed = new char[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				reconstructed[i][j] = model.getElementAt(i, j);
			}
		}
		return reconstructed;
	}
	
	/**
	 * getWinner() returns a character representing the winner of the game.
	 * 
	 * More accurately, it checks the current scores of each player (using methods
	 * from the model), and returns a character representing the player that currently
	 * holds more points (since this method does nothing to check whether the game is
	 * over or not). If the human player is winning, then 'W' is returned. If the
	 * computer is winning, then 'B' is returned. If neither player holds an advantage,
	 * then '_' is returned.
	 * 
	 * @return 'W' if the human player has more points at the moment, 'B' if the computer
	 * has more points, and '_' if they are tied.
	 */
	public char getWinner() {
		if (model.getWScore() > model.getBScore())
			return 'W';
		else if (model.getWScore() == model.getBScore())
			return '_'; // Signifies a tie
		else
			return 'B';
	}
	
}
