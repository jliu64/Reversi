package model;

/**
 * Model for the Reversi game. Stores and provides methods for modifying the actual
 * data of the game.
 * 
 * Stores a 2D character array to act as the game board, as well as two integers to
 * track the current score of each player. Implements accessors for given positions
 * on the board and the two scores, as well as methods to make human or computer
 * moves at given positions, check if the human player can legally make a move at a
 * given position, and return the potential increase in score if the computer were to
 * make a move at a given position.
 * 
 * @author Jesse Liu
 *
 */
public class ReversiModel {
	
	private char[][] board;
	private int wScore;
	private int bScore;
	
	// Constructor, initializes game board and scores
	public ReversiModel() {
		board = new char[8][8]; // Game board is given to be 8x8
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if ((i == 3 && j == 3) || (i == 4 && j == 4))
					board[i][j] = 'W';
				else if ((i == 3 && j == 4) || (i == 4 && j == 3))
					board[i][j] = 'B';
				else
					board[i][j] = '_';
			}
		}
		wScore = 2;
		bScore = 2;
	}
	
	/**
	 * getElementAt(int, int) is a simple accessor that returns the character stored
	 * in the game board array at the given indices.
	 * 
	 * @param row is the row index (an integer) of the desired position
	 * @param col is the column index (an integer) of the desired position
	 * @return the character at the given position in the game board (presumably 'W',
	 * 'B', or '_').
	 */
	public char getElementAt(int row, int col) {
		return board[row][col];
	}
	
	/**
	 * getWScore() is an accessor for the score of the human player.
	 * 
	 * @return the integer representing the human player's current score.
	 */
	public int getWScore() {
		return wScore;
	}
	
	/**
	 * getBScore() is an accessor for the score of the computer.
	 * 
	 * @return the integer representing the computer's current score.
	 */
	public int getBScore() {
		return bScore;
	}
	
	/**
	 * setPosW(int, int) makes a human-player move at the given position.
	 * 
	 * More specifically, it reads in the indices of the position (without checking
	 * if such a move is legal or not, as that is taken care of in the controller),
	 * and places a human-player token at that position (i.e. a 'W' character). Any
	 * computer tokens (i.e. 'B' characters) caught between this position and another
	 * 'W' character in a straight, unbroken line are captured and converted into
	 * human-player tokens, as per the rules of Reversi. This is done by checking all
	 * eight directions around the new token: if any number of computer tokens are
	 * adjacent to the new token, this method checks the board positions in a straight
	 * line in the direction of said adjacent computer token. If a human-player token
	 * is detected in this line, then the computer tokens between the two human tokens
	 * in question are captured. If the edge of the board is reached or an empty space
	 * is encountered, then nothing is captured in this direction.
	 * 
	 * @param row is the integer representing the row index of the move being made.
	 * @param col is the integer representing the column index of the move being made.
	 */
	public void setPosW(int row, int col) { // May not be legal move, checked elsewhere
		board[row][col] = 'W';
		wScore++;
		int i = row;
		int j = col;
		
		// Check if positions should be captured to the right
		if (col < 7) {
			j++;
			while (j < 7 && (board[i][j] == 'B'))
				j++;
			if (board[i][j] == 'W') {
				j--;
				while (board[i][j] == 'B') {
					board[i][j] = 'W';
					wScore++;
					bScore--;
					j--;
				}
			}
		}
		
		// Check if positions should be captured below and to the right
		if (row < 7 && col < 7) {
			i = row;
			j = col;
			i++;
			j++;
			while (i < 7 && j < 7 && (board[i][j] == 'B')) {
				i++;
				j++;
			}
			if (board[i][j] == 'W') {
				i--;
				j--;
				while (board[i][j] == 'B') {
					board[i][j] = 'W';
					wScore++;
					bScore--;
					i--;
					j--;
				}
			}
		}
		
		// Check if positions should be captured below
		if (row < 7) {
			i = row;
			j = col;
			i++;
			while (i < 7 && (board[i][j] == 'B'))
				i++;
			if (board[i][j] == 'W') {
				i--;
				while (board[i][j] == 'B') {
					board[i][j] = 'W';
					wScore++;
					bScore--;
					i--;
				}
			}
		}
		
		// Check if positions should be captured below and to the left
		if (row < 7 && col > 0) {
			i = row;
			j = col;
			i++;
			j--;
			while (i < 7 && j > 0 && (board[i][j] == 'B')) {
				i++;
				j--;
			}
			if (board[i][j] == 'W') {
				i--;
				j++;
				while (board[i][j] == 'B') {
					board[i][j] = 'W';
					wScore++;
					bScore--;
					i--;
					j++;
				}
			}
		}
		
		// Check if positions should be captured to the left
		if (col > 0) {
			i = row;
			j = col;
			j--;
			while (j > 0 && (board[i][j] == 'B'))
				j--;
			if (board[i][j] == 'W') {
				j++;
				while (board[i][j] == 'B') {
					board[i][j] = 'W';
					wScore++;
					bScore--;
					j++;
				}
			}
		}
		
		// Check if positions should be captured above and to the left
		if (row > 0 && col > 0) {
			i = row;
			j = col;
			i--;
			j--;
			while (i > 0 && j > 0 && (board[i][j] == 'B')) {
				i--;
				j--;
			}
			if (board[i][j] == 'W') {
				i++;
				j++;
				while (board[i][j] == 'B') {
					board[i][j] = 'W';
					wScore++;
					bScore--;
					i++;
					j++;
				}
			}
		}
		
		// Check if positions should be captured above
		if (row > 0) {
			i = row;
			j = col;
			i--;
			while (i > 0 && (board[i][j] == 'B'))
				i--;
			if (board[i][j] == 'W') {
				i++;
				while (board[i][j] == 'B') {
					board[i][j] = 'W';
					wScore++;
					bScore--;
					i++;
				}
			}
		}
		
		// Check if positions should be captured above and to the right
		if (row > 0 && col < 7) {
			i = row;
			j = col;
			i--;
			j++;
			while (i > 0 && j < 7 && (board[i][j] == 'B')) {
				i--;
				j++;
			}
			if (board[i][j] == 'W') {
				i++;
				j--;
				while (board[i][j] == 'B') {
					board[i][j] = 'W';
					wScore++;
					bScore--;
					i++;
					j--;
				}
			}
		}
	}
	
	/**
	 * setPosB(int, int) makes a computer move at the given position.
	 * 
	 * More specifically, it reads in the indices of the position (without checking
	 * if such a move is legal or not, as that is taken care of in the controller),
	 * and places a computer-player token at that position (i.e. a 'B' character). Any
	 * human tokens (i.e. 'W' characters) caught between this position and another
	 * 'B' character in a straight, unbroken line are captured and converted into
	 * computer tokens, as per the rules of Reversi. This is done by checking all
	 * eight directions around the new token: if any number of human tokens are
	 * adjacent to the new token, this method checks the board positions in a straight
	 * line in the direction of said adjacent human token. If a computer token is
	 * detected in this line, then the human tokens between the two computer tokens
	 * in question are captured. If the edge of the board is reached or an empty space
	 * is encountered, then nothing is captured in this direction.
	 * 
	 * @param row is the integer representing the row index of the move being made.
	 * @param col is the integer representing the column index of the move being made.
	 */
	public void setPosB(int row, int col) { // May not be legal move, checked elsewhere
		board[row][col] = 'B';
		bScore++;
		int i = row;
		int j = col;
		
		// Check if positions should be captured to the right
		if (col < 7) {
			j++;
			while (j < 7 && (board[i][j] == 'W'))
				j++;
			if (board[i][j] == 'B') {
				j--;
				while (board[i][j] == 'W') {
					board[i][j] = 'B';
					bScore++;
					wScore--;
					j--;
				}
			}
		}
		
		// Check if positions should be captured below and to the right
		if (row < 7 && col < 7) {
			i = row;
			j = col;
			i++;
			j++;
			while (i < 7 && j < 7 && (board[i][j] == 'W')) {
				i++;
				j++;
			}
			if (board[i][j] == 'B') {
				i--;
				j--;
				while (board[i][j] == 'W') {
					board[i][j] = 'B';
					bScore++;
					wScore--;
					i--;
					j--;
				}
			}
		}
		
		// Check if positions should be captured below
		if (row < 7) {
			i = row;
			j = col;
			i++;
			while (i < 7 && (board[i][j] == 'W'))
				i++;
			if (board[i][j] == 'B') {
				i--;
				while (board[i][j] == 'W') {
					board[i][j] = 'B';
					bScore++;
					wScore--;
					i--;
				}
			}
		}
		
		// Check if positions should be captured below and to the left
		if (row < 7 && col > 0) {
			i = row;
			j = col;
			i++;
			j--;
			while (i < 7 && j > 0 && (board[i][j] == 'W')) {
				i++;
				j--;
			}
			if (board[i][j] == 'B') {
				i--;
				j++;
				while (board[i][j] == 'W') {
					board[i][j] = 'B';
					bScore++;
					wScore--;
					i--;
					j++;
				}
			}
		}
		
		// Check if positions should be captured to the left
		if (col > 0) {
			i = row;
			j = col;
			j--;
			while (j > 0 && (board[i][j] == 'W'))
				j--;
			if (board[i][j] == 'B') {
				j++;
				while (board[i][j] == 'W') {
					board[i][j] = 'B';
					bScore++;
					wScore--;
					j++;
				}
			}
		}
		
		// Check if positions should be captured above and to the left
		if (row > 0 && col > 0) {
			i = row;
			j = col;
			i--;
			j--;
			while (i > 0 && j > 0 && (board[i][j] == 'W')) {
				i--;
				j--;
			}
			if (board[i][j] == 'B') {
				i++;
				j++;
				while (board[i][j] == 'W') {
					board[i][j] = 'B';
					bScore++;
					wScore--;
					i++;
					j++;
				}
			}
		}
		
		// Check if positions should be captured above
		if (row > 0) {
			i = row;
			j = col;
			i--;
			while (i > 0 && (board[i][j] == 'W'))
				i--;
			if (board[i][j] == 'B') {
				i++;
				while (board[i][j] == 'W') {
					board[i][j] = 'B';
					bScore++;
					wScore--;
					i++;
				}
			}
		}
		
		// Check if positions should be captured above and to the right
		if (row > 0 && col < 7) {
			i = row;
			j = col;
			i--;
			j++;
			while (i > 0 && j < 7 && (board[i][j] == 'W')) {
				i--;
				j++;
			}
			if (board[i][j] == 'B') {
				i++;
				j--;
				while (board[i][j] == 'W') {
					board[i][j] = 'B';
					bScore++;
					wScore--;
					i++;
					j--;
				}
			}
		}
	}
	
	/**
	 * isLegalW(int, int) checks if it is legal for the human player to put a token ('W')
	 * at the given position or not, and returns a representative boolean for it.
	 * 
	 * This is done by checking all eight straight directions around the position in
	 * question, as with the setPosW(int, int) method above (see its documentation).
	 * If it is detected that any computer tokens would be captured in this way, then
	 * true is returned. Otherwise, false is returned. If the position in question is
	 * not empty, then false is automatically returned. Array bounds are not checked in
	 * this method, as that is taken care of in the controller.
	 * 
	 * @param row is the integer representing the row index of the position in question.
	 * @param col is the integer representing the column index of the position in question.
	 * @return true if the human player can legally make a move at the given position,
	 * false otherwise.
	 */
	public boolean isLegalW(int row, int col) {
		if (board[row][col] != '_')
			return false;
		int i = row;
		int j = col;
		
		// Check if move is legal from the right
		if (col < 7) {
			j++;
			while (j < 7 && (board[i][j] == 'B'))
				j++;
			if (board[i][j] == 'W' && (j != col + 1))
				return true;
		}
		
		// Check if move is legal from below and to the right
		if (row < 7 && col < 7) {
			i = row;
			j = col;
			i++;
			j++;
			while (i < 7 && j < 7 && (board[i][j] == 'B')) {
				i++;
				j++;
			}
			if (board[i][j] == 'W' && (i != row + 1))
				return true;
		}
		
		// Check if move is legal from below
		if (row < 7) {
			i = row;
			j = col;
			i++;
			while (i < 7 && (board[i][j] == 'B'))
				i++;
			if (board[i][j] == 'W' && (i != row + 1))
				return true;
		}
		
		// Check if move is legal from below and to the left
		if (row < 7 && col > 0) {
			i = row;
			j = col;
			i++;
			j--;
			while (i < 7 && j > 0 && (board[i][j] == 'B')) {
				i++;
				j--;
			}
			if (board[i][j] == 'W' && (i != row + 1))
				return true;
		}
		
		// Check if move is legal from the left
		if (col > 0) {
			i = row;
			j = col;
			j--;
			while (j > 0 && (board[i][j] == 'B'))
				j--;
			if (board[i][j] == 'W' && (j != col - 1))
				return true;
		}
		
		// Check if move is legal from above and to the left
		if (row > 0 && col > 0) {
			i = row;
			j = col;
			i--;
			j--;
			while (i > 0 && j > 0 && (board[i][j] == 'B')) {
				i--;
				j--;
			}
			if (board[i][j] == 'W' && (i != row - 1))
				return true;
		}
		
		// Check if move is legal from above
		if (row > 0) {
			i = row;
			j = col;
			i--;
			while (i > 0 && (board[i][j] == 'B'))
				i--;
			if (board[i][j] == 'W' && (i != row - 1))
				return true;
		}
		
		// Check if move is legal from above and to the right
		if (row > 0 && col < 7) {
			i = row;
			j = col;
			i--;
			j++;
			while (i > 0 && j < 7 && (board[i][j] == 'B')) {
				i--;
				j++;
			}
			if (board[i][j] == 'W' && (i != row - 1))
				return true;
		}
		
		return false;
	}
	
	/**
	 * hypotheticalB(int, int) returns an integer representing the increase in score if
	 * the computer were to make a move at the given position.
	 * 
	 * This is done by once again checking all eight straight directions around the given
	 * position, and counting the total number of human tokens that would be captured if
	 * such a move were made by the computer. This method also functions as a way to
	 * check the legality of a computer move, as 1 would be returned if the move were
	 * illegal (since the only computer token gained would be the one just added). Thus,
	 * if the position in question is already occupied, 1 is automatically returned.
	 * This method does not check array bounds, as the controller takes care of that
	 * (or, more accurately, this method will never be called with out-of-bounds indices
	 * because of the way the controller uses this method).
	 * 
	 * @param row is the integer representing the row index of the move being checked.
	 * @param col is the integer representing the column index of the move being checked.
	 * @return an integer representing the increase in the computer's score if it made
	 * a move at the given position.
	 */
	public int hypotheticalB(int row, int col) { // Return increase in score if move was made
		if (board[row][col] != '_')
			return 1;
		int scoreInc = 1;
		int i = row;
		int j = col;
		
		// Check if positions should be captured to the right
		if (col < 7) {
			j++;
			while (j < 7 && (board[i][j] == 'W'))
				j++;
			if (board[i][j] == 'B') {
				j--;
				while (board[i][j] == 'W') {
					scoreInc++;
					j--;
				}
			}
		}
		
		// Check if positions should be captured below and to the right
		if (row < 7 && col < 7) {
			i = row;
			j = col;
			i++;
			j++;
			while (i < 7 && j < 7 && (board[i][j] == 'W')) {
				i++;
				j++;
			}
			if (board[i][j] == 'B') {
				i--;
				j--;
				while (board[i][j] == 'W') {
					scoreInc++;
					i--;
					j--;
				}
			}
		}
		
		// Check if positions should be captured below
		if (row < 7) {
			i = row;
			j = col;
			i++;
			while (i < 7 && (board[i][j] == 'W'))
				i++;
			if (board[i][j] == 'B') {
				i--;
				while (board[i][j] == 'W') {
					scoreInc++;
					i--;
				}
			}
		}
		
		// Check if positions should be captured below and to the left
		if (row < 7 && col > 0) {
			i = row;
			j = col;
			i++;
			j--;
			while (i < 7 && j > 0 && (board[i][j] == 'W')) {
				i++;
				j--;
			}
			if (board[i][j] == 'B') {
				i--;
				j++;
				while (board[i][j] == 'W') {
					scoreInc++;
					i--;
					j++;
				}
			}
		}
		
		// Check if positions should be captured to the left
		if (col > 0) {
			i = row;
			j = col;
			j--;
			while (j > 0 && (board[i][j] == 'W'))
				j--;
			if (board[i][j] == 'B') {
				j++;
				while (board[i][j] == 'W') {
					scoreInc++;
					j++;
				}
			}
		}
		
		// Check if positions should be captured above and to the left
		if (row > 0 && col > 0) {
			i = row;
			j = col;
			i--;
			j--;
			while (i > 0 && j > 0 && (board[i][j] == 'W')) {
				i--;
				j--;
			}
			if (board[i][j] == 'B') {
				i++;
				j++;
				while (board[i][j] == 'W') {
					scoreInc++;
					i++;
					j++;
				}
			}
		}
		
		// Check if positions should be captured above
		if (row > 0) {
			i = row;
			j = col;
			i--;
			while (i > 0 && (board[i][j] == 'W'))
				i--;
			if (board[i][j] == 'B') {
				i++;
				while (board[i][j] == 'W') {
					scoreInc++;
					i++;
				}
			}
		}
		
		// Check if positions should be captured above and to the right
		if (row > 0 && col < 7) {
			i = row;
			j = col;
			i--;
			j++;
			while (i > 0 && j < 7 && (board[i][j] == 'W')) {
				i--;
				j++;
			}
			if (board[i][j] == 'B') {
				i++;
				j--;
				while (board[i][j] == 'W') {
					scoreInc++;
					i++;
					j--;
				}
			}
		}
		return scoreInc;
	}
	
}
