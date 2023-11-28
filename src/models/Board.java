package models;

import java.util.ArrayList;
import java.util.List;

public class Board {
	
	private int size;
	private List<List<Cell>> board;
	
	public Board(int size) {
		this.size = size;
		this.board = new ArrayList<>();
		for(int i = 0; i < size; i++) {
			List<Cell> tempList = new ArrayList<Cell>();
			for(int j = 0; j < size; j++) {
				tempList.add(new Cell(i, j, CellState.EMPTY));
			}
			board.add(tempList);
		}
	}
	
	public int getSize() {
		return size;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	
	public List<List<Cell>> getBoard() {
		return board;
	}
	
	public void setBoard(List<List<Cell>> board) {
		this.board = board;
	}
	
	public void displayBoard() {
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				if(board.get(i).get(j).getPlayer() == null) {
					System.out.print(" - ");
				}
				else {
					System.out.print(" " + board.get(i).get(j).getPlayer().getPlayerSymbol().getSymbol() + " ");
				}
			}
			System.out.println();
		}
		System.out.println();
	}
	
}
