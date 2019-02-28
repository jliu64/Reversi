package view;

import java.util.Scanner;

import controller.ReversiController;

/**
 * View for the Reversi game (handles user input and output).
 * 
 * Stores a controller for the game, which is then used to send user input to
 * the model, as well as have the computer take its turns and check if the game
 * is over (and if so, who won). Contains the method which reads in user input
 * and maintains the game so long as it hasn't ended, as well as methods for
 * visually displaying the current state of the game board and the current score.
 * 
 * @author Jesse Liu
 *
 */
public class ReversiView {
	
	private ReversiController controller;
	
	// Constructor (reads in controller object)
	public ReversiView(ReversiController controller) {
		this.controller = controller;
		System.out.println("Welcome to Reversi\n");
		System.out.println("You are W.\n");
	}
	
	/**
	 * playGame() reads in user input and calls methods from controller in order
	 * maintain the game so long as it hasn't ended.
	 * 
	 * It uses a while loop which continues so long as the game is not over; within
	 * this loop, the user is prompted, user input is processed (including conversion
	 * from string coordinates to 2D array indices as integers), and controller methods
	 * are called to process both the turns of the user and the computer. Calls both
	 * displayBoard() and displayScores() to visually show the game board and scores
	 * after every turn. Additionally, IllegalArgumentExceptions can be caught here,
	 * in which case the user is simply re-prompted. After the game ends, the victor
	 * is printed.
	 */
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
	
	/**
	 * displayBoard() is used to show the game board on the screen.
	 * 
	 * More specifically, it calls the controller's reconstructBoard() method in order
	 * to obtain a 2D array of the game board, then uses it to print out a visual
	 * representation of the current state of the game board.
	 */
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
	
	/**
	 * displayScore() is used to display the scores of the player and the computer
	 * on the screen.
	 * 
	 * More specifically, it calls the getWScore() and getBScore() methods from the
	 * controller, and prints them accordingly.
	 */
	public void displayScore() {
		System.out.println("The score is " + controller.getWScore() + "-"
				+ controller.getBScore() + ".");
	}
	
}
