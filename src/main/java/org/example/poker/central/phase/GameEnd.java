package org.example.poker.central.phase;

import org.example.poker.central.PokerGameSystem;

/**
 * ポーカーゲームのゲームの終了を表現したクラス
 *
 * @author yasu
 * @version 1.0
 */
public class GameEnd implements GamePhase{

    private static final GamePhase singleton = new GameEnd();

    private GameEnd(){}

    /**
     * このクラスはフラグとして処理されるため特に何もしない
     * @param pokerGameSystem {@link PokerGameSystem} ゲームシステム
     */
    @Override
    public void advanceGame(PokerGameSystem pokerGameSystem) {
        // 何もしない
    }

    public static GamePhase getInstance() {
        return singleton;
    }
}
