package utils;

import models.Board;
import models.CellState;

public class MoveUtils {
	
	public static boolean validateMove(int row, int column, Board board) {
		
		if(row<0 || row>=board.getSize() || column<0 || column>=board.getSize()) {
			return false;
		}
		
		else if(board.getBoard().get(row).get(column).getCellState().equals(CellState.FILLED)) {
			return false;
		}
		
		return true;
	}

}
