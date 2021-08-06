package tests;

import static org.junit.Assert.*;
import java.util.Random;
import org.junit.Test;
import model.*;

public class StudentTests {
	public static final String TESTS_TAG = "\nClearCellGameTest";
	
	@Test // test score
	public void test1() {
		int maxRows = 8, maxCols = 8, strategy = 1;
		Game ccGame = new ClearCellGame(maxRows, maxCols, new Random(1L),
				strategy);

		ccGame.setBoardWithColor(BoardCell.BLUE);
		ccGame.setRowWithColor(maxRows - 1, BoardCell.EMPTY);
		ccGame.setRowWithColor(1, BoardCell.YELLOW);
		ccGame.setBoardCell(1, maxCols - 1, BoardCell.RED);
		ccGame.setRowWithColor(3, BoardCell.GREEN);
		ccGame.setRowWithColor(6, BoardCell.RED);
		ccGame.setBoardCell(1, 6, BoardCell.GREEN);
		ccGame.setBoardCell(2, 3, BoardCell.GREEN);
		ccGame.setBoardCell(2, 5, BoardCell.GREEN);
		ccGame.setBoardCell(4, 4, BoardCell.GREEN);
		ccGame.setBoardCell(5, 4, BoardCell.GREEN);
		
		String answer = "Before processCell\n\n";
		answer += getBoardStr(ccGame);
		ccGame.processCell(3, 4);
		answer += "\nAfter processCell\n";
		answer += getBoardStr(ccGame);
		
		answer += "\nScore\n";
		answer += ccGame.getScore();
				
		answer += TESTS_TAG;
		System.out.println(answer);
		assertTrue(TestsSupport.isCorrect("stuTest1.txt", answer));
	}
	
	@Test // test gameover
	public void test2() {
		int maxRows = 8, maxCols = 8, strategy = 1;
		Game ccGame = new ClearCellGame(maxRows, maxCols, new Random(1L),
				strategy);

		ccGame.setBoardWithColor(BoardCell.BLUE);
		ccGame.setRowWithColor(maxRows - 1, BoardCell.EMPTY);
		ccGame.setBoardCell(maxRows-1, 4, BoardCell.GREEN);
		
		assertTrue(ccGame.isGameOver());
	}

	
	@Test // collapse 2 rows at the same time
	public void test3() {
		int maxRows = 8, maxCols = 8, strategy = 1;
		Game ccGame = new ClearCellGame(maxRows, maxCols, new Random(1L),
				strategy);

		ccGame.setBoardWithColor(BoardCell.BLUE);
		ccGame.setRowWithColor(maxRows - 1, BoardCell.EMPTY);
		ccGame.setRowWithColor(1, BoardCell.YELLOW);
		ccGame.setRowWithColor(2, BoardCell.EMPTY);
		ccGame.setBoardCell(1, maxCols - 1, BoardCell.RED);
		ccGame.setRowWithColor(3, BoardCell.GREEN);
		ccGame.setRowWithColor(6, BoardCell.RED);
		ccGame.setBoardCell(1, 6, BoardCell.GREEN);
		ccGame.setBoardCell(2, 3, BoardCell.GREEN);
		ccGame.setBoardCell(2, 5, BoardCell.GREEN);
		ccGame.setBoardCell(4, 4, BoardCell.GREEN);
		ccGame.setBoardCell(5, 4, BoardCell.GREEN);
		
		String answer = "Before processCell\n\n";
		answer += getBoardStr(ccGame);
		ccGame.processCell(3, 4);
		answer += "\nAfter processCell\n";
		answer += getBoardStr(ccGame);
		
		answer += "\nScore\n";
		answer += ccGame.getScore();
				
		answer += TESTS_TAG;
		assertTrue(TestsSupport.isCorrect("stuTest3.txt", answer));
	}
	
	private static String getBoardStr(Game game) {
		int maxRows = game.getMaxRows(), maxCols = game.getMaxCols();

		String answer = "Board(Rows: " + maxRows + ", Columns: " + maxCols + ")\n";
		for (int row = 0; row < maxRows; row++) {
			for (int col = 0; col < maxCols; col++) {
				answer += game.getBoardCell(row, col).getName();
			}
			answer += "\n";
		}

		return answer;
	}
}
