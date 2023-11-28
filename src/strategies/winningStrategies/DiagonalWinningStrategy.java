package strategies.winningStrategies;

import java.util.HashMap;
import java.util.Map;

import models.Board;
import models.Move;
import models.Symbol;

public class DiagonalWinningStrategy implements WinningStrategy {

	Map<Symbol, Integer> diagonalFreqMap = new HashMap<Symbol, Integer>();
	

	@Override
	public boolean checkWinner(Move move, Board board) {
		int row = move.getCell().getRow();
		int column = move.getCell().getColumn();
		
		if(row == column) {
			int freq = diagonalFreqMap.getOrDefault(move.getPlayer().getPlayerSymbol(), 0);
			diagonalFreqMap.put(move.getPlayer().getPlayerSymbol(), freq+1);
			
			// Check if current player is the winner
			if(diagonalFreqMap.get(move.getPlayer().getPlayerSymbol()) == board.getSize()) {
				return true;
			}
		}
		
		return false;
	}
	
	
	@Override
	public void handleUndo(Move move, Board board) {
		int row = move.getCell().getRow();
		int column = move.getCell().getColumn();
		if(row == column) {
			int freq = diagonalFreqMap.get(move.getPlayer().getPlayerSymbol());
			diagonalFreqMap.put(move.getPlayer().getPlayerSymbol(), freq-1);
		}
	}

}