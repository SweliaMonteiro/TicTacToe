package utils;

import java.util.HashSet;
import java.util.List;

import exceptions.BotCountException;
import exceptions.PlayerCountDimensionMismatchException;
import exceptions.SymbolCountException;
import models.Player;
import models.PlayerType;

public class GameUtils {
	
	public static void validateBotCount(List<Player> players) throws BotCountException {
		int botCount = 0;
		for(Player player:players) {
			if(player.getPlayerType().equals(PlayerType.BOT)) {
				botCount++;
			}
		}
		if(botCount > 1) {
			throw new BotCountException("More than one Bot Player found.");
		}
	}
	

	public static void validatePlayerCount(List<Player> players, int boardDimension) throws PlayerCountDimensionMismatchException {
		if(players.size() != boardDimension-1) {
			throw new PlayerCountDimensionMismatchException("Total number of Players are not equal to BoardDimension-1.");
		}
	}
	

	public static void validateSymbolsCount(List<Player> players) throws SymbolCountException {
		HashSet<Character> set = new HashSet<>();
		for(Player player:players) {
			if(set.contains(player.getPlayerSymbol().getSymbol())) {
				throw new SymbolCountException("More than one Player has the symbol : " + player.getPlayerSymbol().getSymbol());
			}
			set.add(player.getPlayerSymbol().getSymbol());
		}
	}

}
