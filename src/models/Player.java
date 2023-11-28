package models;

import java.util.Scanner;

import utils.MoveUtils;

public class Player {

	private int id;
	private String name;
	private Symbol playerSymbol;
	private PlayerType playerType;

	public Player(int id, String name, char playerSymbol, PlayerType playerType) {
		this.id = id;
		this.name = name;
		this.playerSymbol = new Symbol(playerSymbol);
		this.playerType = playerType;
	}

	public Move makeMove(Board board) {
		System.out.print(getName() +"'s turn, please enter row and column : ");
		Scanner scanner = new Scanner(System.in);
		int row = scanner.nextInt();
		int column = scanner.nextInt();
		
		while(!MoveUtils.validateMove(row, column, board)) {
			System.out.print("Not a valid move, please choose another cell : ");
			row = scanner.nextInt();
			column = scanner.nextInt();
		}
		
		board.getBoard().get(row).get(column).setCellState(CellState.FILLED);
		board.getBoard().get(row).get(column).setPlayer(this);
		Move move = new Move(board.getBoard().get(row).get(column), this);
		return move;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Symbol getPlayerSymbol() {
		return playerSymbol;
	}

	public void setPlayerSymbol(Symbol playerSymbol) {
		this.playerSymbol = playerSymbol;
	}

	public PlayerType getPlayerType() {
		return playerType;
	}

	public void setPlayerType(PlayerType playerType) {
		this.playerType = playerType;
	}

}
