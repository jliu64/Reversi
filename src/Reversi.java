import controller.ReversiController;
import model.ReversiModel;
import view.ReversiView;

/**
 * Class containing the main method for playing the Reversi game.
 * 
 * main creates the model, view, and controller objects to be used for a single game,
 * then calls some methods from view in order to begin playing.
 * 
 * @author Jesse Liu 
 *
 */
public class Reversi {

	public static void main(String[] args) {
		ReversiModel model = new ReversiModel();
		ReversiController controller = new ReversiController(model);
		ReversiView view = new ReversiView(controller);
		
		// Display initial game board and score before game-play truly begins
		view.displayBoard();
		view.displayScore();
		view.playGame();
	}

}
