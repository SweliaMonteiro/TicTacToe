package models;

import java.util.Scanner;

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
		Cell cell = new Cell(row, column, this, CellState.FILLED);
		Move move = new Move(cell, this);
		scanner.close();
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
