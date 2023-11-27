package controllers;

import java.util.List;

import exceptions.BotCountException;
import exceptions.PlayerCountDimensionMismatchException;
import exceptions.SymbolCountException;
import models.Game;
import models.GameState;
import models.Player;
import strategies.winningStrategies.WinningStrategy;

public class GameController {
	
	public Game startGame(int boardDimension, List<Player> players, List<WinningStrategy> winningStrategies) throws BotCountException, PlayerCountDimensionMismatchException, SymbolCountException {
		return Game.getBuilder()
				   .setBoardDimension(boardDimension)
				   .setPlayers(players)
				   .setWinningStratergies(winningStrategies)
				   .build();
	}
	
	public void makeMove(Game game) {
		game.makeMove();
	}
	
	public void displayBoard(Game game) {
		game.getBoard().displayBoard();
	}

	public void undo(Game game) {
		game.undo();
	}

	public GameState checkState(Game game) {
		return game.getGameState();
	}
	
	public Player getWinner(Game game) {
		return null;
	}

}
