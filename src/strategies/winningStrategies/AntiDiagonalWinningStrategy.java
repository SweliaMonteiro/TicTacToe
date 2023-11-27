package strategies.winningStrategies;

import java.util.HashMap;
import java.util.Map;

import models.Board;
import models.Move;
import models.Symbol;

public class AntiDiagonalWinningStrategy implements WinningStrategy {

	Map<Symbol, Integer> antiDiagonalFreqMap = new HashMap<Symbol, Integer>();
	

	@Override
	public boolean checkWinner(Move move, Board board) {
		int row = move.getCell().getRow();
		int column = move.getCell().getColumn();
		
		if(row+column == board.getSize()-1) {
			int freq = antiDiagonalFreqMap.getOrDefault(move.getPlayer().getPlayerSymbol(), 0);
			antiDiagonalFreqMap.put(move.getPlayer().getPlayerSymbol(), freq+1);
			
			// Check if current player is the winner
			if(antiDiagonalFreqMap.get(move.getPlayer().getPlayerSymbol()) == board.getSize()) {
				return true;
			}
		}
		
		return false;
	}
	
	
	@Override
	public void handleUndo(Move move, Board board) {
		int row = move.getCell().getRow();
		int column = move.getCell().getColumn();
		if(row+column == board.getSize()-1) {
			int freq = antiDiagonalFreqMap.get(move.getPlayer().getPlayerSymbol());
			antiDiagonalFreqMap.put(move.getPlayer().getPlayerSymbol(), freq-1);
		}
	}

}
