package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import controllers.GameController;
import exceptions.BotCountException;
import exceptions.PlayerCountDimensionMismatchException;
import exceptions.SymbolCountException;
import factories.BotDifficultyLevelFactory;
import factories.WinningStrategyFactory;
import models.BotPlayer;
import models.Game;
import models.GameState;
import models.Player;
import models.PlayerType;
import strategies.winningStrategies.WinningStrategy;

public class Main {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Please enter the number of human players : ");
		int num = scanner.nextInt();

		List<Player> players = new ArrayList<Player>();
		for(int i = 0; i < num; i++) { 
			System.out.print("Enter the name of player #" + (i+1) + " : ");
			String name = scanner.next();
			System.out.print("Enter the symbol for the player #" + (i+1) + " : ");
			char symbol = scanner.next().charAt(0);
			players.add(new Player(i+1, name, symbol, PlayerType.HUMAN));
		}

		System.out.print("Should we add a bot? Y/N : ");
		String addBot = scanner.next();
		if(addBot.equalsIgnoreCase("Y")) {
			System.out.print("Please enter difficulty level for the BOT : Easy(E) / Medium(M) / Hard(H) : ");
			String difficultyLevel = scanner.next();
			players.add(new BotPlayer(0, "BOT", 'B', PlayerType.BOT, BotDifficultyLevelFactory.getBotDifficultyLevel(difficultyLevel)));
		}

		int boardDimension = players.size() + 1;
		
		System.out.print("Please enter comma separted Winning  Stratergies : (1)Row / (2)Column / (3)Diagonal / (4)Anti-Diagonal : "); 
		String inputWinningStrategies = scanner.next();
		String[] inputWinningStrategiesArray = inputWinningStrategies.split(",");
		List<WinningStrategy> winningStrategies = new ArrayList<>();
		for(String inputWinningStratergy:inputWinningStrategiesArray) {
			winningStrategies.add(WinningStrategyFactory.getWinningStrategy(inputWinningStratergy, boardDimension));
		}

		GameController gameController = new GameController();

		Game game;
		try {
			game = gameController.startGame(boardDimension, players, winningStrategies);
		}
		catch (BotCountException | PlayerCountDimensionMismatchException | SymbolCountException e) {
			System.out.println("Invalid game construction exception : " + e.getMessage());
			return;
		}

		System.out.println("Game is starting.....");
		
		gameController.displayBoard(game);

		while(gameController.checkState(game).equals(GameState.IN_PROGRESS)) {
			gameController.makeMove(game);
			gameController.displayBoard(game);
			gameController.undo(game);
		}
		
		System.out.println();

		if(gameController.checkState(game).equals(GameState.WINNER)) {
			System.out.println("Game Over. Winner is : " + game.getWinner().getName());
		}
		else if(gameController.checkState(game).equals(GameState.DRAW)) {
			System.out.println("Game has drawn!");
		}
		
		System.out.println();
		System.out.println("Final Board : ");
		gameController.displayBoard(game);

	}

}
