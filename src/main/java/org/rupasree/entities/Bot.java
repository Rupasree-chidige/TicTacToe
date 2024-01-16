package org.rupasree.entities;

import org.rupasree.factories.BotPlayingStrategyFactory;
import org.rupasree.strategies.botstrategies.BotPlayingStrategy;

public class Bot extends Player{
    private BotDifficultyLevel botDifficultyLevel;
    private BotPlayingStrategy botPlayingStrategy;
    public Bot(String name, char symbol, PlayerType playerType, BotDifficultyLevel botDifficultyLevel) {
        super(name, symbol, playerType);
        this.botDifficultyLevel = botDifficultyLevel;
        this.botPlayingStrategy = BotPlayingStrategyFactory.getInstance(botDifficultyLevel);
    }

    @Override
    public Move makeMove(Board board) {
        return botPlayingStrategy.makeMove(board, this);
    }



}
