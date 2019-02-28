package model;

/**
 * 
 * @author Jesse Liu
 *
 */
public class ReversiModel {
	
	private char[][] board;
	private int wScore;
	private int bScore;
	
	public ReversiModel() {
		board = new char[8][8]; // Note: maybe constant for board dimensions better?
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
	
	public char getElementAt(int row, int col) {
		return board[row][col];
	}
	
	public int getWScore() {
		return wScore;
	}
	
	public int getBScore() {
		return bScore;
	}
	
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
	
	// get legal moves for W and B (account for out-of-bounds in controller)
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
