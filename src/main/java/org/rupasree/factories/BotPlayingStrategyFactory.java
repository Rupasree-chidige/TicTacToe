package org.rupasree.factories;

import org.rupasree.entities.BotDifficultyLevel;
import org.rupasree.strategies.botstrategies.BotDifficultPlayingStrategy;
import org.rupasree.strategies.botstrategies.BotEasyPlayingStrategy;
import org.rupasree.strategies.botstrategies.BotPlayingStrategy;

public class BotPlayingStrategyFactory {

    public static BotPlayingStrategy getInstance(BotDifficultyLevel botDifficultyLevel) {
        switch (botDifficultyLevel) {
            case EASY:
                return new BotEasyPlayingStrategy();
            case DIFFICULT:
                return new BotDifficultPlayingStrategy();
            default:
                throw new IllegalArgumentException(botDifficultyLevel.toString() + " is not a valid DifficultyLevel");
        }
    }
}
