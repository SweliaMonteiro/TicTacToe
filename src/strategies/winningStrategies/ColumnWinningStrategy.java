package strategies.winningStrategies;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.Board;
import models.Move;
import models.Symbol;

public class ColumnWinningStrategy implements WinningStrategy {

	List<Map<Symbol, Integer>> columnFreqMap = new ArrayList<>();
	
	public ColumnWinningStrategy(int boardDimension) {
		for(int i = 0; i < boardDimension; i++) {
			columnFreqMap.add(new HashMap<Symbol, Integer>());
		}
	}
	

	@Override
	public boolean checkWinner(Move move, Board board) {
		int column = move.getCell().getColumn();
		int freq = columnFreqMap.get(column).getOrDefault(move.getPlayer().getPlayerSymbol(), 0);
		columnFreqMap.get(column).put(move.getPlayer().getPlayerSymbol(), freq+1);
		
		// Check if current player is the winner
		if(columnFreqMap.get(column).get(move.getPlayer().getPlayerSymbol()) == board.getSize()) {
			return true;
		}
		
		return false;
	}
	
	
	@Override
	public void handleUndo(Move move, Board board) {
		int column = move.getCell().getColumn();
		int freq = columnFreqMap.get(column).get(move.getPlayer().getPlayerSymbol());
		columnFreqMap.get(column).put(move.getPlayer().getPlayerSymbol(), freq-1);
	}

}