package view;

import java.util.Scanner;

import controller.ReversiController;

/**
 * 
 * @author Jesse Liu
 *
 */
public class ReversiView {
	
	private ReversiController controller;
	
	public ReversiView(ReversiController controller) {
		this.controller = controller;
		System.out.println("Welcome to Reversi\n");
		System.out.println("You are W.\n");
	}
	
	public void playGame() {
		Scanner userInput = new Scanner(System.in);
		boolean stop = controller.isGameOver();
		while (!stop) {
			System.out.print("Where would you like to place your token? ");
			String response = userInput.nextLine().toLowerCase();
			System.out.println();
			if (response.length() != 2) {
				System.out.println("Illegal move. Try again.\n");
				continue;
			}
			int row = (response.charAt(1) - '0') - 1;
			int col = response.charAt(0) - 'a';
			try {
				controller.humanTurn(row, col);
			} catch (IllegalArgumentException e) {
				System.out.println("Illegal move. Try again.\n");
				continue;
			}
			displayBoard();
			displayScore();
			String coordinates = controller.computerTurn();
			if (coordinates != null) {
				System.out.println("The computer places a piece at " + coordinates + ".\n");
				displayBoard();
				displayScore();
			} else
				System.out.println("The computer cannot make any more moves.\n");
			stop = controller.isGameOver();
		}
		userInput.close();
		if (controller.getWinner() == 'W')
			System.out.println("You win!");
		else if (controller.getWinner() == '_')
			System.out.println("You tied!");
		else
			System.out.println("Better luck next time!");
	}
	
	public void displayBoard() {
		char[][] board = controller.reconstructBoard();
		for (int i = 0; i < board.length; i++) {
			System.out.print(i + 1);
			System.out.print(' ');
			for (int j = 0; j < board[i].length; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h\n");
	}
	
	public void displayScore() {
		System.out.println("The score is " + controller.getWScore() + "-"
				+ controller.getBScore() + ".");
	}
	
}
