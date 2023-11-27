package strategies.botPlayingStrategies;

import models.Board;
import models.BotPlayer;
import models.Cell;
import models.CellState;
import models.Move;

public class EasyBotPlayingStrategy implements BotPlayingStrategy {

	@Override
	public Move makeMove(Board board, BotPlayer botPlayer) {
		
		for(int i = 0; i < board.getSize(); i++) {
			for(int j = 0; j < board.getSize(); j++) {
				
				if(board.getBoard().get(i).get(j).getCellState().equals(CellState.EMPTY)) {
					Cell cell = new Cell(i, j, botPlayer, CellState.FILLED);
					Move move = new Move(cell, botPlayer);
					return move;
				}
				
			}
		}
		
		return null;
	}

}
