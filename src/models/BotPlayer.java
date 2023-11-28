package models;

import factories.BotPlayingStratergyFactory;
import strategies.botPlayingStrategies.BotPlayingStrategy;

public class BotPlayer extends Player {
	
	private BotDifficultyLevel botDifficultyLevel;
	
	public BotPlayer(int id, String name, char playerSymbol, PlayerType playerType, BotDifficultyLevel botDifficultyLevel) {
		super(id, name, playerSymbol, playerType);
		this.botDifficultyLevel = botDifficultyLevel;
	}
	
	public Move makeMove(Board board) {
		System.out.println("Bot is making a move.");
		BotPlayingStrategy botPlayerStratergy = BotPlayingStratergyFactory.getBotPlayingStratergy(botDifficultyLevel);
		Move move = botPlayerStratergy.makeMove(board, this);
		System.out.println("Bot made a move to row : " + move.getCell().getRow() + " and column : " + move.getCell().getColumn());
		return move;
	}

	public BotDifficultyLevel getBotDifficultyLevel() {
		return botDifficultyLevel;
	}

	public void setBotDifficultyLevel(BotDifficultyLevel botDifficultyLevel) {
		this.botDifficultyLevel = botDifficultyLevel;
	}

}
