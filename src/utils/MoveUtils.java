package utils;

import models.Board;
import models.CellState;
import models.Move;

public class MoveUtils {
	
	public static boolean validateMove(Move move, Board board) {
		int row = move.getCell().getRow();
		int column = move.getCell().getColumn();
		CellState cellState = move.getCell().getCellState();
		
		if(row<0 || row>=board.getSize() || column<0 || column>=board.getSize() || cellState.equals(CellState.FILLED)) {
			return false;
		}
		
		return true;
	}

}
