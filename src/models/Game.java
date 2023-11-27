package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import exceptions.BotCountException;
import exceptions.PlayerCountDimensionMismatchException;
import exceptions.SymbolCountException;
import strategies.winningStrategies.WinningStrategy;
import utils.GameUtils;
import utils.MoveUtils;

public class Game {

	private Board board;
	private List<Player> players;
	private List<Move> moves;
	private GameState gameState;
	private int indexOfCurrentPlayer;
	private Player winner;
	private List<WinningStrategy> winningStrategies;
	private Map<Integer, Player> symbolCount;

	private Game(int boardDimension, List<Player> players, List<WinningStrategy> winningStrategies) {
		this.board = new Board(boardDimension);
		this.players = players;
		this.winningStrategies = winningStrategies;
		this.moves = new ArrayList<>();
		this.gameState = GameState.IN_PROGRESS;
		this.indexOfCurrentPlayer = 0;
		this.symbolCount = new HashMap<>();
	}


	public void makeMove() {
		// Player will make a move 
		Player player = players.get(indexOfCurrentPlayer);
		Move move = player.makeMove(board);

		while(!MoveUtils.validateMove(move, board)) {
			System.out.println("Not a valid move, please choose another cell.");
			move = player.makeMove(board);
		}

		moves.add(move);

		// Check if current player is the winner
		for(WinningStrategy winningStratergy:winningStrategies) {
			if(winningStratergy.checkWinner(move, board)) {
				this.gameState = GameState.WINNER;
				winner = player;
				return;
			}
		}

		// Check if all cells are filled, leads to draw
		if(moves.size() == board.getSize()*board.getSize()) {
			this.gameState = GameState.DRAW;
			return;
		}

		// Move to next player
		indexOfCurrentPlayer = (indexOfCurrentPlayer + 1) % players.size();
	}


	public void undo() {
		/*
		 * 1. Get previous player and only if HUMAN player, ask if they want to undo their move
		 * 2. If player says YES follow the below steps to undo
		 *    a) Remove last entry from moves
		 *    b) Update the board, along with the cell state making player of that cell as null
		 *    c) Update winning strategy map frequencies
		 *    d) Update the current player index to last player index
		 */
		
		int lastPlayerIndex = (indexOfCurrentPlayer + players.size() - 1) % players.size();
		if(players.get(lastPlayerIndex).getPlayerType().equals(PlayerType.HUMAN)) {
			System.out.print("Please enter Y/N if you want to undo your move : ");
			Scanner scanner = new Scanner(System.in);
			String response = scanner.next();
			
			if(response.equalsIgnoreCase("Y")) {
				Move move = moves.remove(moves.size()-1);
				int row = move.getCell().getRow();
				int column = move.getCell().getColumn();
				
				board.getBoard().get(row).get(column).setCellState(CellState.EMPTY);
				board.getBoard().get(row).get(column).setPlayer(null);
				
				for(WinningStrategy winningStratergy:winningStrategies) {
					winningStratergy.handleUndo(move, board);
				}
				
				indexOfCurrentPlayer = lastPlayerIndex;
			}
			scanner.close();
		}
	}


	public static Builder getBuilder() {
		return new Builder();
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public List<Move> getMoves() {
		return moves;
	}

	public void setMoves(List<Move> moves) {
		this.moves = moves;
	}

	public GameState getGameState() {
		return gameState;
	}

	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}

	public Player getWinner() {
		return winner;
	}

	public void setWinner(Player winner) {
		this.winner = winner;
	}

	public List<WinningStrategy> getWinningStrategies() {
		return winningStrategies;
	}

	public void setWinningStrategies(List<WinningStrategy> winningStrategies) {
		this.winningStrategies = winningStrategies;
	}

	public Map<Integer, Player> getSymbolCount() {
		return symbolCount;
	}

	public void setSymbolCount(Map<Integer, Player> symbolCount) {
		this.symbolCount = symbolCount;
	}

	public int getIndexOfCurrentPlayer() {
		return indexOfCurrentPlayer;
	}

	public void setIndexOfCurrentPlayer(int indexOfCurrentPlayer) {
		this.indexOfCurrentPlayer = indexOfCurrentPlayer;
	}

	public static class Builder {
		private int boardDimension;
		private List<Player> players;
		private List<WinningStrategy> winningStratergies;

		public Builder setBoardDimension(int boardDimension) {
			this.boardDimension = boardDimension;
			return this;
		}

		public Builder setPlayers(List<Player> players) {
			this.players = players;
			return this;
		}

		public Builder setWinningStratergies(List<WinningStrategy> winningStratergies) {
			this.winningStratergies = winningStratergies;
			return this;
		}

		public void addPlayers(Player player) {
			players.add(player);
		}

		public void addWinningStratergies(WinningStrategy winningStratergy) {
			winningStratergies.add(winningStratergy);
		}

		public void validate() throws BotCountException, PlayerCountDimensionMismatchException, SymbolCountException {
			GameUtils.validateBotCount(players);  // validate single Bot player
			GameUtils.validatePlayerCount(players, boardDimension);  // validate no of players == dimension - 1 
			GameUtils.validateSymbolsCount(players);  // validate different symbol for every player
		}

		public Game build() throws BotCountException, PlayerCountDimensionMismatchException, SymbolCountException {
			validate();
			return new Game(boardDimension, players, winningStratergies);
		}

	}

}
