package controller;

import model.ReversiModel;

/**
 * 
 * @author Jesse Liu
 *
 */
public class ReversiController {
	
	private ReversiModel model;
	
	public ReversiController(ReversiModel model) {
		this.model = model;
	}
	
	public int getWScore() {
		return model.getWScore();
	}
	
	public int getBScore() {
		return model.getBScore();
	}
	
	public void humanTurn(int row, int col) throws IllegalArgumentException {
		if ((row >= 0) && (row < 8) && (col >= 0) && (col < 8) &&
				model.isLegalW(row, col))
			model.setPosW(row, col);
		else
			throw new IllegalArgumentException();
	}
	
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
		if (maxRow == -1 && maxCol == -1)
			return null;
		model.setPosB(maxRow, maxCol);
		String coordinates = String.valueOf((char) (maxCol + 'a')) + String.valueOf(maxRow + 1);
		return coordinates;
	}
	
	public boolean isGameOver() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (model.isLegalW(i, j) || (model.hypotheticalB(i, j) > 1))
					return false;
			}
		}
		return true;
	}
	
	public char[][] reconstructBoard() {
		char[][] reconstructed = new char[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				reconstructed[i][j] = model.getElementAt(i, j);
			}
		}
		return reconstructed;
	}
	
	public char getWinner() {
		if (model.getWScore() > model.getBScore())
			return 'W';
		else if (model.getWScore() == model.getBScore())
			return '_'; // Signifies a tie
		else
			return 'B';
	}
	
}
