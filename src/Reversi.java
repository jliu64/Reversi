import controller.ReversiController;
import model.ReversiModel;
import view.ReversiView;

/**
 * 
 * @author Jesse Liu
 *
 */
public class Reversi {

	public static void main(String[] args) {
		ReversiModel model = new ReversiModel();
		ReversiController controller = new ReversiController(model);
		ReversiView view = new ReversiView(controller);
		
		view.displayBoard();
		view.displayScore();
		view.playGame();
	}

}
