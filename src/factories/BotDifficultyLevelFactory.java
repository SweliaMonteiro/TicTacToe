package factories;

import models.BotDifficultyLevel;

public class BotDifficultyLevelFactory {
	
	public static BotDifficultyLevel getBotDifficultyLevel(String difficultyLevel) {
		
		if(difficultyLevel.trim().equalsIgnoreCase("E")) {
			return BotDifficultyLevel.EASY;
		}
		
		else if(difficultyLevel.trim().equalsIgnoreCase("M")) {
			return BotDifficultyLevel.MEDIUM;
		}
		
		else if(difficultyLevel.trim().equalsIgnoreCase("H")) {
			return BotDifficultyLevel.HARD;
		}
		
		return null;
	}

}
