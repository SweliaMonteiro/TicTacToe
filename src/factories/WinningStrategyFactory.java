package factories;

import strategies.winningStrategies.AntiDiagonalWinningStrategy;
import strategies.winningStrategies.ColumnWinningStrategy;
import strategies.winningStrategies.DiagonalWinningStrategy;
import strategies.winningStrategies.RowWinningStrategy;
import strategies.winningStrategies.WinningStrategy;

public class WinningStrategyFactory {
	
	public static WinningStrategy getWinningStrategy(String winningStrategy, int boardDimension) {
		
		if(winningStrategy.trim().equalsIgnoreCase("1")) {
			return new RowWinningStrategy(boardDimension);
		}
		
		else if(winningStrategy.trim().equalsIgnoreCase("2")) {
			return new ColumnWinningStrategy(boardDimension);
		}
		
		else if(winningStrategy.trim().equalsIgnoreCase("3")) {
			return new DiagonalWinningStrategy();
		}
		
		else if(winningStrategy.trim().equalsIgnoreCase("4")) {
			return new AntiDiagonalWinningStrategy();
		}
		
		return null;
	}

}
