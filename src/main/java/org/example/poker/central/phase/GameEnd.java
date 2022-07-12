package org.example.poker.central.phase;

import org.example.poker.central.PokerGameSystem;

public class GameEnd implements GamePhase{

    private static final GamePhase singleton = new GameEnd();

    private GameEnd(){}

    @Override
    public void advanceGame(PokerGameSystem pokerGameSystem) {
        // 何もしない
    }

    public static GamePhase getInstance() {
        return singleton;
    }
}
