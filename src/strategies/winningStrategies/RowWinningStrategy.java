package strategies.winningStrategies;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.Board;
import models.Move;
import models.Symbol;

public class RowWinningStrategy implements WinningStrategy {
	
	List<Map<Symbol, Integer>> rowFreqMap = new ArrayList<>();
	
	public RowWinningStrategy(int boardDimension) {
		for(int i = 0; i < boardDimension; i++) {
			rowFreqMap.add(new HashMap<Symbol, Integer>());
		}
	}
	

	@Override
	public boolean checkWinner(Move move, Board board) {
		int row = move.getCell().getRow();
		int freq = rowFreqMap.get(row).getOrDefault(move.getPlayer().getPlayerSymbol(), 0);
		rowFreqMap.get(row).put(move.getPlayer().getPlayerSymbol(), freq+1);
		
		// Check if current player is the winner
		if(rowFreqMap.get(row).get(move.getPlayer().getPlayerSymbol()) == board.getSize()) {
			return true;
		}
		
		return false;
	}


	@Override
	public void handleUndo(Move move, Board board) {
		int row = move.getCell().getRow();
		int freq = rowFreqMap.get(row).get(move.getPlayer().getPlayerSymbol());
		rowFreqMap.get(row).put(move.getPlayer().getPlayerSymbol(), freq-1);
	}
	
}
