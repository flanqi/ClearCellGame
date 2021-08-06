package model;

import java.util.Random;

/* This class must extend Game */
public class ClearCellGame extends Game {
	private Random random;
	private int strategy;
	private int score;
	
	public ClearCellGame(int maxRows, int maxCols, Random random, int strategy) {
		super(maxRows, maxCols);
		this.random = random;
		this.strategy = strategy;
	}
	
	@Override
	public boolean isGameOver() {
		for(int j = 0; j < getMaxCols(); j++) {
			if(board[getMaxRows()-1][j] != BoardCell.EMPTY) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public int getScore() {
		return score;
	}
	
	@Override
	public void nextAnimationStep() {
		if (!isGameOver()) {
            BoardCell[][] newBoard = new BoardCell[getMaxRows()][getMaxCols()];
            
            for(int j = 0; j < getMaxCols(); j++) {
            	newBoard[0][j] = BoardCell.getNonEmptyRandomBoardCell(random);
            }
            
           
            for(int i = 1; i < getMaxRows(); i++) {
            	for(int j = 0;j < getMaxCols();j++) {
            		newBoard[i][j] = board[i-1][j];
            	}
            }
        
            board = newBoard;
        }
		
		
	}
	
	// determines if a row is empty
	private boolean isEmptyRow(int rowIndex) {
		for(int j = 0; j < getMaxCols(); j++) {
			if(board[rowIndex][j] != BoardCell.EMPTY) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public void processCell(int rowIndex, int colIndex) {
		/* clears cells */
		
		int i = rowIndex;
		int j = colIndex;
		
		BoardCell cell = board[rowIndex][colIndex];
		
		// ensures when user clicks an empty cell, the score won't change
		if(cell != BoardCell.EMPTY) {
			while(j < getMaxCols()-1 && board[i][j+1] == cell) {
				board[i][j+1] = BoardCell.EMPTY;
				j++;
				score++;
			} //clears right
			
			i = rowIndex;
			j = colIndex;
			
			while(j > 0 && board[i][j-1] == cell) {
				board[i][j-1] = BoardCell.EMPTY;
				j--;
				score++;
			} //clears left
			
			i = rowIndex;
			j = colIndex;
			
			while(i>0 && board[i-1][j] == cell) {
				board[i-1][j] = BoardCell.EMPTY;
				i--;
				score++;
			} //clears up
			
			i = rowIndex;
			j = colIndex;
			
			while(i<getMaxRows()-1 && board[i+1][j] == cell) {
				board[i+1][j] = BoardCell.EMPTY;
				i++;
				score++;
			} //clears down
			
			i = rowIndex;
			j = colIndex;
			
			while(i>0 && j>0 && board[i-1][j-1] == cell) {
				board[i-1][j-1] = BoardCell.EMPTY;
				i--;
				j--;
				score++;
			} //clears up left 
			
			i = rowIndex;
			j = colIndex;
			
			while(i>0 && j<getMaxCols()-1 && board[i-1][j+1] == cell) {
				board[i-1][j+1] = BoardCell.EMPTY;
				i--;
				j++;
				score++;
			} //clears up right
			
			i = rowIndex;
			j = colIndex;
			
			while(i<getMaxRows()-1 && j>0 && board[i+1][j-1] == cell) {
				board[i+1][j-1] = BoardCell.EMPTY;
				i++;
				j--;
				score++;
			} //clears down left
			
			i = rowIndex;
			j = colIndex;
			
			while(i<getMaxRows()-1 && j<getMaxCols()-1 && board[i+1][j+1] == cell) {
				board[i+1][j+1] = BoardCell.EMPTY;
				i++;
				j++;
				score++;
			} //clears down right 
			
			board[rowIndex][colIndex] = BoardCell.EMPTY; // clears the point
			score++;

		}
		
		/* collapses empty rows */
        BoardCell[][] newBoard = new BoardCell[getMaxRows()][getMaxCols()];
        
        
        for(int m = 0; m < getMaxRows(); m++) {
        	for(int n = 0; n < getMaxCols(); n++) {
        		newBoard[m][n] = BoardCell.EMPTY;
        	}
        }
       
        for(int m = 0,k = 0;m < board.length; m++) {
        	if(!isEmptyRow(m)) {
        		for(int n = 0; n < board[m].length; n++) {
            		newBoard[k][n] = board[m][n];
            	}
        		k++;
        	}
        }
        
        board = newBoard;
	}
	
}