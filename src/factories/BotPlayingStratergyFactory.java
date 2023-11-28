package factories;

import models.BotDifficultyLevel;
import strategies.botPlayingStrategies.BotPlayingStrategy;
import strategies.botPlayingStrategies.EasyBotPlayingStrategy;
import strategies.botPlayingStrategies.HardBotPlayingStrategy;
import strategies.botPlayingStrategies.MediumBotPlayingStrategy;

public class BotPlayingStratergyFactory {
	
	public static BotPlayingStrategy getBotPlayingStratergy(BotDifficultyLevel botDifficultyLevel) {
		
		if(botDifficultyLevel.equals(BotDifficultyLevel.EASY)) {
			return new EasyBotPlayingStrategy();
		}
		
		else if(botDifficultyLevel.equals(BotDifficultyLevel.MEDIUM)) {
			return new MediumBotPlayingStrategy();
		}
		
		else if(botDifficultyLevel.equals(BotDifficultyLevel.HARD)) {
			return new HardBotPlayingStrategy();
		}
		
		return null;
	}

}
