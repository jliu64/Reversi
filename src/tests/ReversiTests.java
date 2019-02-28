package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import controller.ReversiController;
import model.ReversiModel;
/**
 * This class contains all of the test methods for the controller and model of the
 * Reversi game.
 * 
 * @author Jesse Liu
 *
 */
class ReversiTests {

	/**
	 * Test method for the Reversi controller.
	 */
	@Test
	void testController() {
		ReversiModel model = new ReversiModel();
		ReversiController controller = new ReversiController(model);
		
		char[][] testBoard = controller.reconstructBoard();
		assertEquals(testBoard[3][3], 'W');
		assertEquals(testBoard[4][4], 'W');
		assertEquals(testBoard[3][4], 'B');
		assertEquals(testBoard[4][3], 'B');
		assertEquals(testBoard[1][1], '_');
		assertEquals(controller.getWScore(), 2);
		assertEquals(controller.getBScore(), 2);
		assertFalse(controller.isGameOver());
		assertEquals(controller.getWinner(), '_'); // Called when game not over for testing
		controller.humanTurn(3, 5);
		assertEquals(controller.getWScore(), 4);
		assertEquals(controller.getBScore(), 1);
		assertEquals(controller.getWinner(), 'W');
		controller.computerTurn();
		assertEquals(controller.getWScore(), 3);
		assertEquals(controller.getBScore(), 3);
		
		assertThrows(IllegalArgumentException.class, () -> controller.humanTurn(1, 1));
		assertThrows(IllegalArgumentException.class, () -> controller.humanTurn(3, 3));
		assertThrows(IllegalArgumentException.class, () -> controller.humanTurn(-1, -1));
		assertThrows(IllegalArgumentException.class, () -> controller.humanTurn(10, 10));
		assertThrows(IllegalArgumentException.class, () -> controller.humanTurn(2, -1));
		assertThrows(IllegalArgumentException.class, () -> controller.humanTurn(2, 10));
	}
	
	/**
	 * Test method for the Reversi model, deliberately calls some illegal moves to
	 * aid in testing.
	 */
	@Test
	void testModelDirectly1() {
		ReversiModel model = new ReversiModel();
		ReversiController controller = new ReversiController(model);
		
		assertFalse(model.isLegalW(1, 0));
		model.setPosW(1, 0);
		assertFalse(model.isLegalW(0, 1));
		model.setPosW(0, 1);
		assertFalse(model.isLegalW(6, 0));
		model.setPosW(6, 0);
		assertFalse(model.isLegalW(7, 1));
		model.setPosW(7, 1);
		assertFalse(model.isLegalW(0, 6));
		model.setPosW(0, 6);
		assertFalse(model.isLegalW(1, 7));
		model.setPosW(1, 7);
		assertFalse(model.isLegalW(6, 7));
		model.setPosW(6, 7);
		assertFalse(model.isLegalW(7, 6));
		model.setPosW(7, 6);
		assertFalse(model.isLegalW(0, 0));
		assertFalse(model.isLegalW(0, 4));
		assertTrue(model.isLegalW(5, 3));
		assertFalse(model.isLegalW(1, 1));
		assertFalse(model.isLegalW(5, 4));
		assertFalse(model.isLegalW(5, 5));
		assertFalse(model.isLegalW(2, 2));
		assertEquals(controller.getWScore(), 10);
		assertEquals(model.hypotheticalB(1, 0), 1);
		model.setPosB(2, 1);
		model.setPosB(1, 2);
		model.setPosB(5, 1);
		model.setPosB(6, 2);
		model.setPosB(1, 5);
		model.setPosB(2, 6);
		model.setPosB(5, 6);
		model.setPosB(6, 5);
		
		controller.humanTurn(4, 2);
		controller.humanTurn(3, 5);
		controller.humanTurn(7, 3);
		controller.humanTurn(5, 4);
		controller.humanTurn(2, 4);
		model.setPosW(1, 1);
		model.setPosW(2, 2);
		controller.humanTurn(3, 1);
		controller.humanTurn(0, 2);
		assertEquals(model.getWScore(), 28);
		assertFalse(controller.isGameOver());
	}
	
	/**
	 * Another test method for the Reversi model, deliberately calls some illegal
	 * moves to aid in testing.
	 */
	@Test
	void testModelDirectly2() {
		ReversiModel model = new ReversiModel();
		ReversiController controller = new ReversiController(model);
		
		model.setPosB(1, 0);
		model.setPosB(0, 1);
		model.setPosB(6, 0);
		model.setPosB(7, 1);
		model.setPosB(0, 6);
		model.setPosB(1, 7);
		model.setPosB(6, 7);
		model.setPosB(7, 6);
		assertEquals(controller.getBScore(), 10);
		model.setPosW(2, 1);
		model.setPosW(1, 2);
		model.setPosW(5, 1);
		model.setPosW(6, 2);
		model.setPosW(1, 5);
		model.setPosW(2, 6);
		model.setPosW(5, 6);
		model.setPosW(6, 5);
		assertFalse(model.isLegalW(4, 7));
		assertFalse(model.isLegalW(5, 5));
		assertFalse(model.isLegalW(7, 4));
		
		assertEquals(model.hypotheticalB(3, 2), 3);
		model.setPosB(3, 2);
		assertEquals(model.hypotheticalB(4, 5), 3);
		model.setPosB(4, 5);
		assertEquals(model.hypotheticalB(0, 3), 2);
		model.setPosB(0, 3);
		assertEquals(model.hypotheticalB(3, 5), 2);
		model.setPosB(3, 5);
		assertEquals(model.hypotheticalB(6, 1), 1);
		model.setPosB(6, 1);
		assertEquals(model.hypotheticalB(5, 2), 1);
		model.setPosB(5, 2);
		assertEquals(model.hypotheticalB(4, 1), 2);
		model.setPosB(4, 1);
		assertEquals(model.hypotheticalB(7, 2), 2);
		model.setPosB(7, 2);
		assertEquals(controller.getBScore(), 26);
		assertEquals(controller.getWinner(), 'B');
		assertFalse(controller.isGameOver());
	}
	
	/**
	 * Another test method for the Reversi controller (to aid in 100% branch coverage).
	 */
	@Test
	void testController2() {
		ReversiModel model = new ReversiModel();
		ReversiController controller = new ReversiController(model);
		model.setPosW(5, 2);
		controller.humanTurn(2, 5);
		assertFalse(model.isLegalW(1, 6));
		assertFalse(model.isLegalW(6, 1));
		assertFalse(model.isLegalW(2, 2));
		assertFalse(model.isLegalW(5, 5));
		assertTrue(controller.isGameOver());
		assertNull(controller.computerTurn());
	}
	
}
